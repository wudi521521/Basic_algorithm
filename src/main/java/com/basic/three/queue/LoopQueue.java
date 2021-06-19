package com.basic.three.queue;

/**
 * @author Dillon Wu
 * @Description: 循环队列实现queue接口
 *   为什么总是求余?            既然说是循环队列了，也就是队尾在数组中有可能是在队头前面，所以得用Mod
 * @date 2021/6/18 13:54
 */
public class LoopQueue<E> implements Queue<E> {

    private E[] data;

    private int front, tail;

    private int size;

    public LoopQueue(int capacity) {
        //循环数组需要浪费一位空间
        data = (E[]) new Object[capacity + 1];
        //前部
        front = 0;
        //尾部
        tail = 0;
        //容器中的值
        size = 0;
    }

    public LoopQueue() {
        this(10);
    }

    /**
     * 容量值
     *
     * @return
     */
    public int getCapacity() {
        return data.length - 1;
    }

    @Override
    public void enqueue(E e) {
        //取余
        if ((tail + 1) % data.length == front) {
            resize(getCapacity() * 2);
        }
        data[tail] = e;
        tail = (tail + 1) % data.length;
        size++;
    }

    @Override
    public E dequeue() {
        if (isEmpty()) {
            throw new IllegalArgumentException("Cannot dequeue from an empty queue");
        }
        E ret = data[front];
        data[front]=null;
        front = (front + 1) % data.length;
        size--;
        return ret;
    }

    @Override
    public E getFront() {
        if (isEmpty()){
            throw new IllegalArgumentException("data is null");
        }
        return data[front];
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return front == tail;
    }

    private void resize(int newCapacity) {
        E[] newData = (E[]) new Object[newCapacity + 1];
        for (int i = 0; i < size; i++) {
            newData[i] = data[(front + i) % data.length];
        }
        data = newData;
        front = 0;
        tail = size;

    }

    public String toString(){
        //和 StringBuffer 之间的最大不同在于 StringBuilder 的方法不是线程安全的（不能同步访问）,但是查询快
        StringBuilder res = new StringBuilder();
        res.append("LoopQueue:");
        res.append("front[");
        for (int i = front; i !=tail; i=(i+1)%data.length) {
            res.append(data[i]);
            if ((i +1)%data.length !=tail) {
                res.append(",");
            }
        }
        res.append("]tail");
        return res.toString();
    }

    public static void main(String[] args) {
        LoopQueue<Integer> queue = new LoopQueue<>();
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
