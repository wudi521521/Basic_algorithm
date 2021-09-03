package com.concurrent.phase.thread.current;

/**
 * @author Dillon Wu
 * @Description:
 * @date 2021/9/2 20:00
 */
public class PriorityLinkedListTest<E extends Comparable<E>> {

    private Node<E> first;

    private final Node<E> NULL = (Node<E>) null;

    private final static String PLAIN_NULL = "null";

    private int size;

    public PriorityLinkedListTest() {
        this.first = NULL;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return (size() == 0);
    }

    public static <E extends Comparable<E>> PriorityLinkedListTest<E> of(E... elements) {
        if (elements.length == 0) {
            return new PriorityLinkedListTest<>();
        } else {
            PriorityLinkedListTest<E> list = new PriorityLinkedListTest<>();
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

    public PriorityLinkedListTest<E> addFirst(E e) {
        final Node<E> newNode = new Node<>(e);
        Node<E> previous = null;
        Node<E> current =first;
        //循环判断，当前数据大于大于第一个数据
        while (current !=null && e.compareTo(current.value)>0){
            previous = current;
            current=current.next;
        }
        //前一个为空
        if (previous == null){
            first = newNode;
            newNode.next=current;
        }else{
            previous.next=newNode;
            newNode.next=current;
        }
        size++;
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
        PriorityLinkedListTest<Integer> listTest = PriorityLinkedListTest.of(-1, 3, 1, 0, 4, 5, 9);
        System.out.println(listTest);
    }
}
