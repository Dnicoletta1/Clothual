package com.example.clothual.Database;

import static com.example.clothual.Util.Query.SELECT_ALL_CLOTHAUL;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.clothual.Model.Clothual;

import java.util.List;

@Dao
public interface ClothualDao {

    @Insert
    void insertClothual(Clothual clothual);

    @Query(SELECT_ALL_CLOTHAUL)
    List<Clothual> getAllClothual();

}
