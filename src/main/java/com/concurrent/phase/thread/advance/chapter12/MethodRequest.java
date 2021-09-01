package com.concurrent.phase.thread.advance.chapter12;

/**
 * @author Dillon Wu
 * @Description:
 * @date 2021/8/25 11:38
 */

/**
 * 对应ActiveObject的每一个方法
 */
public abstract class MethodRequest {

    protected final Servant servant;

    protected final FutureResult futureResult;

    public MethodRequest(Servant servant,FutureResult futureResult){
        this.servant = servant;
        this.futureResult = futureResult;
    }

    public abstract  void execute();

}
