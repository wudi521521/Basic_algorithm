package com.zk;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooKeeper;

import java.util.concurrent.CountDownLatch;

/**
 * @author Dillon Wu
 * @Description: curator分布式锁测试
 * @date 2021/9/14 9:56
 */
public class CuratorLockTest {
    //集群连接
    private static String connectString = "101.200.78.47:2181,101.200.78.47:2182,101.200.78.47:2183";
    //超时时间
    private static int sessionTimeOut =200000;

    static ZooKeeper zkClient;

    private CountDownLatch countDownLatch = new CountDownLatch(1);
    public static void main(String[] args) {
        //创建分布式锁1
        InterProcessMutex lock1 = new InterProcessMutex(getCuratorFramework(),"/locks");
        //创建分布式锁2
        InterProcessMutex lock2 = new InterProcessMutex(getCuratorFramework(),"/locks");
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    lock1.acquire();
                    System.out.println("锁1获取到锁");
                    Thread.sleep(5*1000);
                    lock1.acquire();
                    System.out.println("锁1 再次获取锁");
                    lock1.release();
                    System.out.println("线程1 释放锁");
                    lock1.release();
                    System.out.println("线程1再次释放锁");
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }).start();

        new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    lock2.acquire();
                    System.out.println("锁2获取到锁");
                    Thread.sleep(5*1000);
                    lock2.acquire();
                    System.out.println("锁2 再次获取锁");

                    lock2.release();
                    System.out.println("线程2 释放锁");
                    lock2.release();
                    System.out.println("线程2再次释放锁");

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }).start();
    }


    private static CuratorFramework getCuratorFramework(){
        //重试间隔时间,重试次数
        ExponentialBackoffRetry retry = new ExponentialBackoffRetry(3000, 3);
        CuratorFramework client = CuratorFrameworkFactory.builder().connectString(connectString)
                //连接超时时间
                .connectionTimeoutMs(sessionTimeOut)
                .sessionTimeoutMs(sessionTimeOut)
                .retryPolicy(retry).build();
        //启动客户端
        client.start();
        System.out.println("启动");
        return client;
    }
}
