package com.example.clothual.UI.core.categories;

import android.app.Application;
import android.content.ContentResolver;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;

import com.example.clothual.Database.ClothualDao;
import com.example.clothual.Database.ImageDao;
import com.example.clothual.Database.RoomDatabase;
import com.example.clothual.Model.Clothual;
import com.example.clothual.Model.Image;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

public class ShoesFragmentModel {

    public Application application;
    public RoomDatabase database;
    private ImageDao imageDao;
    private ClothualDao clothualDao;

    public ShoesFragmentModel(Application application) {
        this.application = application;
        database = RoomDatabase.getDatabase(application);
        imageDao = database.imageDao();
        clothualDao = database.clothualDao();
    }

    public List<Image> getImageList(ContentResolver contentResolver) {
      /*  List<Image> image = imageDao.getAllImage();
        List<Bitmap> bitmaps = new ArrayList<>();
        if(image.isEmpty()){
            return null;
        }else{
            for(int i = 0; i < image.size(); i++){
                bitmaps.add(importImageFromMemory(contentResolver, Uri.parse(image.get(i).getUri())));
            }
            return bitmaps;
        }

       */
        return imageDao.getAllImage();
    }

    public Bitmap importImageFromMemory(ContentResolver contentResolver, Uri imageUri) throws FileNotFoundException {
        InputStream inputStream = contentResolver.openInputStream(imageUri);
        return BitmapFactory.decodeStream(inputStream);
    }

    public List<Clothual> getClothualList(){
        return clothualDao.getAllClothual();
    }
}
