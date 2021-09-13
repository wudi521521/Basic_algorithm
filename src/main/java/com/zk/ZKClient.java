package com.zk;

import lombok.SneakyThrows;
import org.apache.zookeeper.*;
import org.junit.Test;

/**
 * @author Dillon Wu
 * @Description:
 * @date 2021/9/13 13:41
 */
public class ZKClient {

    //集群连接
    private static String connectString = "101.200.78.47:2181,101.200.78.47:2182,101.200.78.47:2183";
    //超时时间
    private static int sessionTimeOut =2000;
    static   ZooKeeper zkClient;


    @SneakyThrows
    public static void init(){
         zkClient = new ZooKeeper(connectString, sessionTimeOut, new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {

            }
        });
        System.out.println(zkClient.getState().isConnected());
    }

    /**
     * 创建节点
     * @throws KeeperException
     * @throws InterruptedException
     */
    public static void create() throws KeeperException, InterruptedException {
        String node = zkClient.create("/atuigu", "ss.avi".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
    }

    public static void main(String[] args) throws KeeperException, InterruptedException {
        init();
        create();
    }


}
