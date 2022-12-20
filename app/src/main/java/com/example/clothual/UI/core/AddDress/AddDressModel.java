package com.example.clothual.UI.core.AddDress;

import android.app.Application;
import android.content.ContentResolver;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;

import com.example.clothual.Database.ImageDao;
import com.example.clothual.Database.RoomDatabase;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class AddDressModel {


    public Application application;
    public RoomDatabase database;
    private ImageDao imageDao;

    public AddDressModel(Application application) {
        this.application = application;
        database = RoomDatabase.getDatabase(application);
        imageDao = database.imageDao();
    }



    public Bitmap importImageFromMemory(ContentResolver contentResolver, Uri imageUri) throws FileNotFoundException {
        InputStream inputStream = contentResolver.openInputStream(imageUri);
        return BitmapFactory.decodeStream(inputStream);
    }

}
