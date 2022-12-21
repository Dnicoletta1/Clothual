package com.example.clothual.UI.core.AddDress;

import android.app.Application;

import com.example.clothual.Database.ClothualDao;
import com.example.clothual.Database.ImageDao;
import com.example.clothual.Database.RoomDatabase;

public class InformationModel {

    public Application application;
    public RoomDatabase database;
    private ImageDao imageDao;
    private ClothualDao clothualDao;

    public InformationModel(Application application) {
        this.application = application;
        database = RoomDatabase.getDatabase(application);
        imageDao = database.imageDao();
        clothualDao = database.clothualDao();
    }
}
