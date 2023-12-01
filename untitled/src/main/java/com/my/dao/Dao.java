package com.my.dao;

import java.util.List;

public interface Dao<T> {
    List<T> getAll();
    T save(T t);
    void delete(int id);
    T get(int id);

}
