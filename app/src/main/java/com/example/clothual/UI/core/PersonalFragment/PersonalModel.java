package com.example.clothual.UI.core.PersonalFragment;

import android.app.Application;

import com.example.clothual.Model.Account;
import com.example.clothual.Model.User;
import com.example.clothual.Database.AccountDao;
import com.example.clothual.Database.RoomDatabase;
import com.example.clothual.Database.UserDao;

import java.util.List;

public class PersonalModel {

    public Application application;
    public RoomDatabase database;
    private final UserDao userDao;
    private final AccountDao accountDao;

    public PersonalModel(Application application) {
        this.application = application;
        //database = Room.databaseBuilder(application,
        //      RoomDatabase.class, DATABASE_NAME).build();
        database = RoomDatabase.getDatabase(application);
        userDao = database.daoUser();
        accountDao = database.daoAccount();
    }

    public String getName(String username){
        List<Account> account = accountDao.getAllAccount();
        List<User> user = userDao.getAllUser();

        for(int i = 0; i <account.size(); i++){
            if(account.get(i).getUsername().equals(username)){
                int id = account.get(i).getId();
                for(int j = 0; j < user.size(); j++){
                    if(user.get(j).getIdAccount() == id){
                        return user.get(j).getName() + " " + user.get(j).getSurname();
                    }
                }
            }
        }
        return null;
    }

    /*public String getSurname(String username){
        List<Account> account = accountDao.getAllAccount();
        List<User> user = userDao.getAllUser();

        for(int i = 0; i <account.size(); i++){
            if(account.get(i).getUsername().equals(username)){
                int id = account.get(i).getId();
                for(int j = 0; j < user.size(); j++){
                    if(user.get(j).getIdAccount() == id){
                        return user.get(j).getSurname();
                    }
                }
            }
        }
        return null;
    }

     */

}
