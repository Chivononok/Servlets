package enterprise.dto;

public class ProductDTO {
    private int id;
    private String productName;
    private double productPrice;

    public ProductDTO(String productName, double productPrice){
        this.productName = productName;
        this.productPrice = productPrice;
        this.id++;
    }

    public int getId() {return id;}
    public String getProductName() {return productName;}
    public void setProductName(String productName) {this.productName = productName;}
    public double getProductPrice() {return productPrice;}
    public void setProductPrice(double productPrice) {this.productPrice = productPrice;}
}
