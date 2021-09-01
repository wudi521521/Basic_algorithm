package com.concurrent.phase.thread.advance.chapter12;

/**
 * @author Dillon Wu
 * @Description:
 * @date 2021/8/25 11:19
 */
public class Test {

    public static void main(String[] args) {
        //System.gc();
        ActiveObject activeObject = ActiveObjectFactory.createActiveObject();
        new MarkerClientThread(activeObject,"wudi").start();;
        new MarkerClientThread(activeObject,"Tom").start();
        new DisplayClientThread("Chris",activeObject).start();
    }
}
