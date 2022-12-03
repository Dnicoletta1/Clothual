package com.example.clothual.UI.core.PersonalFragment;

import android.app.Application;

import com.example.clothual.Model.Account;
import com.example.clothual.database.AccountDao;
import com.example.clothual.database.RoomDatabase;
import com.example.clothual.database.UserDao;

import java.util.List;

public class ModifyModel {

    public Application application;
    public RoomDatabase database;
    private final UserDao userDao;
    private final AccountDao accountDao;

    public ModifyModel(Application application) {
        this.application = application;
        //database = Room.databaseBuilder(application,
        //      RoomDatabase.class, DATABASE_NAME).build();
        database = RoomDatabase.getDatabase(application);
        userDao = database.daoUser();
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

    public String getEmail(int id ){
        return accountDao.getEmail(id);
    }

    public String getUsername(int id ){
        return accountDao.getUsername(id);
    }

    /*
    public boolean update(String email, String username, String passwordNuova, String passwordVecchia, int id){
        if(passwordNuova.isEmpty() && passwordVecchia.isEmpty()){
            String password = accountDao.getPassword(id);
            if(email.isEmpty()){
                String newEmail = accountDao.getEmail(id);
                if(username.isEmpty()){
                    String newUsername = accountDao.getUsername(id);
                }
            }
        }

        if(checkPassword(passwordVecchia, id)){
            Account account = new Account(username, passwordNuova, email);
            accountDao.updateAccount(account);
            return true;
        }
        return false;


    }


     */
}
