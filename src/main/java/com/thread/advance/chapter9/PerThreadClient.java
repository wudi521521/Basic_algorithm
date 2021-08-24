package com.thread.advance.chapter9;

import java.util.stream.IntStream;

/**
 * @author Dillon Wu
 * @Description:
 * @date 2021/8/24 13:24
 */
public class PerThreadClient {

    public static void main(String[] args) {
        MessageHandler messageHandler = new MessageHandler();
        IntStream.rangeClosed(1,5).forEach(item->{
            messageHandler.request(new Message(String.valueOf(item)));
        });
    }
}
