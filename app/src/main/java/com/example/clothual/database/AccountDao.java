package com.example.clothual.database;

import static com.example.clothual.Util.Query.GET_ID;
import static com.example.clothual.Util.Query.SELECT_ALL_ACCOUNT;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.clothual.Model.Account;

import java.util.List;

@Dao
public interface AccountDao {

    @Query(SELECT_ALL_ACCOUNT)
    List<Account> getAllAccount();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAccount(Account account);

    @Delete
    void deleteAccount(Account account);

    @Query(GET_ID)
    int getId(String username);
}
