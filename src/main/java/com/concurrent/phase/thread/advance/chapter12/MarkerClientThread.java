package com.concurrent.phase.thread.advance.chapter12;

/**
 * @author Dillon Wu
 * @Description:
 * @date 2021/8/25 20:41
 */
public class MarkerClientThread extends Thread {
    private final char fillChar;

    private final ActiveObject activeObject;

    public MarkerClientThread(ActiveObject activeObject,String name){
        super(name);
        this.activeObject=activeObject;
        this.fillChar=name.charAt(0);
    }

    @Override
    public void run() {
        try{
            for (int i=0;true;i++){
                Result result = activeObject.makeString(i + 1, fillChar);
                Thread.sleep(20);
                String value = (String) result.getResultValue();
                System.out.println(Thread.currentThread().getName()+": value"+value);
            }
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
