import java.sql.*;

public class Database {

    // Method to initialize DB for testing the program.
    // Database will generate along with relevant tables.
    // Two products will be added along with admin user username: admin, password: admin.
    // Returns a connection to the initialized DB.
    public Connection initializeDb(String url, String username, String password) {

        Connection connection = null;

        try {

            // Establishing connection with provided details to create statements.
            connection = DriverManager.getConnection
                    (url, username, password);
            Statement statement = connection.createStatement();

            // Creating database.
            statement.executeUpdate("create database if not exists example_db");
            statement.executeQuery("use example_db");

            // Creating tables.
            statement.executeUpdate("""
                    create table if not exists login
                    (user_id int not null auto_increment,
                    username varchar(32) not null unique,
                    password varchar(32) not null,
                    primary key (user_id))
                    """);

            statement.executeUpdate("""
                    create table if not exists products
                    (prod_id varchar(12) not null unique,
                    prod_type varchar(32) not null,
                    model_no varchar(12) not null unique,
                    prod_name varchar(32) not null,
                    prod_desc varchar(256) not null,
                    keywords varchar(256) not null,
                    primary key (prod_id))
                    """);

            statement.executeUpdate("""
                    create table if not exists review
                    (user_id int not null unique,
                    prod_id varchar(12) not null unique,
                    review varchar(256) not null,
                    review_score int not null,
                    foreign key (user_id)
                    references login (user_id),
                    foreign key (prod_id)
                    references products (prod_id))
                    """);

            // Adding admin user.
            username = "admin";
            password = "admin";

            // Calling login table data from DB.
            ResultSet results = statement.executeQuery("Select * from login");

            // If login table is empty admin user is added.
            if (!results.next()) {

                statement.executeUpdate("insert into login (username, password) values ('" +
                        username + "', '" + password + "')");
            }

            // Adding product data.
            String productId1 = "M20324";
            String productType1 = "inline";
            String modelNo1 = "ION05";
            String productName1 = "Stan Smith Shoes";
            String productDesc1 = """
                    Shop with Stan Smith Shoes - White at adidas.co.uk! See all the styles and colours of Stan
                    Smith Shoes - White at the official adidas UK online store.""";
            String keyWords1 = "Stan Smith Shoes";

            String productId2 = "BB5476";
            String productType2 = "inline";
            String modelNo2 = "IAZ12";
            String productName2 = "Gazelle Shoes";
            String productDesc2 = """
                    Shop for Gazelle Shoes - Black at adidas.co.uk! See all the styles and colours of Gazelle 
                    Shoes - Black at the official adidas UK online store.""";
            String keyWords2 = "Gazelle Shoes";

            // Calling products table data from DB.
            results = statement.executeQuery("select * from products");

            // If products table is empty the two products are added.
            if (!results.next()) {

                statement.executeUpdate("insert into products values ('" +
                        productId1 + "', '" + productType1 + "', '" + modelNo1 +
                        "', '" + productName1 + "', '" + productDesc1 + "', '" + keyWords1 + "')");

                statement.executeUpdate("insert into products values ('" +
                        productId2 + "', '" + productType2 + "', '" + modelNo2 +
                        "', '" + productName2 + "', '" + productDesc2 + "', '" + keyWords2 + "')");
            }

        } catch (SQLException error) {
            error.printStackTrace();
        }

        // Returns connection to DB.
        return connection;
    }
}
