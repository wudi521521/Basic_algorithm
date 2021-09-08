package com.jedis;

import redis.clients.jedis.Jedis;

/**
 * @author Dillon Wu
 * @Description:
 * @date 2021/9/8 8:43
 */
public class JedisConnect {
    public static void main(String[] args) {
        //1:new Jedis 对象即可//101.200.78.47
        Jedis jedis = new Jedis("101.200.78.47",6379);
        boolean connected = jedis.isConnected();
        System.out.println("==="+connected);
        jedis.set("kkk","vvv");


    }
}
