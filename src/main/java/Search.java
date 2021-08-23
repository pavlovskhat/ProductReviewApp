import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Search {

    // Method to search database using keywords.
    public Product searchProducts(String keyword, Connection connection) {

        // Initializing keyString String and project object.
        String keyString = null;
        Product product = null;

        try {

            // Initializing sql statement generation.
            Statement statement = connection.createStatement();

            // Selecting all product info from database.
            ResultSet results = statement.executeQuery("select * from products");

            // Setting boolean for loop exit.
            boolean foundProduct = false;

            // Iterate over products to check for keyword match.
            while (results.next()) {

                String keywords = results.getString(6);
                String[] keyWordsSplit = keywords.split(" ");
                for (int i = 0; i < keyWordsSplit.length; i++) {

                    // Iteration will end once keyword is found, loop exit boolean set to true.
                    if (keyword.equalsIgnoreCase(keyWordsSplit[i])) {
                        foundProduct = true;
                        break;
                    }
                }

                // Loop will exit when boolean = true.
                if (foundProduct) {
                    keyString = keywords;  // Saving correct keywords from database.
                    break;
                }
            }

            // Identifying product in database using keywords and constructing as object.
            results = statement.executeQuery("select * from products where keywords = '" +
                    keyString + "'");
            results.next();
            String productId = results.getString(1);
            String productType = results.getString(2);
            String modelNumber = results.getString(3);
            String productName = results.getString(4);
            String productDesc = results.getString(5);
            String keywords = results.getString(6);

            product = new Product(productId, productType, modelNumber, productName, productDesc, keywords);

        } catch (SQLException error) {
            error.printStackTrace();
        }

        // Returning constructed product object.
        return product;
    }
}
