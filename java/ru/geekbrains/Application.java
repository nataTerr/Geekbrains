package ru.geekbrains;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import java.io.IOException;

@ComponentScan
public class Application {

    public static void main(String[] args) throws IOException {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Application.class);
        CartWork cartWork = context.getBean(CartWork.class);
        cartWork.run();
    }
}
