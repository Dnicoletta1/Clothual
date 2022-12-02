package com.example.clothual.UI.welcome.RegistrationFragment;

import android.app.Application;

import com.example.clothual.Model.Account;
import com.example.clothual.Model.User;
import com.example.clothual.database.AccountDao;
import com.example.clothual.database.RoomDatabase;
import com.example.clothual.database.UserDao;

public class RegistrationModel {

    public Application application;
    public RoomDatabase database;
    private final UserDao userDao;
    private final AccountDao accountDao;

    public RegistrationModel(Application application) {
        this.application = application;
        //database = Room.databaseBuilder(application,
        //      RoomDatabase.class, DATABASE_NAME).build();
        database = RoomDatabase.getDatabase(application);
        userDao = database.daoUser();
        accountDao = database.daoAccount();
    }

    public void createUser(String username, String name, String surname, String passowrd, String email){
        RoomDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                Account account = new Account(username, email, passowrd);
                accountDao.insertAccount(account);
                User user = new User(surname, name, accountDao.getId(username));
                userDao.insertUser(user);
            }
        });

    }


}
