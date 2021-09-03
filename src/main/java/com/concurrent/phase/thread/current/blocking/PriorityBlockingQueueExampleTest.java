package com.concurrent.phase.thread.current.blocking;

import org.junit.After;
import org.junit.Before;

import java.util.concurrent.PriorityBlockingQueue;

/**
 * @author Dillon Wu
 * @Description:
 * @date 2021/9/3 13:58
 */
public class PriorityBlockingQueueExampleTest {

    private PriorityBlockingQueueExample example;

    @Before
    public void setUp(){
        this.example=new PriorityBlockingQueueExample();
    }

    @After
    public void tearDown(){

    }

    public void testAddNewElement(){
        PriorityBlockingQueue<Object> queue = example.create(5);
    }
}
