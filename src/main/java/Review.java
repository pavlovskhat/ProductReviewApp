import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Review {

    // Method to create reviews.
    public void createReview(int userId, Connection connection) {

        // Initializing relevant classes and product object.
        Scanner userInput = new Scanner(System.in).useDelimiter("\n");
        Search findProduct = new Search();
        Product product;

        // Loop block for re-search with new keywords.
        while (true) {

            // User input / output to get keyword.
            System.out.print("\nPlease enter a keyword to search product to review: ");
            String keyword = userInput.next();

            // Running method to find product and returning as object construct.
            product = findProduct.searchProducts(keyword, connection);

            // Output toString method of object for verification.
            System.out.print(product.toString());

            // Input / Output for user verification of product.
            System.out.print("\nPlease confirm that this is the correct product (y/n): ");
            String productVer = userInput.next();

            // Loop break if user found product to review.
            if (productVer.equalsIgnoreCase("y")) {
                break;
            }
        }

        // Get method to return product id.
        String productId = product.getProductId();

        // Input / Output to get review and score from user.
        System.out.print("Please write your review here: ");
        String productReview = userInput.next();

        System.out.print("Please rate the product out of 10: ");
        int productScore = userInput.nextInt();

        // Adding review data to database.
        try {

            Statement statement = connection.createStatement();

            statement.executeUpdate("insert into review values ('" +
                    userId + "', '" + productId + "', '" + productReview + "', '" + productScore + "')");

        } catch (SQLException error) {
            error.printStackTrace();
        }
    }

    // Method to view all the current users reviews.
    public void getReviews(int userId, Connection connection) {

        try {

            // Connection for sql statement generation.
            Statement statement = connection.createStatement();

            // Getting all review data from database relevant to current user id.
            ResultSet results = statement.executeQuery("select * from review where user_id = '" +
                    userId + "'");

            // Output of all review data to screen.
            while (results.next()) {

                String productId = results.getString(2);
                System.out.print("\nProduct ID: " + productId);

                String userReview = results.getString(3);
                System.out.print("\nProduct Review:\n" + userReview);

                String productScore = results.getString(4);
                System.out.print("\nProduct Score: " + productScore + "/10");
            }

        } catch (SQLException error) {
            error.printStackTrace();
        }
    }

    // Method to get the review statistics per product.
    public void getReviewStats(Connection connection) {

        // Initializing relevant classes.
        Scanner userInput = new Scanner(System.in).useDelimiter("\n");
        Search findProduct = new Search();

        // User input / output to get keyword to search for product.
        System.out.print("\nPlease enter a keyword to search product: ");
        String keyword = userInput.next();

        // Running search method using keyword and returning product construct.
        Product product = findProduct.searchProducts(keyword, connection);

        // Getting relevant product info from construct get methods.
        String productId = product.getProductId();
        String modelNumber = product.getModelNumber();
        String productName = product.getProductName();

        // Initializing review counter and total review score integers.
        int reviewCounter = 0;
        int totalScore = 0;

        try {

            // Connection for sql statement generation.
            Statement statement = connection.createStatement();

            // Getting all review data relevant to the specified product via product id.
            ResultSet results = statement.executeQuery("select * from review where prod_id = '" +
                    productId + "'");

            // Iterating over each review updating review counter and total integers.
            while (results.next()) {

                // Isolating score on each iteration.
                int productScore = results.getInt(4);

                totalScore += productScore;
                reviewCounter += 1;
            }

        } catch (SQLException error) {
            error.printStackTrace();
        }

        // If product has review data on record.
        if (reviewCounter != 0) {

            // Equation to calculate average score.
            int averageScore = (Math.round(totalScore / reviewCounter));

            // Output of statistic info to screen.
            System.out.println("\nProduct ID: " + productName);
            System.out.println("Model Number: " + modelNumber);
            System.out.println("Product Name: " + productName);
            System.out.println("Average Score: " + averageScore + "/10");
            System.out.println("Total Reviews: " + reviewCounter);

        } else {  // If no review data on record.

            // Output message to screen.
            System.out.println("\nProduct ID: " + productName);
            System.out.println("Model Number: " + modelNumber);
            System.out.println("Product Name: " + productName);
            System.out.println("There are no reviews on this product yet.");
        }
    }
}
