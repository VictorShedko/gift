package com.epam.esm.repository;

import java.util.List;

public interface GiftRepositoryInterface<T> {

    void add(T t);

    int delete(T t);

    T findById(Integer id);

    List<T> all();

}
