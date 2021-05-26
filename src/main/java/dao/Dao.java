package dao;

import model.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Dao implements IDao {

    private Connection getConnection(){
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/product","root","123456");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }




    @Override
    public List<Product> findAll() {
        List<Product> products = new ArrayList<>();
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from product");
            System.out.println(preparedStatement);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int price = resultSet.getInt("price");
                int quantity = resultSet.getInt("quantity");
                products.add(new Product(id,name,price,quantity));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return products;
    }

    @Override
    public void save(Product product) {
        System.out.println("insert product SQL");

        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement  = connection.prepareStatement("INSERT INTO product" + "  (name, price, quantity) VALUES " +
                    " (?, ?, ?);");
            preparedStatement.setString(1,product.getName());
            preparedStatement.setInt(2,product.getPrice());
            preparedStatement.setInt(3,product.getQuantity());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public Product findByID(int id) {
        Product product = null;

        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select id,name ,price,quantity from product where id = ?");
            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                String name = resultSet.getString("name");
                int price = resultSet.getInt("price");
                int quantity = resultSet.getInt("quantity");
                product = new Product(id,name,price,quantity);

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return product;
    }

    @Override
    public void update(int id, Product product) throws SQLException {
        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("update product set name = ?, price = ?, quantity = ? where id = ?" );
        preparedStatement.setString(1,product.getName());
        preparedStatement.setInt(2,product.getPrice());
        preparedStatement.setInt(3,product.getQuantity());
        preparedStatement.setInt(4,id);

        preparedStatement.executeUpdate();
    }

    @Override
    public void remote(int id) {

        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("delete from product where id = ?");
            preparedStatement.setInt(1,id);
            preparedStatement.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
}
