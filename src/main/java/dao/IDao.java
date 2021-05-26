package dao;

import model.Product;

import java.sql.SQLException;
import java.util.List;

public interface IDao {
    List<Product> findAll();

    void save(Product product);

    Product findByID(int id);

    void update(int id, Product product) throws SQLException;

    void remote(int id);

}
