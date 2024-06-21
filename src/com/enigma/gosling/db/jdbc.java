package com.enigma.gosling.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class jdbc {
    private final static String DB_URL = "jdbc:postgresql://localhost:5432/dbToDoApp";
    private final static String USER = "postgres";
    private final static String PASS = "123456789";

    public static Connection showAll(){
        try {
            String sql = "SELECT * FROM tasks";

            return java.sql.DriverManager.getConnection(DB_URL, USER, PASS);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
