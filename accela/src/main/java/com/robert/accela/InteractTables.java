package com.robert.accela;

import java.util.ArrayList;
import java.util.List;
import java.sql.*;


public class InteractTables {

    public static void insertPerson(Person p) {
        String sql = "INSERT INTO PERSONS (ID, FIRSTNAME, LASTNAME) " + "VALUES ('" + p.getId() + "','"
                + p.getFirstName() + "','" + p.getLastName() + "')";
        interactTable(sql);
    }

    public static void deletePerson(Integer id) {
        String sql = "DELETE FROM PERSONS WHERE id=" + id + ";";
        interactTable(sql);
    }

    public static void updatePerson(Person p) {
        String sql = "UPDATE PERSONS SET FIRSTNAME = '" + p.getFirstName() + "',LASTNAME='" + p.getLastName()
                + "' WHERE ID=" + p.getId();
        interactTable(sql);
    }

    public static List<Person> selectPerson() {
        String sql = "SELECT PERSONS.ID as ID, "
                 + "PERSONS.FIRSTNAME as FIRSTNAME, " + "PERSONS.LASTNAME as LASTNAME, "
                + "ADDRESSES.ID as ADDRESSID, " + "ADDRESSES.STREET as STREET, " 
                + "ADDRESSES.CITY as CITY, " + "ADDRESSES.STATE as STATE, " 
                + "ADDRESSES.POSTALCODE as POSTALCODE, ADDRESSES.PERSONID as PERSONID"
                + " FROM PERSONS LEFT JOIN ADDRESSES ON PERSONS.ID = ADDRESSES.PERSONID"
                + " UNION ALL "
                + "SELECT PERSONS.ID as ID, "
                + "PERSONS.FIRSTNAME as FIRSTNAME, " + "PERSONS.LASTNAME as LASTNAME, "
                + "ADDRESSES.ID as ADDRESSID, " + "ADDRESSES.STREET as STREET, " 
                + "ADDRESSES.CITY as CITY, " + "ADDRESSES.STATE as STATE, " 
                + "ADDRESSES.POSTALCODE as POSTALCODE, ADDRESSES.PERSONID as PERSONID"
                + " FROM ADDRESSES LEFT JOIN PERSONS ON PERSONS.ID = ADDRESSES.PERSONID"
                + " WHERE ADDRESSES.ID IS NULL";
        List<Person> persons = selectTable(sql);
        return persons;
    }

    public static Integer countPersons() {
        String sql = "SELECT Count(*) AS total FROM PERSONS";
        return countTable(sql);
    }

    public static Boolean checkPersonExists(Integer id) {
        String sql = "SELECT Count(*) AS total FROM PERSONS WHERE id=" + id + ";";
        return countTable(sql) != 0;
    }
    public static Boolean checkAddressExists(Integer id) {
        String sql = "SELECT Count(*) AS total FROM ADDRESSES WHERE id=" + id + ";";
        return countTable(sql) != 0;
    }

    public static void insertAddress(Address address) {
        String sql = "INSERT INTO ADDRESSES (ID, PERSONID, STREET, CITY, STATE, POSTALCODE) " + "VALUES ('"
                + address.getId() + "','" + address.getPersonId() + "','" + address.getStreet() + "','"
                + address.getCity() + "','" + address.getState() + "','" + address.getPostalCode() + "')";
        interactTable(sql);
    }

    public static void updateAddress(Address a) {
        String sql = "UPDATE ADDRESSES SET STREET = '" + a.getStreet()
                + "',CITY='" + a.getCity() + "',STATE='" + a.getState()
                + "',POSTALCODE='" + a.getPostalCode() + "',PERSONID='" + a.getPersonId()
                + "' WHERE ID=" + a.getId();
        interactTable(sql);
    }

    public static void deleteAddress(Integer id) {
        String sql = "DELETE FROM ADDRESSES WHERE id=" + id + ";";
        interactTable(sql);
    }

    public static void interactTable(String sql) {
        Connection conn = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection(CONSTANTS.URL);
            conn.setAutoCommit(false);
            stmt = conn.createStatement();
            stmt.executeUpdate(sql);
            stmt.close();
            conn.commit();
        } catch (Exception e) {
            System.out.println("Caught Exception...: " + e);
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    /* ignore */}
            }
        }
    }

    public static List<Person> selectTable(String sql) {
        List<Person> persons = new ArrayList<Person>();
        Connection conn = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection(CONSTANTS.URL);
            conn.setAutoCommit(false);
            stmt = conn.createStatement();

            ResultSet rs1 = stmt.executeQuery(sql);
            while (rs1.next()) {
                Person person = new Person();
                Address address = new Address();
                person.setId(rs1.getInt("ID"));
                person.setFirstName(rs1.getString("FIRSTNAME"));
                person.setLastName(rs1.getString("LASTNAME"));
                address.setId(rs1.getInt("ADDRESSID"));
                address.setStreet(rs1.getString("STREET"));
                address.setCity(rs1.getString("CITY"));
                address.setState(rs1.getString("STATE"));
                address.setPostalCode(rs1.getString("POSTALCODE"));
                address.setPersonId(rs1.getInt("PERSONID"));
                person.setAddress(address);
                persons.add(person);
            }
            rs1.close();
            stmt.close();
            conn.commit();
        } catch (Exception e) {
            System.out.println("Caught Exception...");
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    /* ignore */}
            }
        }
        return persons;
    }

    public static Integer countTable(String sql) {
        Integer total = 0;
        Connection conn = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection(CONSTANTS.URL);
            conn.setAutoCommit(false);
            stmt = conn.createStatement();
            ResultSet rs2 = stmt.executeQuery(sql);
            total = rs2.getInt("total");
            rs2.close();
            stmt.close();
            conn.commit();
        } catch (Exception e) {
            System.out.println("Caught Exception...");
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    /* ignore */}
            }
        }
        return total;
    }
}
