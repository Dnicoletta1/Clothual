package com.example.clothual.Util;

public class Query {

    //Query User
    public static final String SELECT_ALL_USER = "SELECT * FROM User";

    //Query Account
    public static final String SELECT_ALL_ACCOUNT = "SELECT * FROM Account";

    public static final String GET_ID = "SELECT id FROM Account WHERE username LIKE :username";

    public static final String GET_PASSWORD = "SELECT password FROM Account WHERE id LIKE :idAccount";

    public static final String GET_EMAIL = "SELECT email FROM Account WHERE id LIKE :idAccount";

    public static final String GET_USERNAME = "SELECT username FROM Account WHERE id LIKE :idAccount";

    //Query Image
    public static final String SELECT_ALL_IMAGE = "SELECT * FROM Image";
}
