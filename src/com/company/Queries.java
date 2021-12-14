package com.company;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;

import static java.lang.System.out;

public class Queries {
    public static void listAllExpensiveLocations() {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            conn = DataBaseConnection.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery("(SELECT LOCATION.NAME, PRICE_INDICATOR FROM LOCATION INNER JOIN THEATER ON LOCATION.LOCATION_ID = THEATER.THEATER_ID WHERE PRICE_INDICATOR = '$$$$') UNION (SELECT LOCATION.NAME, PRICE_INDICATOR FROM LOCATION INNER JOIN RESTAURANT_LOCATION ON LOCATION.LOCATION_ID = RESTAURANT_LOCATION.RESTAURANT_ID WHERE PRICE_INDICATOR = '$$$$');");
            out.format("%-40s\t%s", "NAME", "PRICE\n");
            while(rs.next()) {
                out.format("%-40s\t%s\n", rs.getString(1), rs.getString(2));
            }
            out.println();
        } catch (SQLException ex) {
            out.println("Error obtaining all expensive locations: " + ex.getMessage());
        } catch (Exception ex) {
            ex.printStackTrace();
            out.println(ex.getMessage());
        } finally {
            Util.closeConnection(rs,stmt,conn);
        }
    }

    public static void listAllShowsAndTimes() {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            conn = DataBaseConnection.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT LOCATION.NAME, THEATER_SHOW.SHOW_TITLE, THEATER_SHOW.DATE, THEATER_SHOW.TIME FROM LOCATION INNER JOIN THEATER_SHOW ON LOCATION.LOCATION_ID = THEATER_SHOW.THEATER_ID;");
            out.format("%-30s\t%-20s\t%-10s\t%s\n", "NAME", "TILE", "DATE", "TIME");
            while(rs.next()) {
                out.format("%-30s\t%-20s\t%-10s\t%s\n", rs.getString(1), rs.getString(2), rs.getDate(3).toString(), rs.getTime(4).toString());
            }
            out.println();
        } catch (SQLException ex) {
            out.println("Error listing all Showings at theaters: " + ex.getMessage());
        } catch (Exception ex) {
            out.println(ex.getMessage());
        } finally {
            Util.closeConnection(rs,stmt,conn);
        }
    }

    public static void listWOZTheater() {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            conn = DataBaseConnection.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT LOCATION.NAME, THEATER_SHOW.SHOW_TITLE, THEATER_SHOW.DATE, THEATER_SHOW.TIME FROM LOCATION INNER JOIN THEATER_SHOW ON LOCATION.LOCATION_ID = THEATER_SHOW.THEATER_ID WHERE SHOW_TITLE = 'Wizard of Oz';");
            out.format("%-30s\t%-20s\t%-10s\t%s\n", "NAME", "TILE", "DATE", "TIME");
            while(rs.next()) {
                out.format("%-30s\t%-20s\t%-10s\t%s\n", rs.getString(1), rs.getString(2), rs.getDate(3).toString(), rs.getTime(4).toString());
            }
            out.println();
        } catch (SQLException ex) {
            out.println("Error listing all theaters showing the Wizard of Oz: " + ex.getMessage());
        } catch (Exception ex) {
            out.println(ex.getMessage());
        } finally {
            Util.closeConnection(rs,stmt,conn);
        }
    }

    public static void displayAllToyShopLocations(){
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            conn = DataBaseConnection.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT LOCATION.NAME, SHOP.TYPE, LOCATION.ADDRESS FROM LOCATION INNER JOIN SHOP ON LOCATION.LOCATION_ID = SHOP_ID WHERE TYPE = 'Toy Store';");
            out.format("%-30s\t%-20s\t%-50s\n", "NAME", "TYPE", "ADDRESS");
            while(rs.next()) {
                out.format("%-30s\t%-20s\t%-50s\n", rs.getString(1), rs.getString(2), rs.getString(3));
            }
            out.println();
        } catch (SQLException ex) {
            out.println("Error showing all Toy Store locations: " + ex.getMessage());
        } catch (Exception ex) {
            out.println(ex.getMessage());
        } finally {
            Util.closeConnection(rs,stmt,conn);
        }
    }

    public static void listAllWozReviewers() {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            conn = DataBaseConnection.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT PERSON.NAME, REVIEW.SHOW_TITLE FROM PERSON INNER JOIN REVIEW ON REVIEW.PERSON_ID = PERSON.PERSON_ID WHERE SHOW_TITLE = 'Wizard of Oz';");
            out.format("%-30s\t%-50s\n", "NAME", "TITLE");
            while(rs.next()) {
                out.format("%-30s\t%-50s\n", rs.getString(1), rs.getString(2));
            }
            out.println();
        } catch (SQLException ex) {
            out.println("Error showing all Wizard of Oz reviews: " + ex.getMessage());
        } catch (Exception ex) {
            out.println(ex.getMessage());
        } finally {
            Util.closeConnection(rs,stmt,conn);
        }
    }

    public static void listAllReviewersComments(){
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            conn = DataBaseConnection.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT PERSON.NAME, REVIEW.SHOW_TITLE, COMMENT.TEXT FROM PERSON JOIN COMMENT ON COMMENT.PERSON_ID = PERSON.PERSON_ID JOIN REVIEW ON COMMENT.REVIEW_ID = REVIEW.REVIEW_ID WHERE SHOW_TITLE = 'Wizard of Oz';");
            out.format("%-30s\t%-50s\t%-30s\n", "NAME", "TITLE", "TEXT");
            while(rs.next()) {
                out.format("%-30s\t%-50s\t%-200s\n", rs.getString(1), rs.getString(2), rs.getString(3));
            }
            out.println();
        } catch (SQLException ex) {
            out.println("Error showing all Reviewer's comments: " + ex.getMessage());
        } catch (Exception ex) {
            out.println(ex.getMessage());
        } finally {
            Util.closeConnection(rs,stmt,conn);
        }
    }
}