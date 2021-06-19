package com.basic.three.queue;

import com.basic.three.array.ArrayE;

/**
 * @author Dillon Wu
 * @Description: 数组队列---实现queue队列接口
 * @date 2021/6/18 11:27
 */
public class ArrayQueue<E> implements Queue<E> {

    private ArrayE<E> array;

    public ArrayQueue(int capacity) {
        array = new ArrayE<>(capacity);
    }

    public ArrayQueue() {
        array = new ArrayE<>();
    }

    /**
     * 队列添加元素
     *
     * @param e
     */
    @Override
    public void enqueue(E e) {
        array.addLast(e);
    }

    /**
     * 取出队列元素
     *
     * @return
     */
    @Override
    public E dequeue() {
        return array.removeFirst();
    }

    /**
     * 获取队列首部元素
     *
     * @return
     */
    @Override
    public E getFront() {
        return array.get(0);
    }

    @Override
    public int getSize() {
        return array.getSize();
    }

    @Override
    public boolean isEmpty() {
        return array.isEmpty();
    }

    public int getCapacity() {
        return array.getCapacity();
    }

    public String toString() {
        //和 StringBuffer 之间的最大不同在于 StringBuilder 的方法不是线程安全的（不能同步访问）,但是查询快
        StringBuilder res = new StringBuilder();
        res.append("Queue:");
        res.append('[');
        for (int i = 0; i < array.getSize(); i++) {
            res.append(array.get(i));
            if (i != array.getSize() - 1) {
                res.append(",");
            }
        }
        res.append("]");
        return res.toString();
    }

    public static void main(String[] args) {
        ArrayQueue<Integer> queue = new ArrayQueue<>();
        for (int i = 0; i < 10; i++) {
            queue.enqueue(i);
            System.out.println(queue);
            if (i%3==2){
                queue.dequeue();
                System.out.println(queue);
            }
        }
    }
}
