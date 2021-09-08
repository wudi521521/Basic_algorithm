package com.jedis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

/**
 * @author Dillon Wu
 * @Description: 测试事物
 * @date 2021/9/8 9:59
 */
public class JedisTX {
    public static void main(String[] args) {
        //1:new Jedis 对象即可//101.200.78.47
        Jedis jedis = new Jedis("101.200.78.47",6379);
        //开启事物
        Transaction multi = jedis.multi();
        try{
            multi.set("K","V");

            //关闭事物
            multi.exec();
        }catch (Exception e){
            //放弃事物
            multi.discard();
        }finally {
            //客户端关闭
            jedis.close();
        }



    }
}
