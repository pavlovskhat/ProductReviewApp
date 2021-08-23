import java.sql.*;
import java.util.Scanner;

// Login class for user authentication, if user is not registered he will not be able to implement CRUD
// on review class.
public class Login {

    // Login method initializing with established connection to external DB.
    public int programLogin(Connection connection) {

        // Initializing scanner and user id as blank 0 value.
        Scanner userInput = new Scanner(System.in).useDelimiter("\n");
        int userId = 0;

        // Try catch block to defend against SQL exceptions.
        try {

            // Create a direct line to the Database for running our queries
            Statement statement = connection.createStatement();
            ResultSet results;  // Initializing ResultSet as variable.

            while (true) {

                // Checking if it is a new or existing user.
                System.out.print("\nDo you have an existing account (y/n)? ");
                String loginOption = userInput.next();

                // If existing user prompt to enter user details which is then verified with DB.
                if (loginOption.equalsIgnoreCase("y")) {

                    boolean login = false;

                    // While loop block for re-entering if incorrect.
                    while (true) {

                        // User input / output to get username & password.
                        System.out.print("\nEnter username: ");
                        String username = userInput.next();

                        System.out.print("Enter password: ");
                        String password = userInput.next();

                        // Calling DB login table data.
                        results = statement.executeQuery("Select * from login");

                        // While loop to compare input with DB.
                        while (results.next()) {

                            // If input finds a match loop will break and login is complete.
                            if (username.equals(results.getString(2)) &&
                                    password.equals(results.getString(3))) {

                                // Finding unique user id no for return from DB.
                                results = statement.executeQuery("select user_id from login where username = '" +
                                        username + "'");
                                results.next();  // Moving iterator into row for finding correct index.
                                userId = results.getInt(1);

                                login = true;
                                break;

                            } else {
                                System.out.print("Incorrect username or password.");
                            }
                        }

                        if (login) {
                            break;
                        }
                    }
                    break;

                } else if (loginOption.equalsIgnoreCase("n")) {

                    String username = null;
                    String password = null;
                    boolean uniqueUser = false;

                    while (true) {
                        System.out.print("Enter username: ");
                        username = userInput.next();

                        results = statement.executeQuery("Select * from login");

                        while (results.next()) {

                            if (username.equals(results.getString(2))) {
                                System.out.println("That username is in use, please use another username.");
                                break;

                            } else {
                                uniqueUser = true;
                                break;
                            }
                        }

                        if (uniqueUser) {
                            break;
                        }
                    }

                    while (true) {

                        System.out.print("Enter password: ");
                        password = userInput.next();

                        System.out.print("Re-enter password: ");
                        String passwordVer = userInput.next();

                        if (password.equals(passwordVer)) {
                            break;

                        } else {
                            System.out.println("You did not enter the same password, please try again.");
                        }
                    }

                    // Updating DB with new user details.
                    statement.executeUpdate("insert into login (username, password) values ('" +
                            username + "', '" + password + "')");

                    // Finding unique user id no for return from DB.
                    results = statement.executeQuery("select user_id from login where username = '" +
                            username + "'");
                    results.next();
                    userId = results.getInt(1);

                    break;

                } else {
                    System.out.println("You have not entered a valid input.");
                }
            }

        } catch(SQLException error) {
            error.printStackTrace();
        }

        // Unique user id returned to main method.
        return userId;
    }
}
