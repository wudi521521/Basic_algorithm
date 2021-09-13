package com.zk;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import scala.math.Equiv;

import java.io.IOException;
import java.util.List;

/**
 * @author Dillon Wu
 * @Description: 客户端监听
 * @date 2021/9/13 15:21
 */
public class ConsumerListener {

    //集群连接
    private static String connectString = "101.200.78.47:2181,101.200.78.47:2182,101.200.78.47:2183";
    //超时时间
    private static int sessionTimeOut =200000;

    static ZooKeeper zkClient;

    public static void main(String[] args) throws IOException, KeeperException, InterruptedException {
        ConsumerListener listener = new ConsumerListener();
        //1:获取zk连接
        listener.getConnect();
        //2:监听/Servers下面节点的增加或者删除
        listener.getServerList();

        //3:业务逻辑
        Thread.sleep(1000000L);
    }

    /**
     * 获取节点并家庭
     * @throws KeeperException
     * @throws InterruptedException
     */
    public void getServerList() throws KeeperException, InterruptedException {
        List<String> children = zkClient.getChildren("/servers", true);
        for (String child:children){
            byte[] data = zkClient.getData("/servers/" + child, false, null);
            System.out.println(new String(data));
        }
    }

    /**
     * 连接集群
     * @throws IOException
     */
    private void getConnect() throws IOException {
        zkClient = new ZooKeeper(connectString, sessionTimeOut, new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {
                //注册一次监听一次
                try {
                    getServerList();
                } catch (KeeperException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
