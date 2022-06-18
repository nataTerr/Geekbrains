package ru.geekbrains.springWeb.repositories;

import org.springframework.stereotype.Component;
import ru.geekbrains.springWeb.model.Product;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Component

public class ProductRepositoryImpl implements ProductRepository {

    private List<Product> products;

    @PostConstruct
    public void init() {
        this.products = new ArrayList<>(Arrays.asList(
                new Product(1L, "Camera", 100L),
                new Product(2L, "Photo", 200L),
                new Product(3L, "Smartphone", 100L)

        ));
    }

    @Override
    public Product findById(Long id) {
        return products.stream().filter(s -> s.getId().equals(id)).findFirst().get();
    }

    @Override
    public List<Product> findAll() {
        return Collections.unmodifiableList(products);
    }

    @Override
    public void save(Product product) {
        if (product.getId() == null) {
            Long id = Long.valueOf(products.size() + 1);
            product.setId(id);
        }
        products.add(product);
    }

}
