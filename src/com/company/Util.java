package com.company;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static java.lang.System.out;

public class Util {

    /**
     * Close connection to database
     * 
     * @param r ResultSet to close
     * @param s Statement or PreparedStatement to close
     * @param c Connection to close
     */
    public static void closeConnection(ResultSet r, Statement s, Connection c) {
        try {
            if (r != null) { r.close(); }
            if (s != null) { s.close(); }
            if (c != null) { c.close(); }
        } catch (SQLException ex) {

        }
    }

    public static void insertError(String table, SQLException ex) {
        out.println("There was an error inserting into " + table + "\n" + ex.getMessage());
    }

    public static void updateError(String table, SQLException ex) {
        out.println("There was an error updating " + table + "\n" + ex.getMessage());
    }

    public static void deleteError(String table, SQLException ex) {
        out.println("There was an error deleting from " + table + "\n" + ex.getMessage());
    }

    public static void queryError(String query, SQLException ex) {
        out.println("There was an error executing " + query + "\n" + ex.getMessage());
    }
}
