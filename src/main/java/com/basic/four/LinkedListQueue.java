package com.basic.four;

import java.lang.annotation.Inherited;

/**
 * @author Dillon Wu
 * @Description: 链表实现队列queue
 * @date 2021/6/19 18:27
 */
public class LinkedListQueue<E> implements Queue<E>{

    /**
     * 内部类
     */
    private class Node {
        public E e;
        public LinkedListQueue.Node next;

        public Node(E e, LinkedListQueue.Node next) {
            this.e = e;
            this.next = next;
        }

        public Node(E e) {
            this(e, null);
        }

        public Node() {
            this(null, null);
        }

        public String toString() {
            return e.toString();
        }
    }


    private Node head,tail;
    private int size;

    public LinkedListQueue(){
        head =null;
        tail =null;
        size =0;
    }
    @Override
    public void enqueue(E e) {
        if (tail ==null){
            tail = new Node(e);
            head=tail;
        }else{
            tail.next=new Node(e);
            tail=tail.next;
        }
        size++;
    }

    @Override
    public E dequeue() {
        if (isEmpty()){
           throw new IllegalArgumentException("Cannot dequeue from an empty queue");
        }
        Node result = head;
        head =head.next;
        result.next=null;
        if (head==null){
            tail =null;
        }
        size--;
        return result.e;
    }

    @Override
    public E getFront() {
        if (isEmpty()){
            throw new IllegalArgumentException("Cannot dequeue from an empty queue");
        }
        return head.e;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size==0;
    }

}
