package com.thread.advance.chapter12;

/**
 * @author Dillon Wu
 * @Description:
 * @date 2021/8/25 11:29
 */

/**
 * 接受异步消息的主动方法
 */
public interface ActiveObject {

    Result makeString(int count,char fillChar);

    void displayString(String text);


}
