package com.thread.three.automic.integer;

/**
 * @author Dillon Wu
 * @Description:
 * @date 2021/8/27 10:45
 */
public class AtomicIntegerDetailsTest2 {

    private final static CompareAndSetLock lock = new CompareAndSetLock();
    public static void main(String[] args) throws Exception{
        for (int i = 0; i < 5; i++) {
             new Thread() {
                @Override
                public void run() {
                    try {
                        doSomething2();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }.start();
        }
    }

    private static void doSomething() {
        synchronized (AtomicIntegerDetailsTest2.class) {
            System.out.println(Thread.currentThread().getName() + " get the lock");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    private static void doSomething2() throws Exception{
         try {
             lock.tryLock();
             System.out.println(Thread.currentThread().getName() + " get the lock");
             Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
             lock.unlock();
         }

    }
}
