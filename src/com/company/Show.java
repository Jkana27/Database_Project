package com.company;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

import static java.lang.System.out;

public class Show {
    

    public static void insertShow(Scanner scan) {
        
        out.println("Enter the title of the Show");
        String showTitle = scan.next();
        
        
        addShow(showTitle);
    }

    public static void updateShow(Scanner scan){
        
        out.println("Enter the old title of the Show");
        String title = scan.next();
        out.println("Enter the new title of the Show");
        String newTitle = scan.next();
        

        update(title, newTitle);
    }

    public static void deleteShow(Scanner scan){
        
        out.println("Enter the title of the Show to delete");
        String title = scan.next();
        

        delete(title);
    }

    private static void addShow(String showTitle) {
        PreparedStatement ps = null;
        Connection conn = null;

        try {
            String sql = "INSERT INTO SHOWS ( SHOW_TITLE) VALUES (?)";
            conn = DataBaseConnection.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, showTitle);

            int rows = ps.executeUpdate();

            if (rows > 0 ){
                out.println("A new Show has been added.");
            }
        } catch (SQLException ex) {
            Util.insertError("Shows", ex);
        } catch (Exception ex) {
            out.println(ex.getMessage());
        } finally {
            Util.closeConnection(null, ps, conn);
        }
    }

    private static void delete(String title) {
        String sql = "DELETE FROM SHOWS WHERE SHOW_TITLE = ?;";
        PreparedStatement ps = null;
        Connection conn = null;

        try {
            conn = DataBaseConnection.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, title);

            int rows = ps.executeUpdate();

            if (rows > 0) {
                out.println("A Show has been deleted.");
            }
        } catch (SQLException ex) {
            Util.deleteError("Shows", ex);
        } catch (Exception e) {
            out.println(e.getMessage());
        } finally {
            Util.closeConnection(null, ps, conn);
        }
    }

    private static void update(String title, String newTitle) {
        String sql = "UPDATE SHOWS SET SHOW_TITLE = ? WHERE SHOW_TITLE = ?;";
        PreparedStatement ps = null;
        Connection conn = null;

        try {
            conn = DataBaseConnection.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, newTitle);
            ps.setString(2, title);

            int rows = ps.executeUpdate();

            if (rows > 0) {
                out.println("A Show has been updated.");
            }
        } catch (SQLException ex) {
            Util.updateError("Shows", ex);
        } catch (Exception e) {
            out.println(e.getMessage());
        } finally {
            Util.closeConnection(null, ps, conn);
        }
    }
}
