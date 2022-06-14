package ru.geekbrains.service;

import ru.geekbrains.model.Product;

import java.util.List;

public interface ProductService {

    List<Product> getProduct();

    void update(Product product);

    Product selectById(Integer id);

    void delete(Integer id);
}
