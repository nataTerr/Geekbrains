package ru.geekbrains.springWeb.services;

import ru.geekbrains.springWeb.model.Product;

import java.util.List;

public interface ProductService {

    List<Product> findAll();

    void save(Product product);

    Product findById(Long id);
}
