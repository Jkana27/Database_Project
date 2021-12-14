package com.company;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Time;
import java.util.Scanner;

import static java.lang.System.out;

public class TheaterShow {
    

    public static void insertTheaterShow(Scanner scan) {
        
        out.println("Please enter Theater's Show Id" +
                "\n PLEASE MAKE SURE THE THEATER ID IS VALID!");
        int theaterID = scan.nextInt();
        out.println("Enter the name of the show");
        String showTitle = scan.next();
        out.println("Enter the date of the Show (Format: yyyy-mm-dd)");
        Date date = Date.valueOf(scan.next());
        out.println("Enter the old time of the Show (Format: hh:mm:ss)");
        Time time = Time.valueOf(scan.next());
        

        addTheaterShow(theaterID, showTitle, date, time);
    }

    public static void updateTheater(Scanner scan){
        
        out.println("Please enter the old Theater Id" +
                "\n PLEASE MAKE SURE THE THEATER ID IS VALID!");
        int id = scan.nextInt();
        out.println("Enter the old title of the show");
        String title = scan.next();
        out.println("Enter the old date of the Show (Format: yyyy-mm-dd)");
        Date date = Date.valueOf(scan.next());
        out.println("Enter the old time of the Show (Format: hh:mm:ss)");
        Time time = Time.valueOf(scan.next());
        out.println("Enter the new date of the Show (Format: yyyy-mm-dd)");
        Date d = Date.valueOf(scan.next());
        out.println("Enter the old time of the Show (Format: hh:mm:ss)");
        Time t = Time.valueOf(scan.next());
        

        update(id, title, date, time, d, t);
    }

    public static void deleteTheater(Scanner scan){
        
        out.println("Please enter the Theater id of the showing to delete");
        int id = scan.nextInt();
        out.println("Please enter the Show title of the showing to delete");
        String title = scan.next();
        out.println("Please enter the Date of the showing to delete (yyyy-mm-dd)");
        Date date = Date.valueOf(scan.next());
        out.println("Please enter the Time of the showing to delete (hh:mm:ss)");
        Time time = Time.valueOf(scan.next());
        

        delete(id, title, date, time);
    }

    private static void addTheaterShow(int theaterID, String showTitle, Date date, Time time) {
        PreparedStatement ps = null;
        Connection conn = null;

        try {
            String sql = "INSERT INTO THEATER_SHOW ( THEATER_ID, SHOW_TITLE, DATE, TIME) VALUES (?, ?, ?, ?)";
            conn = DataBaseConnection.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, theaterID);
            ps.setString(2, showTitle);
            ps.setDate(3, date);
            ps.setTime(4, time);

            int rows = ps.executeUpdate();

            if (rows > 0 ){
                out.println("A new Theater Show has been created.");
            }
        } catch (SQLException ex) {
            Util.insertError("Theater Show", ex);
        } catch (Exception ex) {
            out.println(ex.getMessage());
        } finally {
            Util.closeConnection(null, ps, conn);
        }
    }

    private static void delete(int lID, String title, Date date, Time time) {
        String sql = "DELETE FROM THEATER_SHOW WHERE THEATER_ID = ? AND SHOW_TITLE = ? AND DATE = ? AND TIME = ?;";
        PreparedStatement ps = null;
        Connection conn = null;

        try {
            conn = DataBaseConnection.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, lID);
            ps.setString(2, title);
            ps.setDate(3, date);
            ps.setTime(4, time);

            int rows = ps.executeUpdate();

            if (rows > 0) {
                out.println("A Theater Show has been deleted.");
            }
        } catch (SQLException ex) {
            Util.deleteError("Theater Show", ex);
        } catch (Exception e) {
            out.println(e.getMessage());
        } finally {
            Util.closeConnection(null, ps, conn);
        }
    }

    private static void update(int id, String title, Date date, Time time, Date d, Time t) {
        String sql = "UPDATE THEATER_SHOW SET DATE = ?, TIME = ? WHERE THEATER_ID = ? AND SHOW_TITLE = ? AND DATE = ? AND TIME = ?;";
        PreparedStatement ps = null;
        Connection conn = null;

        try {
            conn = DataBaseConnection.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setDate(1, d);
            ps.setTime(2, t);
            ps.setInt(3, id);
            ps.setString(4, title);
            ps.setDate(5, d);
            ps.setTime(6, t);

            int rows = ps.executeUpdate();

            if (rows > 0) {
                out.println("A Theater Show has been updated.");
            }
        } catch (SQLException ex) {
            Util.updateError("Theater Show", ex);
        } catch (Exception e) {
            out.println(e.getMessage());
        } finally {
            Util.closeConnection(null, ps, conn);
        }
    }
}
