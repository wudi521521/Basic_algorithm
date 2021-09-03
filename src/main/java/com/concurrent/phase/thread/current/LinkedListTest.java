package com.concurrent.phase.thread.current;

import com.basic.four.LinkedList;

/**
 * @author Dillon Wu
 * @Description:
 * @date 2021/9/2 20:00
 */
public class LinkedListTest<E> {

    private Node<E> first;

    private final Node<E> NULL = (Node<E>) null;

    private final static String PLAIN_NULL = "null";

    private int size;

    public LinkedListTest() {
        this.first = NULL;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return (size() == 0);
    }

    public static <E> LinkedListTest<E> of(E... elements) {
        if (elements.length == 0) {
            return new LinkedListTest<>();
        } else {
            LinkedListTest<E> list = new LinkedListTest<>();
            for (E ele : elements) {
                list.addFirst(ele);
            }
            return list;
        }

    }

    public boolean contains(E e) {
        Node<E> current = first;
        while (current != null) {
            if (current.value == e) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    public E removeFirst() {
        if (this.isEmpty()) {
            throw new NoElementException("The linked list is empty");
        }
        Node<E> node = first;
        first = node.next;
        size--;
        return node.value;
    }

    ;

    public LinkedListTest<E> addFirst(E e) {
        final Node<E> newNode = new Node<>(e);
        newNode.next = first;
        this.size++;
         this.first = newNode;
        return this;
    }

    private static class Node<E> {
        E value;
        Node<E> next;

        public Node(E value) {
            this.value = value;
        }


        public String toString() {
            return "Node{" +
                    "value=" + value +
                    ", next=" + next +
                    '}';
        }
    }

    static class NoElementException extends RuntimeException {
        public NoElementException(String message) {
            super(message);
        }
    }

    public static void main(String[] args) {
        LinkedListTest<String> listTest = LinkedListTest.of("hello", "world", "number");
        System.out.println(listTest.size());
        System.out.println(listTest.contains("hello"));
        String removeFirst = listTest.removeFirst();
        System.out.println(removeFirst);
        System.out.println(listTest.size);
    }
}
