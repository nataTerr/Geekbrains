package ru.geekbrains.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ru.geekbrains.model.Product;
import ru.geekbrains.repository.Repository;
import ru.geekbrains.repository.RepositoryProduct;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private Repository<Product> repositoryProduct;

    @Autowired
    public void setProductRepository(Repository<Product> repositoryProduct) {
        this.repositoryProduct = repositoryProduct;
    }

    @Override
    public List<Product> getProduct() {
        return repositoryProduct.findAll();
    }

    @Override
    public void update(Product product) {
        repositoryProduct.update(product);
    }

    @Override
    public Product selectById(Integer id) {
        return repositoryProduct.selectById(id);
    }

    @Override
    public void delete(Integer id) {
        repositoryProduct.delete(id);
    }
}
