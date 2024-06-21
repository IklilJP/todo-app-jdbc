package com.enigma.gosling.db;

import java.sql.*;

public class Jdbc {
    private final static String DB_URL = "jdbc:postgresql://localhost:5432/dbToDoApp";
    private final static String USER = "postgres";
    private final static String PASS = "123456789";

    public static void showAll() {
        int i = 1;
        try {
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM todo");
            String check = "";

            while (rs.next()) {
                System.out.println("No. " + i);
                System.out.println("ID: " + rs.getString("id"));
                check = rs.getString("id");
                System.out.println("To do: " + rs.getString("todo_name"));
                System.out.println("Priority: " + rs.getString("priority"));
                System.out.println("Descripsion: " + rs.getString("descripsion"));
                System.out.println("Status: " + rs.getString("is_active"));
                i = i + 1;
                System.out.println("-".repeat(50));
            }
            if (check.isBlank() || check.isEmpty()) {
                System.out.println("Tidak ada data");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void add(String todo_name, String priority, String descripsion, String is_active) {
        try {
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmt = conn.createStatement();
            PreparedStatement add = conn.prepareStatement("INSERT INTO todo(todo_name, priority, descripsion, is_active ,create_date) VALUES (?, ?, ?, ?, now())");
            add.setString(1, todo_name);
            add.setString(2, priority);
            add.setString(3, descripsion);
            add.setString(4, is_active);
            add.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void update(String id, String todo_name, String priority, String descripsion, String is_active) {
        try {

            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmt = conn.createStatement();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM todo WHERE id = ?");
            ps.setInt(1, Integer.parseInt(id));
            ResultSet rs = stmt.executeQuery("SELECT * FROM todo WHERE id = '" + id + "'");
            String check = "";
            if (rs.next()) {
                check = rs.getString("id");
                if (todo_name.isBlank() || todo_name.isEmpty()) {
                    todo_name = rs.getString("todo_name");
                }
            }
            if (check.isBlank() || check.isEmpty()) {
                System.out.println("ID Tidak Ditemukan");
                return;
            }
            PreparedStatement update = conn.prepareStatement("UPDATE todo SET todo_name = ?, priority = ?, descripsion = ?, is_active = ?, edit_date = now() WHERE id = ?");
            update.setString(1, todo_name);
            update.setString(2, priority);
            update.setString(3, descripsion);
            update.setString(4, is_active);
            update.setInt(5, Integer.parseInt(id));
            update.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void delete(String id) {
        try {
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmt = conn.createStatement();
            String sql = "DELETE FROM todo WHERE id = '" + id + "'";
            stmt.executeUpdate(sql);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static Boolean findbyId(String id) {
        Boolean found = false;
        try {
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM todo WHERE id = '" + id + "'");
            String check = "";
            while (rs.next()) {
                check = rs.getString("id");
            }
            if (!check.isBlank() || !check.isEmpty()) {
                found = true;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return found;
    }
}
