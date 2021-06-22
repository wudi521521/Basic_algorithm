package com.basic.four;

/**
 * @author Dillon Wu
 * @Description: 数组队列接口
 * @date 2021/6/18 11:23
 */
public interface Queue<E> {

    /**
     * 进入队列
     */
    void enqueue(E e);

    /**
     * 取出队列数据
     *
     * @return
     */
    E dequeue();

    /**
     * 队列首部的元素
     *
     * @return
     */
    E getFront();

    /**
     * 队列中总数据
     *
     * @return
     */
    int getSize();

    /**
     * 队列是否为空
     *
     * @return
     */
    boolean isEmpty();
}
