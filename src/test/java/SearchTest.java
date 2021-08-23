import org.junit.jupiter.api.Test;
import java.sql.Connection;
import static org.junit.jupiter.api.Assertions.*;

// Example of a test class that can be implemented for my program.
class SearchTest implements Credentials {

    Search search = new Search();
    Database database = new Database();

    // Initializing database and establishing connection.
    Connection connection = database.initializeDb(url, username, password);

    @Test
    public void testSearch() {

        String productId = "M20324";
        String productType = "inline";
        String modelNo = "ION05";
        String productName = "Stan Smith Shoes";
        String productDesc = """
                    Shop with Stan Smith Shoes - White at adidas.co.uk! See all the styles and colours of Stan
                    Smith Shoes - White at the official adidas UK online store.""";
        String keywords = "Stan Smith Shoes";

        Product expProduct = new Product(productId, productType, modelNo, productName, productDesc, keywords);
        String expResult = expProduct.getProductId();

        Product actualProduct = search.searchProducts("stan", connection);
        String actualResult = actualProduct.getProductId();

        assertEquals(expResult, actualResult);
    }
}