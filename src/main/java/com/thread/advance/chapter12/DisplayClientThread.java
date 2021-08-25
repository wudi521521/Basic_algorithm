package com.thread.advance.chapter12;

/**
 * @author Dillon Wu
 * @Description:
 * @date 2021/8/25 20:37
 */
public class DisplayClientThread extends Thread {

    private final ActiveObject activeObject;

    public DisplayClientThread(String name,ActiveObject activeObject){
        super(name);
        this.activeObject = activeObject;
    }

    @Override
    public void run() {
        for(int i=0;true;i++){
            String text =Thread.currentThread().getName()+"="+i;
            activeObject.displayString(text);
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
