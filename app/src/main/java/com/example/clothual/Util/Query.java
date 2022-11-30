package com.example.clothual.Util;

public class Query {

    //Query User
    public static final String SELECT_ALL_USER = "SELECT * FROM User";

    //Query Account
    public static final String SELECT_ALL_ACCOUNT = "SELECT * FROM Account";

    public static final String GET_ID = "SELECT id FROM Account WHERE username LIKE :username";
}
