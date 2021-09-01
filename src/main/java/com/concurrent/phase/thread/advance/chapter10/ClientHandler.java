package com.concurrent.phase.thread.advance.chapter10;

import java.net.Socket;

/**
 * @author Dillon Wu
 * @Description:
 * @date 2021/8/24 14:00
 */
public class ClientHandler implements Runnable {

    private final Socket socket;

    public ClientHandler(Socket socket){
        this.socket = socket;
    }


    @Override
    public void run() {

    }

    public void stop(){}
}
