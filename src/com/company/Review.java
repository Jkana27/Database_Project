package com.company;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;

import static java.lang.System.out;

public class Review {

    public static void insertReview(Scanner scan) {
        out.println("Please enter Review's Id" + "\n PLEASE MAKE SURE THE REVIEW ID IS VALID!");
        int reviewID = scan.nextInt();
        out.println("Enter the  the review");
        String text = scan.next();
        out.println("Enter the reviewer's rating (1 to 100)");
        int rating = scan.nextInt();
        out.println("Enter the person's  ID");
        int personID = scan.nextInt();
        out.println("Enter the location ID");
        int locationID = scan.nextInt();
        out.println("Is this a review for a Show (y/n)?");
        String ans = scan.next();
        String showTitle = null;
        if (ans.equals("y")) {
            out.println("Enter the name of the show");
            showTitle = scan.next();
        }

        addReview(reviewID, text, rating, personID, locationID, showTitle);
    }

    public static void updateReview(Scanner scan) {
        out.println("Enter the old review ID");
        int id = scan.nextInt();
        out.println("Enter new review ID");
        int rID = scan.nextInt();
        out.println("Enter the review text");
        String text = scan.next();
        out.println("Enter reviewer's rating (1 to 100)");
        int rating = scan.nextInt();
        out.println("Enter the reviewers's ID");
        int pID = scan.nextInt();
        out.println("Enter the location ID");
        int lID = scan.nextInt();
        out.println("Is this a review for a Show (y/n)?");
        String ans = scan.next();
        String title = null;
        if (ans.equals("y")) {
            out.println("Enter the title of the Show");
            title = scan.next();
        }

        update(id, rID, text, rating, pID, lID, title);
    }

    public static void deleteReview(Scanner scan) {
        
        out.println("Enter the id of the review to delete");
        int id = scan.nextInt();
        

        delete(id);
    }

    private static void addReview(int reviewID, String text, int rating, int personID, int locationID,
            String showTitle) {
        String sql = "INSERT INTO REVIEW (REVIEW_ID, TEXT, RATING, PERSON_ID, LOCATION_ID, SHOW_TITLE) VALUES (?, ?, ?, ?, ?, ?)";
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DataBaseConnection.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, reviewID);
            ps.setString(2, text);
            ps.setInt(3, rating);
            ps.setInt(4, personID);
            ps.setInt(5, locationID);
            if (showTitle == null) {
                ps.setNull(6, Types.VARCHAR);
            } else {
                ps.setString(6, showTitle);
            }

            int rows = ps.executeUpdate();

            if (rows > 0) {
                out.println("A new Review has been created.");
            }
        } catch (SQLException ex) {
            Util.insertError("Review", ex);
        } catch (Exception e) {
            out.println(e.getMessage());
        } finally {
            Util.closeConnection(null, ps, conn);
        }
    }

    private static void delete(int rID) {
        String sql = "DELETE FROM REVIEW WHERE REVIEW_ID = ?;";
        PreparedStatement ps = null;
        Connection conn = null;

        try {
            conn = DataBaseConnection.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, rID);

            int rows = ps.executeUpdate();

            if (rows > 0) {
                out.println("A Review has been deleted.");
            }
        } catch (SQLException ex) {
            Util.deleteError("Review", ex);
        } catch (Exception e) {
            out.println(e.getMessage());
        } finally {
            Util.closeConnection(null, ps, conn);
        }
    }

    private static void update(int id, int rID, String text, int rating, int pID, int lID, String title) {
        String sql = "UPDATE REVIEW SET REVIEW_ID = ?, TEXT = ?, RATING = ?, PERSON_ID = ?, LOCATION_ID = ?, SHOW_TITLE = ? WHERE REVIEW_ID = ?;";
        PreparedStatement ps = null;
        Connection conn = null;

        try {
            conn = DataBaseConnection.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, rID);
            ps.setString(2, text);
            ps.setInt(3, rating);
            ps.setInt(4, pID);
            ps.setInt(5, lID);
            if (title == null) {
                ps.setNull(6, Types.VARCHAR);
            } else {
                ps.setString(6, title);
            }
            ps.setInt(7, id);

            int rows = ps.executeUpdate();

            if (rows > 0) {
                out.println("A Review has been updated.");
            }
        } catch (SQLException ex) {
            Util.updateError("Review", ex);
        } catch (Exception e) {
            out.println(e.getMessage());
        } finally {
            Util.closeConnection(null, ps, conn);
        }
    }
}
