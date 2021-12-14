package com.company;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Scanner;
import java.sql.SQLException;

import static java.lang.System.out;

public class Shop {
    

    public static void insertShop(Scanner scan) {
        
        out.println("Please enter Shop Id" +
                "\n PLEASE MAKE SURE THE SHOP ID IS VALID!");
        int shopID = scan.nextInt();
        out.println("Enter the name");
        String name = scan.next();
        out.println("Enter the address");
        String address = scan.next();
        out.println("Enter the type of Shop");
        String type = scan.next();
        

        addShop(shopID, name, address, type);
    }

    public static void updateShop(Scanner scan){
        
        out.println("Please enter old Shop Id" +
                "\n PLEASE MAKE SURE THE LOCATION ID IS VALID!");
        int id = scan.nextInt();
        out.println("Please enter new Shop Id");
        int lID = scan.nextInt();
        out.println("Enter the name");
        String name = scan.next();
        out.println("Enter the address");
        String address = scan.next();
        out.println("Enter the type of Shop");
        String type = scan.next();
        

        update(id, lID, name, address, type);
    }

    public static void deleteShop(Scanner scan){
        
        out.println("Please enter Shop Id of Shop to delete" +
                "\n PLEASE MAKE SURE THE LOCATION ID IS VALID!");
        int id = scan.nextInt();
        

        delete(id);
    }

    private static void addShop(int shopID, String name, String address, String type) {
        //add to location
        Location.addLocation(shopID, name, address);

        PreparedStatement ps = null;
        Connection conn = null;

        try {
            String sql = "INSERT INTO SHOP (LOCATION_ID, TYPE) VALUES (?, ?)";
            conn = DataBaseConnection.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, shopID);
            ps.setString(2, type);

            int rows = ps.executeUpdate();

            if (rows > 0 ){
                out.println("A new Shop has been created.");
            }
        } catch (SQLException ex) {
            Util.insertError("Shop", ex);
        } catch (Exception ex) {
            out.println(ex.getMessage());
        } finally {
            Util.closeConnection(null, ps, conn);
        }

    }

    private static void delete(int lID) {
        String sql = "DELETE FROM SHOP WHERE LOCATION_ID = ?;";
        PreparedStatement ps = null;
        Connection conn = null;

        try {
            conn = DataBaseConnection.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, lID);

            int rows = ps.executeUpdate();

            if (rows > 0) {
                out.println("A Shop  has been deleted.");
            }
        } catch (SQLException ex) {
            Util.deleteError("Shop", ex);
        } catch (Exception e) {
            out.println(e.getMessage());
        } finally {
            Util.closeConnection(null, ps, conn);
        }

        Location.deleteLocation(lID);
    }

    private static void update(int id, int lID, String name, String address, String type) {
        Location.updateLocation(id, lID, name, address);

        String sql = "UPDATE SHOP SET TYPE = ? WHERE SHOP_ID = ?;";
        PreparedStatement ps = null;
        Connection conn = null;

        try {
            conn = DataBaseConnection.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, type);
            ps.setInt(2, lID);

            int rows = ps.executeUpdate();

            if (rows > 0) {
                out.println("A Shop has been updated.");
            }
        } catch (SQLException ex) {
            Util.updateError("Shop", ex);
        } catch (Exception e) {
            out.println(e.getMessage());
        } finally {
            Util.closeConnection(null, ps, conn);
        }
    }

}
