public class Product {

    // Product object attributes.
    String productId;
    String productType;
    String modelNumber;
    String productName;
    String productDesc;
    String keyWords;

    // Product constructor.
    public Product(String productId, String productType, String modelNumber, String productName,
                   String productDesc, String keyWords) {

        this.productId = productId;
        this.productType = productType;
        this.modelNumber = modelNumber;
        this.productName = productName;
        this.productDesc = productDesc;
        this.keyWords = keyWords;
    }

    // Get methods.
    public String getProductId() { return productId; }
    public String getProductName() { return productName; }
    public String getModelNumber() { return modelNumber; }

    // Product toString output method.
    public String toString() {
        String output = "\nProduct ID: " + this.productId;
        output += "\nProduct Type: " + this.productType;
        output += "\nModel Number: " + this.modelNumber;
        output += "\nProduct Name: " + this.productName;
        output += "\nDescription: " + this.productDesc;

        return output;
    }
}
