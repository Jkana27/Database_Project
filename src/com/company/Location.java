package com.company;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static java.lang.System.out;

public class Location {

    /**
     * Updates Location in Database
     * 
     * @param lID the ID for the Location to update
     * @param name the name of the Location
     * @param address the address of the Location
     */
    public static void updateLocation(int id, int lID, String name, String address) {
        PreparedStatement ps = null;
        Connection conn = null;

        try {
            String sql = "UPDATE LOCATION SET LOCATION_ID = ?, NAME = ?, ADDRESS = ? WHERE LOCATION_ID = ?;";
            conn = DataBaseConnection.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, lID);
            ps.setString(2, name);
            ps.setString(3, address);
            ps.setInt(4, id);

            int rows = ps.executeUpdate();

            if (rows > 0) {
                out.println("Location was updated");
            }
        } catch (SQLException ex) {
            Util.updateError("Location", ex);
        } catch (Exception ex) {
            out.println(ex.getMessage());
        } finally {
            Util.closeConnection(null, ps, conn);
        }
    }

    /**
     * Deletes Location from Database
     * 
     * @param lID the ID for the Location to delete
     */
    public static void deleteLocation(int lID) {
        PreparedStatement ps = null;
        Connection conn = null;

        try {
            String sql = "DELETE FROM LOCATION WHERE LOCATION_ID = ?;";
            conn = DataBaseConnection.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, lID);

            int rows = ps.executeUpdate();

            if (rows > 0) {
                out.println("Location deleted from Database");
            }
        } catch (SQLException ex) {
            Util.deleteError("Location", ex);   
        } catch (Exception ex) {
            out.println(ex.getMessage());
        } finally {
            Util.closeConnection(null, ps, conn);
        }
    }

    public static void addLocation(int locationID, String name, String address) {
        PreparedStatement ps = null;
        Connection conn = null;

        try{ 
            String sql = "INSERT INTO LOCATION ( LOCATION_ID, NAME, ADDRESS) VALUES (?, ?, ?)";
            conn = DataBaseConnection.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, locationID);
            ps.setString(2, name);
            ps.setString(3, address);

            int rows = ps.executeUpdate();

            if (rows > 0 ){
                out.println("A new Location has been created.");
            }
        } catch (SQLException ex) {
            Util.insertError("Location", ex);
        } catch (Exception ex) {
            out.println(ex.getMessage());
        } finally {
            Util.closeConnection(null, ps, conn);
        }
    }

}
