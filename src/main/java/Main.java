import java.sql.Connection;

public class Main implements Credentials {

    // Main method.
    public static void main(String[] args) {

        // Initializing relevant classes.
        Login login = new Login();
        Review review = new Review();
        Menu menu = new Menu();
        Database database = new Database();

        // Initializing database and establishing connection.
        Connection connection = database.initializeDb(url, username, password);

        // Running login method and returning user id from database.
        int userId = login.programLogin(connection);

        // Welcome message.
        System.out.println("""
                \nWelcome to the adidas store review app. Please select one of the following
                menu options.""");

        // Switch cases for menu options.
        label:
        while (true) {

            // Running menu method and returning user input choice.
            String choice = menu.reviewMenu();

            // Different methods called based on menu option.
            switch (choice) {
                case "1":
                    review.createReview(userId, connection);

                    break;
                case "2":
                    review.getReviews(userId, connection);

                    break;
                case "3":

                    review.getReviewStats(connection);

                case "4":
                    break label;  // Exit option to end switch loop and exit program.
            }
        }
    }
}
