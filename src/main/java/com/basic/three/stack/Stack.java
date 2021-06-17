package com.basic.three.stack;

/**
 * @author Dillon Wu
 * @Description: 自定义stack
 * @date 2021/6/17 14:11
 */
public interface Stack<E> {
    /**
     * 返回元素个数
     * @return
     */
    int getSize();

    /**
     * 判断栈是否为空
     * @return
     */
    boolean isEmpty();

    /**
     * 进栈
     * @param e
     */
    void push(E e);

    /**
     * 出栈
     * @return
     */
    E pop();

    /**
     * 查询栈顶元素
     * @return
     */
    E peek();
}
