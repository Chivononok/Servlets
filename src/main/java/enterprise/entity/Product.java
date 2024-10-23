package enterprise.entity;

public class Product {
    private String productName;
    private double productPrice;
    private int id;

    public Product(String productName, double productPrice, int id){
        this.productName = productName;
        this.productPrice = productPrice;
        this.id = id;
    }
    public Product(){
        this.id = -1;
    }

    public String getProductName() {return productName;}
    public double getProductPrice() {return productPrice;}
    public int getId() {return id;}

    public void setProductName(String productName) {this.productName = productName;}
    public void setProductPrice(double productPrice) {this.productPrice = productPrice;}
    public void setId(int id) {this.id = id;}
}
