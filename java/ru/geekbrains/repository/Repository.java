package ru.geekbrains.repository;


import java.util.List;

public interface Repository<T> {

    T selectById(Integer id);

    List<T> findAll();

    void update(T obj);

    T delete(Integer id);
}