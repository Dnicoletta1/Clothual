package com.example.clothual.database;

import static com.example.clothual.Util.Query.*;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.clothual.Model.Account;

import java.util.List;

@Dao
public interface AccountDao {

    @Query(SELECT_ALL_ACCOUNT)
    List<Account> getAllAccount();

    @Insert
    void insertAccount(Account account);

    @Delete
    void deleteAccount(Account account);

}
