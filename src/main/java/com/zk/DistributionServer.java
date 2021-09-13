package com.zk;

import org.apache.zookeeper.*;

import java.io.IOException;

/**
 * @author Dillon Wu
 * @Description:
 * @date 2021/9/13 15:08
 */
public class DistributionServer {
    //集群连接
    private static String connectString = "101.200.78.47:2181,101.200.78.47:2182,101.200.78.47:2183";
    //超时时间
    private static int sessionTimeOut =200000;

    static   ZooKeeper zkClient;


    public static void main(String[] args) throws IOException, KeeperException, InterruptedException {
        DistributionServer server = new DistributionServer();
        //1:获取zk连接
       server.getConnect();
        //2:服务接口注册zk集群
        server.regist("hostName");
        //3:启动业务逻辑
        Thread.sleep(1000000L);

    }

    /**
     * 创建节点(连接节点)
     * @param hostname
     * @throws KeeperException
     * @throws InterruptedException
     */
    private void regist(String hostname) throws KeeperException, InterruptedException {
        zkClient.create("/servers/"+hostname,hostname.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
        System.out.println(hostname+"已经上线");
    }

    /**
     * 连接集群
     * @throws IOException
     */
    private void getConnect() throws IOException {
        zkClient = new ZooKeeper(connectString, sessionTimeOut, new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {

            }
        });
    }
}
