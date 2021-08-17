package com.basic.eight;

/**
 * @author Dillon Wu
 * @Description: Map映射基础
 * @date 2021/8/17 13:34
 */
public interface Map<K,V> {

    void add(K key,V value);

    V remove(K key);

    boolean contains(K key);

    V get(K key);

    void set(K key,V newValue);

    int getSize();

    boolean isEmpty();
}
