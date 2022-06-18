package ru.geekbrains.springWeb.repositories;

import ru.geekbrains.springWeb.model.Product;

import java.util.List;

public interface ProductRepository {

    Product findById(Long id);

    List<Product> findAll();

    void save(Product product);

}
