package com.example.clothual.database;

import static com.example.clothual.Util.Query.*;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.clothual.Model.User;

import java.util.List;

@Dao
public interface UserDao {

    @Query(SELECT_ALL_USER)
    List<User> getAllUser();

    @Insert
    void insertUser(User user);

    @Delete
    void deleteUser(User user);
}
