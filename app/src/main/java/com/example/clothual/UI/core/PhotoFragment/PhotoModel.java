package com.example.clothual.UI.core.PhotoFragment;

import static com.example.clothual.Util.Constant.PATTERN_DATE_FORMAT;
import static com.example.clothual.Util.Constant.PATTERN_HOUR_FORMAT;

import android.app.Application;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;

import androidx.annotation.RequiresApi;

import com.example.clothual.Database.ImageDao;
import com.example.clothual.Database.RoomDatabase;
import com.example.clothual.Model.Image;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class PhotoModel {

    public Application application;
    public RoomDatabase database;
    private ImageDao imageDao;

    public PhotoModel(Application application) {
        this.application = application;
        database = RoomDatabase.getDatabase(application);
        imageDao = database.imageDao();
    }

    public Uri saveImage(ContentResolver contentResolver, Bitmap image, String title, String description) throws IOException {
        return saveImageToMemory( contentResolver,  image,  title,  description);
    }


    public Uri saveImageToMemory(ContentResolver contentResolver, Bitmap bitmap, String title, String description) throws IOException {
        // Crea una nuova entrata per l'immagine nella memoria del dispositivo
        System.out.println("Salvataggio");
        ContentValues values = new ContentValues();
        values.put(MediaStore.Images.Media.TITLE, title);
        values.put(MediaStore.Images.Media.DESCRIPTION, description);
        values.put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg");
        Uri uri = contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);

        // Apri uno stream di output verso la nuova entrata
        OutputStream outputStream = contentResolver.openOutputStream(uri);

        // Salva l'immagine nella memoria del dispositivo
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
        outputStream.close();

        // Restituisce l'URI dell'immagine appena salvata
        Image image = new Image(title, description, uri.toString());
        imageDao.insertImage(image);
        return uri;
    }

    public Bitmap getImage(ContentResolver contentResolver) throws FileNotFoundException {
        List<Image> image = imageDao.getAllImage();

        if(image.isEmpty()){
            return null;
        }else{
            return importImageFromMemory(contentResolver, Uri.parse(image.get(0).getUri()));
        }

    }

    public List<Bitmap> getImageList(ContentResolver contentResolver) throws FileNotFoundException {
        List<Image> image = imageDao.getAllImage();
        List<Bitmap> bitmaps = new ArrayList<>();
        if(image.isEmpty()){
            return null;
        }else{
            for(int i = 0; i < image.size(); i++){
                bitmaps.add(importImageFromMemory(contentResolver, Uri.parse(image.get(i).getUri())));
            }
            return bitmaps;
        }

    }


    public Bitmap importImageFromMemory(ContentResolver contentResolver, Uri imageUri) throws FileNotFoundException {
        InputStream inputStream = contentResolver.openInputStream(imageUri);
        return BitmapFactory.decodeStream(inputStream);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public String getNameImage(){
        DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern(PATTERN_DATE_FORMAT)
                .withZone(ZoneId.systemDefault());
        DateTimeFormatter timeColonFormatter = DateTimeFormatter.ofPattern(PATTERN_HOUR_FORMAT).withZone(ZoneId.systemDefault());
        Clock clock = Clock.systemDefaultZone();
        Instant instant = clock.instant();
        System.out.println("Nome stampato");
        return formatterDate.format(instant)+"__"+timeColonFormatter.format(instant);
    }


}
