package com.thread.advance.chapter12;

/**
 * @author Dillon Wu
 * @Description:
 * @date 2021/8/25 13:18
 */

/**
 * {@link ActiveObject #makeString(int,char)}
 */
public class MakeStringRequest extends MethodRequest {

   private final int count;

   private final char fillChar;

    public MakeStringRequest(Servant servant,FutureResult futureResult,int count,char fillChar){
        super(servant,futureResult);
        this.fillChar=fillChar;
        this.count = count;
    }
    @Override
    public void execute() {
       Result result = servant.makeString(count,fillChar);
       futureResult.setResult(result);
    }
}
