package com.thread.advance.chapter10;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Dillon Wu
 * @Description:
 * @date 2021/8/24 13:52
 */
public class AppServer extends Thread{

    private int port;

    private static final int DEFAULT_PORT=12722;

    private volatile boolean start = true;

    private List<ClientHandler> clientHandlers = new ArrayList<>();

    private List<Thread> clientHandler = new ArrayList<>();

    private final static ExecutorService executor = Executors.newFixedThreadPool(5);

    public AppServer(){
        this(DEFAULT_PORT);
    }
    public AppServer(int defaultPort){
        this.port = defaultPort;
    }

    @Override
    public void run() {
        try{
            ServerSocket serverSocket = new ServerSocket(port);
            while(start){
                Socket client = serverSocket.accept();
                ClientHandler clientHandler = new ClientHandler(client);
                executor.submit(new ClientHandler(client));
                this.clientHandlers.add(clientHandler);
            }
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    public void startServer(){
        try{
            ServerSocket serverSocket = new ServerSocket(port);

        }catch (IOException e){
            e.printStackTrace();
        }finally {
            this.dispose();
        }
    }

    private void dispose(){
        clientHandlers.stream().forEach(item->{
            item.stop();
        });
        this.executor.shutdown();
    }

    public void shutDown(){
        this.start = false;
        this.interrupt();
    }
}
