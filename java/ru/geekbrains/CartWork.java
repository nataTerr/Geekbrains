package ru.geekbrains;

import org.springframework.stereotype.Service;
import ru.geekbrains.model.Cart;
import ru.geekbrains.model.Product;
import ru.geekbrains.service.CartService;
import ru.geekbrains.service.ProductService;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

@Service
public class CartWork {

    private final ProductService productService;
    private final CartService cartService;

    public CartWork(ProductService productService, CartService cartService) {
        this.productService = productService;
        this.cartService = cartService;
    }

    public static void printList(List<?> list) {
        System.out.println("СПИСОК ПРОДУКТОВ:");
        for (Object prodList : list) {
            System.out.println(prodList.toString());
        }
    }

    public void run() throws IOException {
        Cart cart = cartService.getCart();
        System.out.println("Консольное приложение для работы с корзиной. Для справки /?");
        listCommand();

        Scanner in = new Scanner(System.in);
        boolean exit = false;
        while (!exit) {
            System.out.print("Введите команду: ");
            Integer prodId;
            Integer quantity;
            String str = in.nextLine();
            if (!str.isEmpty()) {
                String[] parts = str.split("\\s");
                String command = parts[0];
                if (command.equalsIgnoreCase("exit")) {
                    exit = true;
                    System.out.println("Спасибо, приходите к нам еще.");
                } else if (command.equalsIgnoreCase("/?")) {
                    listCommand();
                } else if (command.equals("list")) {
                    printList(productService.getProduct());
                } else if (command.equalsIgnoreCase("new")) {
                    // удалить корзину, создать новую
                    cart = cartService.getCart();
                    System.out.println("Создана новая (пустая) корзина, старая - удалена.");
                } else if (command.equalsIgnoreCase("print")) {
                    cartService.printCart(cart); // распечатать содержимое корзины
                } else if (command.equalsIgnoreCase("sum")) {
                    System.out.println(cartService.getSum(cart)); // распечатать стоимость корзины
                    // параметры для удаления и добавления продуктов - должно быть три части
                } else if (parts.length == 3) {
                    // преобразуем данные в нужный формат
                    try {
                        prodId = Integer.valueOf(parts[1]);
                        quantity = Integer.parseInt(parts[2]);
                    } catch (NumberFormatException e) {
                        errorCommand();
                        continue;
                    }
                    if (command.equalsIgnoreCase("add")) {
                        // добавить продукт
                        Product product = productService.selectById(prodId);
                        if (product != null) {
                            cartService.addProduct(cart, productService.selectById(prodId), quantity);
                            System.out.println("В корзину добавлен товар: " + productService.selectById(prodId) + " - " + quantity + " шт.");
                        } else {
                            System.out.println("Такого товара нет в списке.");
                        }
                    } else if (command.equalsIgnoreCase("del")) {
                        // удалить продукт
                        if (cartService.getProductQuantity(cart, prodId) > 0) {
                            cartService.delProduct(cart, productService.selectById(prodId), quantity);
                            System.out.println("Из корзины удален товар: " + productService.selectById(prodId) + " - " + quantity + " шт.");
                        } else {
                            System.out.println("Такого продукта нет в корзине.");
                        }
                    }
                } else {
                    errorCommand();
                }
            }
        }
    }

    private static void errorCommand() {
        System.out.println("Неправильный формат команды");
    }

    private static void listCommand() {
        System.out.println("Вывести список продуктов: list");
        System.out.println("Добавить продукт в корзину: add [N продукта] [количество]");
        System.out.println("Удалить продукт из корзины: del [N продукта] [количество]");
        System.out.println("\tЕсли количество товара в корзине < 1, то он удаляется из списка.");
        System.out.println("Показать содержимое корзины: print");
        System.out.println("Показать только общую стоимость товаров в корзине: sum");
        System.out.println("Очистить корзину (создать новую): new");
        System.out.println("Завершить: exit");
    }
}
