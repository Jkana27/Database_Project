package com.company;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

import static java.lang.System.out;

public class Theater {
    

    public static void insertTheater(Scanner scan) {
        
        out.println("Please enter Location's Id" +
                "\n PLEASE MAKE SURE THE LOCATION ID IS VALID!");
        int theaterID = scan.nextInt();
        out.println("Enter the name");
        String name = scan.next();
        out.println("Enter the address");
        String address = scan.next();
        out.println("Enter the Price of the Theater");
        String priceIndicator = scan.next();
        

        addTheater(theaterID, name, address, priceIndicator);
    }

    public static void updateTheater(Scanner scan){
        
        out.println("Please enter old Location Id" +
                "\n PLEASE MAKE SURE THE LOCATION ID IS VALID!");
        int id = scan.nextInt();
        out.println("Please enter new Location Id");
        int lID = scan.nextInt();
        out.println("Enter the name");
        String name = scan.next();
        out.println("Enter the address");
        String address = scan.next();
        out.println("Enter the Price of the Theater");
        String priceIndicator = scan.next();
        

        update(id, lID, name, address, priceIndicator);
    }

    public static void deleteTheater(Scanner scan){
        
        out.println("Please enter Location Id for the Theater to delete" +
                "\n PLEASE MAKE SURE THE SHOP ID IS VALID!");
        int id = scan.nextInt();
        

        delete(id);
    }

    private static void addTheater(int theaterID, String name, String address, String priceIndicator) {
        //add to location table
        Location.addLocation(theaterID, name, address);

        PreparedStatement ps = null;
        Connection conn = null;

        try {
            String sql = "INSERT INTO THEATER ( THEATER_ID, PRICE_INDICATOR) VALUES (?, ?)";
            conn = DataBaseConnection.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, theaterID);
            ps.setString(2, priceIndicator);


            int rows = ps.executeUpdate();

            if (rows > 0 ){
                out.println("A new Theater has been created.");
            }
        } catch (SQLException ex) {
            Util.insertError("Theater", ex);
        } catch (Exception ex) {
            out.println(ex.getMessage());
        } finally {
            Util.closeConnection(null, ps, conn);
        }
    }

    private static void delete(int lID) {
        String sql = "DELETE FROM THEATER WHERE THEATER_ID = ?;";
        PreparedStatement ps = null;
        Connection conn = null;

        try {
            conn = DataBaseConnection.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, lID);

            int rows = ps.executeUpdate();

            if (rows > 0) {
                out.println("A Theater has been deleted.");
            }
        } catch (SQLException ex) {
            Util.deleteError("Theater", ex);
        } catch (Exception e) {
            out.println(e.getMessage());
        } finally {
            Util.closeConnection(null, ps, conn);
        }

        Location.deleteLocation(lID);
    }

    private static void update(int id, int lID, String name, String address, String priceIndicator) {
        Location.updateLocation(id, lID, name, address);
        
        String sql = "UPDATE THEATER SET PRICE_INDICATOR = ? WHERE THEATER_ID = ?;";
        PreparedStatement ps = null;
        Connection conn = null;

        try {
            conn = DataBaseConnection.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, priceIndicator);
            ps.setInt(2, lID);

            int rows = ps.executeUpdate();

            if (rows > 0) {
                out.println("A Theater has been updated.");
            }
        } catch (SQLException ex) {
            Util.updateError("Theater", ex);
        } catch (Exception e) {
            out.println(e.getMessage());
        } finally {
            Util.closeConnection(null, ps, conn);
        }
    }

}
