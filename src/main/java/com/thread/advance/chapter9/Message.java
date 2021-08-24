package com.thread.advance.chapter9;

/**
 * @author Dillon Wu
 * @Description:
 * @date 2021/8/24 13:20
 */
public class Message {
    public String getValue() {
        return value;
    }

    private final String value;

    public Message(String value){
        this.value = value;
    }

}
