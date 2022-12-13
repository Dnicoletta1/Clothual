package com.example.clothual.UI.core.PersonalFragment;

import static com.example.clothual.Util.Constant.CREDENTIALS_LOGIN_FILE;
import static com.example.clothual.Util.Constant.EMAIL_CHANGE;
import static com.example.clothual.Util.Constant.PASSWORD_PREFERENCE;
import static com.example.clothual.Util.Constant.USERNAME_CHANGE;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.example.clothual.Model.Account;
import com.example.clothual.Database.AccountDao;
import com.example.clothual.Database.RoomDatabase;

import org.apache.commons.validator.routines.EmailValidator;

import java.util.List;

public class ModifyModel {

    public Application application;
    public RoomDatabase database;
    private final AccountDao accountDao;

    public ModifyModel(Application application) {
        this.application = application;
        database = RoomDatabase.getDatabase(application);
        accountDao = database.daoAccount();
    }

    public boolean checkPassword(String password, int id){
        List<Account> account = accountDao.getAllAccount();
        for(int i = 0; i < account.size(); i++){
            if(account.get(i).getId() == id && account.get(i).getPassword().equals(password)){
                return true;
            }
        }
        return false;
    }

    public boolean checkEmail(String email){
        return EmailValidator.getInstance().isValid(email);
    }

    public String getEmail(int id ){
        return accountDao.getEmail(id);
    }

    public String getUsername(int id ){
        return accountDao.getUsername(id);
    }


    public void update(int id){
        SharedPreferences sharePref = application.getSharedPreferences(CREDENTIALS_LOGIN_FILE, Context.MODE_PRIVATE);
        Account account = new Account(sharePref.getString(USERNAME_CHANGE, ""), sharePref.getString(EMAIL_CHANGE, ""),
                sharePref.getString(PASSWORD_PREFERENCE, ""));
        account.setId(id);
        accountDao.updateAccount(account);
    }
}
