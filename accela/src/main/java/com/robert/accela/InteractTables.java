package com.robert.accela;

import java.util.Dictionary;
import java.util.HashMap;
import java.util.Scanner;

import java.sql.*;

public class InteractTables {

    public static void insertPerson(Person p) {
        String sql = "INSERT INTO PERSONS (ID, FIRSTNAME, LASTNAME) " +
                "VALUES ('" + p.getId() + "','" + 
                p.getFirstName() + "','" +
                p.getLastName()  + "')";
        interactTable("INSERT", sql);
    }

    public static void deletePerson(Integer id) {
        String sql = "DELETE FROM PERSONS WHERE id=" + id + ";";
        interactTable("DELETE", sql);
    }

    public static void updatePerson(Person p) {
        String sql = "UPDATE PERSONS SET FIRSTNAME = '" + 
                p.getFirstName() 
                + "',LASTNAME=" + p.getLastName() +
        " WHERE ID=" + p.getId();
        interactTable("UPDATE", sql);
    }

    public static void selectPerson() {
        String sql = "SELECT * FROM PERSONS";
        selectTable("SELECT", sql);
    }

    public static void countPersons() {
        String sql = "SELECT Count(*) AS total FROM PERSONS";
        selectTable("COUNT", sql);
    }


    public static void interactTable(String type, String sql) {
        HashMap<String, Boolean> result = new HashMap<String, Boolean>();
        Connection conn = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            String url = "jdbc:sqlite:accela/src/main/resources/db/employees.db";
            conn = DriverManager.getConnection(url);
            conn.setAutoCommit(false);
            stmt = conn.createStatement();

            switch (type) {
                case "INSERT":
                case "UPDATE":
                case "DELETE":
                    stmt.executeUpdate(sql);
                    break;
                default:
                    System.out.println("Oops!!! Wrong Choice...");
                    result.put("issue", true);
                    break;
            }
            stmt.close();
            conn.commit();
            conn.close();
        }
        catch (Exception e) {
            System.out.println("Caught Exception...");
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }

    public static void selectTable(String type, String sql) {
        HashMap<String, Boolean> result = new HashMap<String, Boolean>();
        Connection conn = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            String url = "jdbc:sqlite:accela/src/main/resources/db/employees.db";
            conn = DriverManager.getConnection(url);
            conn.setAutoCommit(false);
            stmt = conn.createStatement();

            switch (type) {
                case "SELECT":
                    ResultSet rs1 = stmt.executeQuery(sql);
                    System.out.println("ID\t FIRSTNAME\t LASTNAME\t ");
                    while (rs1.next()) {
                        Integer id = rs1.getInt("ID");
                        String firstName = rs1.getString("FIRSTNAME");
                        String lastName = rs1.getString("LASTNAME");
                        System.out.println(id + "\t " + firstName + " \t " + lastName);
                    }
                    rs1.close();
                    break;
                case "COUNT":
                    ResultSet rs2 = stmt.executeQuery(sql);
                    Integer total = rs2.getInt("total");
                    System.out.println(total);
                    rs2.close();
                    break;
                default:
                    System.out.println("Oops!!! Wrong Choice...");
                    result.put("issue", true);
                    break;
            }
            stmt.close();
            conn.commit();
            conn.close();
        }
        catch (Exception e) {
            System.out.println("Caught Exception...");
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }
}
