package com.company;

import java.util.Scanner;

import static java.lang.System.out;

public class Main {
    
    private static Scanner scan;

    public static void main(String[] args) {
        scan = new Scanner(System.in);
        scan.useDelimiter("\n");
        out.println("Enter database username:");
        String uID = scan.next();
        out.println("Enter database password:");
        String pID = scan.next();
        DataBaseConnection.setUserInfo(uID, pID);

        boolean session = true;
        while (session) {
            out.println("Please select an option. " + 
                "\n 1. Query Database" + 
                "\n 2. Insert" + 
                "\n 3. Update" + 
                "\n 4. Delete" +
                "\n 5. Exit"
            );
            int selection = scan.nextInt();

            switch (selection) {
                case 1:
                    queryDataBase();
                    break;
                case 2:
                    insertDataBase();
                    break;
                case 3:
                    updateDataBase();
                    break;
                case 4:
                    deleteDataBase();
                    break;
                case 5:
                    session = false;
                    break;
                default:
                    out.println("That is not a valid menu option");

            }
        }
        scan.close();
    }

    /**
     * Provides Options to Query Database
     */
    private static void queryDataBase() {
        //Show queries
        out.println("Please select a query to perform" + 
            "\n 1. List all very expensive ($$$$) Locations" + 
            "\n 2. List all shows and showtimes" +
            "\n 3. Show theaters that are showing \"The Wizard of Oz\"" +
            "\n 4. Show the Location of all Toy Shops" + 
            "\n 5. List all of the people who have reviewed \"The Wizard of Oz\"" + 
            "\n 6. List all comments for reviews of \"The Wizard of Oz\" and the commenter names"
        );
        int q = scan.nextInt();

        switch (q) {
            case 1:
                Queries.listAllExpensiveLocations();
                break;
            case 2:
                Queries.listAllShowsAndTimes();
                break;
            case 3:
                Queries.listWOZTheater();
                break;
            case 4:
                Queries.displayAllToyShopLocations();
                break;
            case 5:
                Queries.listAllWozReviewers();
                break;
            case 6:
                Queries.listAllReviewersComments();
                break;
            default:
                out.println("Invalid menu option");
        }
    }

    /**
     * Provides an interface to choose waht table to insert a new tuple into the
     * database
     *
     */
    private static void insertDataBase() {
        tableList();

        int num = scan.nextInt();

        switch (num) {
            case 1:
                Person.insertPerson(scan);
                break;
            case 2:
                RestaurantLocation.insertRestaurantLocation(scan);
                break;
            case 3:
                Shop.insertShop(scan);
                break;
            case 4:
                Theater.insertTheater(scan);
                break;
            case 5:
                Show.insertShow(scan);
                break;
            case 6:
                TheaterShow.insertTheaterShow(scan);
                break;
            case 7:
                Review.insertReview(scan);
                break;
            case 8:
                Comment.insertComment(scan);
                break;
            default:
                out.println("That is not a valid menu selection.");
        }
    }

    /**
     * Provides an interface that Allows the user to update a tuple in the database
     * by selecting which table they want to perform that action on
     */
    private static void updateDataBase() {
        tableList();

        int num = scan.nextInt();

        switch (num) {
            case 1:
                Person.updatePerson(scan);
                break;
            case 2:
                RestaurantLocation.updateRestaurantLocation(scan);
                break;
            case 3:
                Shop.updateShop(scan);
                break;
            case 4:
                Theater.updateTheater(scan);
                break;
            case 5:
                Show.updateShow(scan);
                break;
            case 6:
                TheaterShow.updateTheater(scan);
                break;
            case 7:
                Review.updateReview(scan);
                break;
            case 8:
                Comment.updateComment(scan);
                break;
            default:
                out.println("That is not a valid menu selection.");
        }
    }

    /**
     * Provides the interface that allows the user to select a table that they will
     * Delete a tuple from
     */
    private static void deleteDataBase() {

        tableList();

        int num = scan.nextInt();

        switch (num) {
            case 1:
                Person.deletePerson(scan);
                break;
            case 2:
                RestaurantLocation.deleteRestaurantLocation(scan);
                break;
            case 3:
                Shop.deleteShop(scan);
                break;
            case 4:
                Theater.deleteTheater(scan);
                break;
            case 5:
                Show.deleteShow(scan);
                break;
            case 6:
                TheaterShow.deleteTheater(scan);
                break;
            case 7:
                Review.deleteReview(scan);
                break;
            case 8:
                Comment.deleteComment(scan);
                break;
            default:
                out.println("That is not a valid menu selection.");
        }
    }

    private static void tableList() {
        out.println("Please select a table to modify" + 
            "\n 1. Person" + 
            "\n 2. Restaurant Location" + 
            "\n 3. Shop" + 
            "\n 4. Theater" + 
            "\n 5. Show" + 
            "\n 6. Theater Show" + 
            "\n 7. Review" + 
            "\n 8. Comment"
        );
    }

}
