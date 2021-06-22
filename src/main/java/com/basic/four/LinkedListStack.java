package com.basic.four;

/**
 * @author Dillon Wu
 * @Description:链表实现栈
 * @date 2021/6/19 18:11
 */
public class LinkedListStack<E> implements Stack<E> {
    private LinkedList<E> list;

    @Override
    public int getSize() {
        return list.getSize();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public void push(E e) {
        list.addFirst(e);
    }

    @Override
    public E pop() {
        return list.removeFirst();
    }

    @Override
    public E peek() {
        return list.getFirst();
    }

    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("Stack:top");
        res.append(list);
        return res.toString();
    }
}
