package service;

import model.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductServiceIml implements ProductService{

    private static Map<Integer, Product> productMap;
    static {
        productMap = new HashMap<>();
        productMap.put(1,new Product(1,"Iphone 7 Plus ", 10,5));
        productMap.put(2,new Product(2,"Iphone 8  ", 12,5));
        productMap.put(3,new Product(3,"Iphone 8 Plus ", 15,5));
        productMap.put(4,new Product(4,"Iphone XS ", 20,5));
    }



    @Override
    public List<Product> findAll() {
        return new ArrayList<>(productMap.values());
    }

    @Override
    public void save(Product product) {
        productMap.put(product.getId(),product);
    }

    @Override
    public Product findByID(int id) {
        return productMap.get(id);
    }

    @Override
    public void update(int id, Product product) {
        productMap.put(id,product);
    }

    @Override
    public void remote(int id) {
        productMap.remove(id);
    }
}
