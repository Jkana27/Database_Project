package com.company;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

import static java.lang.System.out;

public class RestaurantLocation {

    public static void insertRestaurantLocation(Scanner scan) {
        out.println("Please enter Restaurant Id" +
                "\n PLEASE MAKE SURE THE LOCATION ID IS VALID!");
        int restaurantID = scan.nextInt();
        out.println("Enter the name");
        String name = scan.next();
        out.println("Enter the address");
        String address = scan.next();
        out.println("Enter the type of Food");
        String foodType = scan.next();
        out.println("Enter the Price for the food ($, $$, $$$, or $$$$)");
        String priceIndicator = scan.next();

        addRestaurantLocation(restaurantID, name, address, foodType, priceIndicator);
    }

    public static void updateRestaurantLocation(Scanner scan){
        out.println("Please enter old Restaurant Id" +
                "\n PLEASE MAKE SURE THE LOCATION ID IS VALID!");
        int id = scan.nextInt();
        out.println("Please enter new Restaurant Id");
        int restaurantID = scan.nextInt();
        out.println("Enter the name");
        String name = scan.next();
        out.println("Enter the address");
        String address = scan.next();
        out.println("Enter the type of Food");
        String foodType = scan.next();
        out.println("Enter the Price for the food ($, $$, $$$, or $$$$)");
        String priceIndicator = scan.next();

        update(id, restaurantID, name, address, foodType, priceIndicator);
    }

    public static void deleteRestaurantLocation(Scanner scan){
        out.println("Please enter Restaurant Id" +
                "\n PLEASE MAKE SURE THE LOCATION ID IS VALID!");
        int restaurantID = scan.nextInt();

        delete(restaurantID);
    }

    private static void addRestaurantLocation(int restaurantID, String name, String address, String foodType, String priceIndicator) {
        //add to Location database
        Location.addLocation(restaurantID, name, address);

        PreparedStatement ps = null;
        Connection conn = null;

        try {
            String sql = "INSERT INTO RESTAURANT_LOCATION (RESTAURANT_ID, FOOD_TYPE, PRICE_INDICATOR) VALUES (?, ?, ?);";
            conn = DataBaseConnection.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, restaurantID);
            ps.setString(2, foodType);
            ps.setString(3, priceIndicator);

            int rows = ps.executeUpdate();

            if (rows > 0 ){
                out.println("A new Restaurant has been created.");
            }
        } catch (SQLException ex) {
            Util.insertError("Restaurant", ex);
        } catch (Exception ex) {
            out.println(ex.getMessage());
        } finally {
            Util.closeConnection(null, ps, conn);
        }

    }

    private static void delete(int lID) {
        PreparedStatement ps = null;
        Connection conn = null;

        try {
            String sql = "DELETE FROM RESTAURANT_LOCATION WHERE RESTAURANT_ID = ?;";
            conn = DataBaseConnection.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, lID);

            int rows = ps.executeUpdate();

            if (rows > 0) {
                out.println("A Restaurant has been deleted");
            }
        } catch (SQLException ex) {
            Util.deleteError("Restaurant", ex);
        } catch (Exception ex) {
            out.println(ex.getMessage());
        } finally {
            Util.closeConnection(null, ps, conn);
        }

        //delete location entry from database
        Location.deleteLocation(lID);
    }

    private static void update(int id, int lID, String name, String address, String foodType, String priceIndicator) {
        Location.updateLocation(id, lID, name, address);

        PreparedStatement ps = null;
        Connection conn = null;

        try {
            String sql = "UPDATE RESTAURANT_LOCATION SET FOOD_TYPE = ?, PRICE_INDICATOR = ? WHERE RESTAURANT_ID = ?;";
            conn = DataBaseConnection.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, foodType);
            ps.setString(2, priceIndicator);
            ps.setInt(3, lID);

            int rows = ps.executeUpdate();

            if (rows > 0) {
                out.println("A Restaurant has been updated");
            }
        } catch (SQLException ex) {
            Util.updateError("Restaurant", ex);
        } catch (Exception ex) {
            out.println(ex.getMessage());
        } finally {
            Util.closeConnection(null, ps, conn);
        }
    }
}
