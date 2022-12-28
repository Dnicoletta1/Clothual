package com.example.clothual.UI.core.Categories;

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

    public List<Image> getImageList(){
        return imageDao.getAllImage();
    }

    public List<Image> getImageShoesList(List<Clothual> clothualList) {
        List<Image> imageList = imageDao.getAllImage();
        List<Image> shoes = new ArrayList<>();
        for(int i = 0; i < clothualList.size(); i++){
            for(int j = 0; j < imageList.size(); j++){
                if(clothualList.get(i).getIdImage() == imageList.get(j).getID()){
                    shoes.add(imageList.get(j));
                }
            }
        }
        return shoes;
    }

    public List<Image> getImagePreferiteList(List<Clothual> clothualList) {
        List<Image> imageList = imageDao.getAllImage();
        List<Image> preferite = new ArrayList<>();
        for(int i = 0; i < clothualList.size(); i++){
            for(int j = 0; j < imageList.size(); j++){
                if(clothualList.get(i).getIdImage() == imageList.get(j).getID()){
                    preferite.add(imageList.get(j));
                }
            }
        }
        return preferite;
    }

   public List<Clothual> getShoesList(){
        List<Clothual> list = clothualDao.getAllClothual();
        List<Clothual> shoes = new ArrayList<>();
        for(int i = 0; i < list.size(); i++){
            if(list.get(i).getType() == 1){
                shoes.add(list.get(i));
            }
        }
       System.out.println("Lughezza shoes: " + shoes.size());

       return shoes;

    }

    public List<Clothual> getClothualList(){
        return clothualDao.getAllClothual();
    }

    public List<Clothual> getPreferiteList(){
        List<Clothual> list = clothualDao.getAllClothual();
        List<Clothual> preferite = new ArrayList<>();
        for(int i = 0; i < list.size(); i++){
            if(list.get(i).isPreferite()){
                preferite.add(list.get(i));
            }
        }
        System.out.println("Lughezza preferiti: " + preferite.size());
        return preferite;
    }

    public void deleteClothual(Clothual clothual){
        clothualDao.deleteClothual(clothual);
    }

    public void deleteImage(Image image){
        imageDao.deleteImage(image);
    }

    public void updateClothaulElement(Clothual clothual){
        clothualDao.updateClothual(clothual);
    }

}
