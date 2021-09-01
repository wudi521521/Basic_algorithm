package com.concurrent.phase.thread.three.automic.integer;

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
