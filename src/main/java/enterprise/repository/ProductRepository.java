package enterprise.repository;

import enterprise.entity.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductRepository {
    private PostgreConnect postgreConnect;
    public static final String ADD_NEW_PRODUCT="Insert Into products (id, productName, productPrice) VALUES (DEFAULT, ?, ?)";
    public static final String GET_ALL_PRODUCTS="Select * from products";
    public static final String GET_PRODUCT_BY_ID="Select * from products where id=?";
    public static final String DELETE_PRODUCT_BY_ID="Delete from products where id=?";
    public static final String UPDATE_PRODUCT_BY_ID="Update products Set productname=?, productprice=? Where id=?";

    public ProductRepository(PostgreConnect postgreConnect){
        this.postgreConnect = postgreConnect;
    };

    public void add(Product product){
        Connection connection = postgreConnect.getConnection();
        try(PreparedStatement preparedStatement = connection.prepareStatement(ADD_NEW_PRODUCT);){
            preparedStatement.setString(1, product.getProductName());
            preparedStatement.setDouble(2, product.getProductPrice());
            preparedStatement.execute();
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public List<Product> getProducts(){
        List<Product> products = new ArrayList<>();
        Connection connection = postgreConnect.getConnection();
        try(Statement statement = connection.createStatement();){
            ResultSet resultSet = statement.executeQuery(GET_ALL_PRODUCTS);
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String productName = resultSet.getString("productname");
                double productPrice = resultSet.getDouble("productprice");
                Product product = new Product(productName, productPrice, id);
                products.add(product);
            }
        } catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return products;
    }

    public Product getProductById(int id){
        Product product = new Product();
        Connection connection = postgreConnect.getConnection();
        try(PreparedStatement preparedStatement = connection.prepareStatement(GET_PRODUCT_BY_ID);){
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                String productName = resultSet.getString("productname");
                double productPrice = resultSet.getDouble("productprice");
                product.setProductPrice(productPrice);
                product.setProductName(productName);
                product.setId(id);
            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return product;
    }
    public void deleteProductById(int id){
        Connection connection = postgreConnect.getConnection();
        try(PreparedStatement preparedStatement = connection.prepareStatement(DELETE_PRODUCT_BY_ID);){
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void updateProductById(int id, String productName, double productPrice){
        Connection connection = postgreConnect.getConnection();
        try(PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PRODUCT_BY_ID);){
            preparedStatement.setString(1, productName);
            preparedStatement.setDouble(2, productPrice);
            preparedStatement.setInt(3, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
