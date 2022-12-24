package com.example.clothual.UI.core.categories;

import android.app.Application;

import com.example.clothual.Database.ClothualDao;
import com.example.clothual.Database.ImageDao;
import com.example.clothual.Database.RoomDatabase;
import com.example.clothual.Model.Clothual;
import com.example.clothual.Model.Image;

import java.util.ArrayList;
import java.util.List;

public class CategoryModel {

    public Application application;
    public RoomDatabase database;
    private ImageDao imageDao;
    private ClothualDao clothualDao;

    public CategoryModel(Application application) {
        this.application = application;
        database = RoomDatabase.getDatabase(application);
        imageDao = database.imageDao();
        clothualDao = database.clothualDao();
    }

    public List<Image> getImageList() {
        return imageDao.getAllImage();
    }

   public List<Clothual> getShoesList(){
        List<Clothual> list = clothualDao.getAllClothual();
        List<Clothual> shoes = new ArrayList<>();
        for(int i = 0; i < list.size(); i++){
            if(list.get(i).getType() == 1){
                shoes.add(list.get(i));
            }
        }
        return shoes;

    }

    public List<Clothual> getClothualList(){
        return clothualDao.getAllClothual();
    }

    public void deleteClothual(Clothual clothual){
        clothualDao.deleteClothual(clothual);
    }

    public void deleteImage(Image image){
        imageDao.deleteImage(image);
    }
}
