package com.zk;

import lombok.SneakyThrows;
import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;
import org.junit.Before;
import org.junit.Test;
import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;

import java.util.List;

/**
 * @author Dillon Wu
 * @Description:
 * @date 2021/9/13 13:41
 */
public class ZKClient {

    //集群连接
    private String connectString = "101.200.78.47:2181,101.200.78.47:2182,101.200.78.47:2183";
    //超时时间
    private int sessionTimeOut =200000;
    static   ZooKeeper zkClient;


    @SneakyThrows
    @Before
    public void init(){
         zkClient = new ZooKeeper(connectString, sessionTimeOut, new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {
               /* System.out.println("----------------------");
                List<String> children = null;
                try{
                     children = zkClient.getChildren("/", true);
                    for (String child:children){
                        System.out.println(child);
                    }
                }catch (Exception e){

                }
                System.out.println("----------------------------");
*/
            }
        });
        System.out.println(zkClient.getState().isConnected());
    }

    /**
     * 创建节点
     * @throws KeeperException
     * @throws InterruptedException
     */
    @Test
    public void create() throws KeeperException, InterruptedException {
        //参数1:要创建的节点的路径 参数2：节点数据 参数3：节点权限 参数4：节点的类型
        String node = zkClient.create("/atuigu1", "ss.avi".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        System.out.println("===="+node);
    }

    /**
     * 获取子节点并监控节点变化
     */
    @Test
    public void getChildren() throws KeeperException, InterruptedException {
        List<String> children = zkClient.getChildren("/", true);
        for (String child:children){
            System.out.println("=========="+child);
        }

        //延时
        Thread.sleep(Long.MIN_VALUE);

    }

    /**
     * 判断节点是否存在
     * @throws KeeperException
     * @throws InterruptedException
     */
    @Test
    public void exit() throws KeeperException, InterruptedException {
        Stat exists = zkClient.exists("/atuigu1", false);
        System.out.println(exists==null?"not exist":"exist");
    }




}
