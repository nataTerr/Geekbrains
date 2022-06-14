package ru.geekbrains.repository;

import org.springframework.stereotype.Component;
import ru.geekbrains.model.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class RepositoryProduct implements Repository<Product> {


    private final Map<Integer, Product> productMap = new HashMap<>();

    {
        productMap.put(1, new Product(1, "Camera", 100));
        productMap.put(2, new Product(2, "Photo", 200));
        productMap.put(3, new Product(3, "Smartphone", 300));
    }

    @Override
    public Product selectById(Integer id) {
        return productMap.get(id);
    }


    @Override
    public List<Product> findAll() {
        return new ArrayList<>(productMap.values());
    }

    @Override
    public void update(Product obj) {
        if (obj.getId() == null) {
            Integer id = (Integer) (productMap.size() + 1);
            obj.setId(id);
        }
        productMap.put(obj.getId(), obj);
    }


    @Override
    public Product delete(Integer id) {
        return productMap.remove(id);
    }
}