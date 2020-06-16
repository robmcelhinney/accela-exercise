package com.robert.accela;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


public class CreateTables {
   
    public static void createNewPersonTable() {  
        // SQLite connection string  
        String url = "jdbc:sqlite:accela/src/main/resources/db/employees.db";
          
        String sql = "CREATE TABLE IF NOT EXISTS PERSONS " +
        "(ID INT PRIMARY KEY     NOT NULL," +
        " FIRSTNAME      TEXT    NOT NULL, " + 
        " LASTNAME       TEXT    NOT NULL)"; 
          
        createNewTable(url, sql);
    }  
   
    public static void createNewAddressTable() {  
        // SQLite connection string  
        String url = "jdbc:sqlite:accela/src/main/resources/db/employees.db";
          
        String sql = "CREATE TABLE IF NOT EXISTS ADDRESSES " +
                "(ID INT PRIMARY KEY     NOT NULL," +
                " PERSONID       INT     NOT NULL, " + 
                " STREET        CHAR(50), " + 
                " CITY          CHAR(50), " + 
                " STATE         CHAR(50), " + 
                " POSTALCODE    CHAR(50))"; 
          
        createNewTable(url, sql);
    }  

    
    public static void createNewTable(String url, String sql) {
        try{  
            Connection conn = DriverManager.getConnection(url);  
            Statement stmt = conn.createStatement();  
            stmt.execute(sql);
            stmt.close();
            conn.close();
        } catch (SQLException e) {  
            System.out.println(e.getMessage());  
        }  
    }
   
    /** 
     * @param args the command line arguments 
     */  
    public static void main(String[] args) {  
        createNewPersonTable();
        createNewAddressTable();
    }  
   
}  