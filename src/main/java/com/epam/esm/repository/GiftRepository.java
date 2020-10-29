package com.epam.esm.repository;

import com.epam.esm.entity.Tag;

import java.util.List;

public interface GiftRepository<T> {
    void add(T t);

    int deleteTag(T t);

    T findTagById(Integer id);

    List<T> all();

}
