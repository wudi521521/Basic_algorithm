package com.basic.three.stack;

import com.basic.three.array.Array;
import com.basic.three.array.ArrayE;

/**
 * @author Dillon Wu
 * @Description: 实现栈
 * @date 2021/6/17 14:14
 */
public class ArrayStack<E> implements Stack<E> {

    ArrayE<E> array;

    public ArrayStack(int capacity) {
        array = new ArrayE<>(capacity);
    }

    public ArrayStack() {
        array = new ArrayE<>();
    }

    @Override
    public int getSize() {
        return array.getSize();
    }

    @Override
    public boolean isEmpty() {
        return array.isEmpty();
    }

    @Override
    public void push(E e) {
        array.addLast(e);
    }

    @Override
    public E pop() {
        return array.removeLast();
    }

    @Override
    public E peek() {
        return array.get(array.getSize() - 1);
    }

    public int capacity() {
        return array.getCapacity();
    }

    public String toString() {
        //和 StringBuffer 之间的最大不同在于 StringBuilder 的方法不是线程安全的（不能同步访问）,但是查询快
        StringBuilder res = new StringBuilder();
        res.append("stack:");
        res.append('[');
        for (int i = 0; i < array.getSize(); i++) {
            res.append(array.get(i));

        }
        res.append("]");
        return res.toString();
    }


}
