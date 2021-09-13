package com.zk;

import lombok.SneakyThrows;
import org.apache.zookeeper.KeeperException;

import java.io.IOException;

/**
 * @author Dillon Wu
 * @Description:
 * @date 2021/9/13 21:04
 */
public class DistributeLockTest {
    public static void main(String[] args) throws InterruptedException, IOException, KeeperException {
        final DistributedLock lock1 = new DistributedLock();
        final DistributedLock lock2 = new DistributedLock();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    lock1.ZKLock();
                    System.out.println("锁1获取到锁");
                    Thread.sleep(5*1000);
                    lock1.unZKLock();
                    System.out.println("锁2是释放锁");
                } catch (KeeperException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }).start();

        new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    lock2.ZKLock();
                    System.out.println("锁2获取到锁");
                    Thread.sleep(5*1000);
                    lock2.unZKLock();
                    System.out.println("锁2释放锁");
                } catch (KeeperException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }).start();
    }
}
