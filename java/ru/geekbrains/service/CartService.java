package ru.geekbrains.service;

import ru.geekbrains.model.Cart;
import ru.geekbrains.model.Product;

public interface CartService {

    Cart getCart();

    void addProduct(Cart cart, Product product, Integer quantity);

    void delProduct(Cart cart, Product product, Integer quantity);

    void addProduct(Cart cart, Integer id, Integer quantity);

    void printCart(Cart cart);

    Integer getSum(Cart cart);

    int getProductQuantity(Cart cart, Product product);

    int getProductQuantity(Cart cart, Integer prodId);

}
