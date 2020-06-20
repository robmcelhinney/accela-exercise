package com.robert.accela;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateTables {

    public static void createNewPersonTable() {
        String sql = "CREATE TABLE IF NOT EXISTS PERSONS " + "(ID INT PRIMARY KEY     NOT NULL,"
                + " FIRSTNAME      TEXT    NOT NULL, " + " LASTNAME       TEXT    NOT NULL)";
        createNewTable(CONSTANTS.URL, sql);
    }

    public static void createNewAddressTable() {
        String sql = "CREATE TABLE IF NOT EXISTS ADDRESSES " + "(ID INT PRIMARY KEY     NOT NULL,"
                + " PERSONID       INT     NOT NULL, " + " STREET        VARCHAR(50), " + " CITY          VARCHAR(50), "
                + " STATE         VARCHAR(50), " + " POSTALCODE    VARCHAR(10))";

        createNewTable(CONSTANTS.URL, sql);
    }

    public static void createNewTable(String url, String sql) {
        try {
            Connection conn = DriverManager.getConnection(url);
            Statement stmt = conn.createStatement();
            stmt.execute(sql);
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void CreateBothTables() {
        File database = new File(CONSTANTS.DB_PATH);
        if (!database.exists()) {
            createNewPersonTable();
            createNewAddressTable();
        }
    }

}