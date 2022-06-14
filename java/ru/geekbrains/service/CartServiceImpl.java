package ru.geekbrains.service;

import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.stereotype.Service;
import ru.geekbrains.model.Cart;
import ru.geekbrains.model.Product;
import ru.geekbrains.repository.Repository;

import java.util.Map;

@Service

public class CartServiceImpl implements CartService {

    private final Repository<Product> repositoryProduct;

    public CartServiceImpl(Repository<Product> repositoryProduct) {
        this.repositoryProduct = repositoryProduct;
    }

    @Lookup
    @Override
    public Cart getCart() {
        return null;
    }

    @Override
    public void addProduct(Cart cart, Product product, Integer quantity) {
        cart.addProduct(product, quantity);
    }

    @Override
    public void delProduct(Cart cart, Product product, Integer quantity) {
        cart.delProduct(product, quantity);
    }

    @Override
    public void addProduct(Cart cart, Integer id, Integer quantity) {
        Product product = repositoryProduct.selectById(id);
        this.addProduct(cart, product, quantity);
    }

    @Override
    public void printCart(Cart cart) {
        Integer sum = Integer.valueOf(0);
        for (Map.Entry<Product, Integer> entryMap : cart.getCartMap().entrySet()) {
            Product product = entryMap.getKey();
            Integer prodSum = product.getCost() * (Integer.valueOf(entryMap.getValue()));
            System.out.printf("Product id = %-2s | name = %-15s | price = %-8s | quantity = %-3s | sum = %-12s \n",
                    product.getId(), product.getName(), product.getCost(), entryMap.getValue(), prodSum);
        }
    }

    @Override
    public Integer getSum(Cart cart) {
        return cart.getSum();
    }

    @Override
    public int getProductQuantity(Cart cart, Product product) {
        if (cart.getCartMap().containsKey(product)) {
            return cart.getCartMap().get(product);
        }
        return 0;
    }

    @Override
    public int getProductQuantity(Cart cart, Integer prodId) {
        Product product = repositoryProduct.selectById(prodId);
        return this.getProductQuantity(cart, product);
    }

}
