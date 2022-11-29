package com.example.clothual.Model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class User {

    @PrimaryKey(autoGenerate = true)
    private int id;
    //private int idAccount;

    private String surname;
    private String name;



    public User(String surname, String name) {
        this.surname = surname;
        this.name = name;
        //this.idAccount = this.id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

   /* public int getIdAccount() {
        return idAccount;
    }

    public void setIdAccount(int idAccount) {
        this.idAccount = idAccount;
    }

    */

    @NonNull
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
               // ", idAccount=" + idAccount +
                ", surname='" + surname + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
