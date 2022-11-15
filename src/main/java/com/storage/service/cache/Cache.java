package com.storage.service.cache;

public interface Cache<T> {
    void add(String key, T object);

    T get(String key);

    void remove(String key);
}
