package com.thread.advance.chapter12;

/**
 * @author Dillon Wu
 * @Description:
 * @date 2021/8/25 11:43
 */
public class RealResult implements Result {

    private final Object resultValue;

    public RealResult(Object resultValue){
        this.resultValue = resultValue;
    }
    @Override
    public Object getResultValue() {
        return null;
    }
}
