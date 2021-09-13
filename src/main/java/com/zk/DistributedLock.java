package com.zk;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * @author Dillon Wu
 * @Description: zk分布式锁
 * @date 2021/9/13 20:23
 */
public class DistributedLock {
    //集群连接
    private static String connectString = "101.200.78.47:2181,101.200.78.47:2182,101.200.78.47:2183";
    //超时时间
    private static int sessionTimeOut =200000;

    static ZooKeeper zkClient;

    private CountDownLatch countDownLatch = new CountDownLatch(1);

    private CountDownLatch waitLatch = new CountDownLatch(1);

    private String waitPath ;

    private String  currentMode;

    public DistributedLock() throws InterruptedException, KeeperException, IOException {
        //获取连接
        zkClient = new ZooKeeper(connectString, sessionTimeOut, new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {
                     //connectLatch 如果连接上zk 可以释放
                if (watchedEvent.getState()== Event.KeeperState.SyncConnected){
                    countDownLatch.countDown();
                }
                //waitLatch 需要释放
                if (watchedEvent.getType() == Event.EventType.NodeDeleted && watchedEvent.getPath().equals(waitPath)){
                    waitLatch.countDown();
                }
            }
        });
        //等待zk正常连接后，往下走程序
        countDownLatch.await();
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++");
        //判断节点/locks是否存在
        Stat stat = zkClient.exists("/locks", false);
        if (stat ==null){
            //创建一个根节点(持久化)
            zkClient.create("/locks","locks".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        }

    }

    //对zk加锁
    public void ZKLock() throws KeeperException, InterruptedException {
        //创建对应的临时带序号节点
         currentMode = zkClient.create("/locks/" + "seq-", null, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
        //判断创建的节点是否是最小序号节点，如果是获取到锁;如果不不是 ，监听它前一个序号节点
        List<String> children = zkClient.getChildren("/locks", false);
        //如果chidrent,只有一个值那就直接返回,如果多个节点，需要判断是否，谁最小
        if (children.size()==1){
            return;
        }else {
            //升序
            Collections.sort(children);
            //获取节点名称
            String thisNode = currentMode.substring("/locks/".length());
            //通过seq-000000000获取该节点在children集合的位置
            int index = children.indexOf(thisNode);
            //判断
            if (index == -1){
                System.out.println("数据异常");
            }else if (index ==0){
                //就一个节点,获取锁
                return;
            }else{
                //监听前一个节点
                waitPath = "/locks/"+children.get(index-1);
                //监控当前节点前一个节点
                zkClient.getData(waitPath,true,null);
                //等待监听
                waitLatch.await();

                return;
            }

        }
    }

    //解锁
    public void unZKLock() throws KeeperException, InterruptedException {
      //删除节点
        zkClient.delete(currentMode,-1);
    }
}
