package com.company;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

import static java.lang.System.out;

public class Comment {

    public static void insertComment(Scanner scan) {
        out.println("Please enter Comment's Id" +
                "\n PLEASE MAKE SURE THE COMMENT ID IS VALID!");
        int cid = scan.nextInt();
        out.println("Enter the content of the comment");
        String text = scan.next();
        out.println("Enter the Id of the review for the comment");
        int rid = scan.nextInt();
        out.println("Enter the Id for the person who wrote the comment");
        int pid = scan.nextInt();

        addComment(cid, text, rid, pid);
    }

    public static void updateComment(Scanner scan){
        out.println("Please enter the old Comment Id" + 
            "\n PLEASE MAKE SURE THE COMMENT ID IS VALID");
        int id = scan.nextInt();
        out.println("Please enter the new Comment Id");
        int cid = scan.nextInt();
        out.println("Enter the content of the comment");
        String text = scan.next();
        out.println("Enter the Id of the review for the comment");
        int rid = scan.nextInt();
        out.println("Enter the Id for the person who wrote the comment");
        int pid = scan.nextInt();

        update(id, cid, text, rid, pid);
    }

    public static void deleteComment(Scanner scan){
        out.println("Please enter the Comment Id" + 
            "\n PLEASE MAKE SURE THE COMMENT ID IS VALID");
        int cid = scan.nextInt();

        delete(cid);
    }

    /**
     * Adds Comment to Database
     * 
     * @param commentID the Comment ID
     * @param text the written text for the Comment
     * @param reviewID the Review ID for the Comment
     * @param personID the ID of the person who wrote the Comment
     */
    private static void addComment(int commentID, String text, int reviewID, int personID) {
        PreparedStatement ps = null;
        Connection conn = null;

        try {
            String sql = "INSERT INTO COMMENT ( COMMENT_ID, TEXT, REVIEW_ID, PERSON_ID) VALUES (?, ?, ?, ?);";
            conn = DataBaseConnection.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, commentID);
            ps.setString(2, text);
            ps.setInt(3, reviewID);
            ps.setInt(4, personID);

            int rows = ps.executeUpdate();

            if (rows > 0 ){
                out.println("A new Comment has been created.");
            }
        } catch (SQLException ex) {
            Util.insertError("Comment", ex);
        } catch (Exception ex) {
            out.println(ex.getMessage());
        } finally {
            Util.closeConnection(null, ps, conn);
        }
    }

    /**
     * Updates Comment in Database
     * 
     * @param cID the ID for the Comment to update
     * @param text the text for the Comment
     * @param rID the ID for the Review on which the Comment was made
     * @param pID the ID of the Person who wrote the Comment
     */
    private static void update(int id, int cID, String text, int rID, int pID) {
        PreparedStatement ps = null;
        Connection conn = null;

        try {
            String sql = "UPDATE COMMENT SET COMMENT_ID = ?, TEXT = ?, REVIEW_ID = ?, PERSON_ID = ? WHERE COMMENT_ID = ?;";
            conn = DataBaseConnection.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, cID);
            ps.setString(2, text);
            ps.setInt(3, rID);
            ps.setInt(4, pID);
            ps.setInt(5, id);
            
            int rows = ps.executeUpdate();

            if (rows > 0) {
                out.println("Comment has been updated");
            }
        } catch (SQLException ex) {
            Util.insertError("Comment", ex);
        } catch (Exception ex) {
            out.println(ex.getMessage());
        } finally {
            Util.closeConnection(null, ps, conn);
        }
    }

    /**
     * Deletes a Comment from the Database
     * 
     * @param cID the ID of the Comment to delete
     */
    public static void delete(int cID) {
        PreparedStatement ps = null;
        Connection conn = null;

        try {
            String sql = "DELETE FROM COMMENT WHERE COMMENT_ID = ?;";
            conn = DataBaseConnection.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, cID);

            int rows = ps.executeUpdate();

            if (rows > 0) {
                out.println("Comment has been deleted");
            }
        } catch (SQLException ex) {
            Util.deleteError("Comment", ex);
        } catch (Exception ex) {
            out.println(ex.getMessage());
        } finally {
            Util.closeConnection(null, ps, conn);
        }
    }
}
