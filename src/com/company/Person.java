package com.company;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

import static java.lang.System.out;

public class Person {

    public static void insertPerson(Scanner scan) {
        out.println("Please enter Person's Id" +
                "\n PLEASE MAKE SURE THE PERSON ID IS VALID!");
        int id = scan.nextInt();
        out.println("Enter the name of the Person");
        String name = scan.next();

        addPerson(id, name);
    }

    public static void updatePerson(Scanner scan){
        out.println("Please enter old Person Id" +
                "\n PLEASE MAKE SURE THE PERSON ID IS VALID!");
        int id = scan.nextInt();
        out.println("Please enter new Id");
        int pid = scan.nextInt();
        out.println("Enter the name of the Person");
        String name = scan.next();

        update(id, pid, name);
    }

    public static void deletePerson(Scanner scan){
        out.println("Please enter Person's Id" +
                "\n PLEASE MAKE SURE THE PERSON ID IS VALID!");
        int id = scan.nextInt();

        delete(id);
    }

    private static void addPerson(int id, String name) {
        PreparedStatement ps = null;
        Connection conn = null;

        try {
            String sql = "INSERT INTO PERSON (PERSON_ID, NAME) VALUES (?, ?)";
            conn = DataBaseConnection.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.setString(2, name);

            int rows = ps.executeUpdate();

            if (rows > 0 ){
                out.println("A new Person has been created.");
            }
        } catch (SQLException ex) {
            Util.insertError("Person", ex);
        } catch (Exception ex) {
            out.println(ex.getMessage());
        } finally {
            Util.closeConnection(null, ps, conn);
        }
    }

    private static void delete(int pID) {
        PreparedStatement ps = null;
        Connection conn = null;

        try {
            String sql = "DELETE FROM PERSON WHERE PERSON_ID = ?;";
            conn = DataBaseConnection.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, pID);

            int rows = ps.executeUpdate();

            if (rows > 0) {
                out.println("Person was deleted");
            }
        } catch (SQLException ex) {
            Util.deleteError("Person", ex);
        } catch (Exception ex) {
            out.println(ex.getMessage());
        } finally {
            Util.closeConnection(null, ps, conn);
        }
    }

    private static void update(int id, int pID, String name) {
        PreparedStatement ps = null;
        Connection conn = null;

        try {
            String sql = "UPDATE PERSON SET PERSON_ID = ?, NAME = ? WHERE PERSON_ID = ?;";
            conn = DataBaseConnection.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, pID);
            ps.setString(2, name);
            ps.setInt(3, id);

            int rows = ps.executeUpdate();

            if (rows > 0) {
                out.println("Person was updated");
            }
        } catch (SQLException ex) {
            Util.updateError("Person", ex);
        } catch (Exception ex) {
            out.println(ex.getMessage());
        } finally {
            Util.closeConnection(null, ps, conn);
        }
    }
}


