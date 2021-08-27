package com.thread.three.automic.integer;

import java.util.concurrent.Executors;

/**
 * @author Dillon Wu
 * @Description:
 * @date 2021/8/27 10:44
 */
public class GetLockException extends Exception {
    public GetLockException(){
        super();
    }

    public GetLockException(String message){
        super(message);
    }
}
