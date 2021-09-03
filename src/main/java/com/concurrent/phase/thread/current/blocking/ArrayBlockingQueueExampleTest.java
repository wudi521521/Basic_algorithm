package com.concurrent.phase.thread.current.blocking;

import org.hamcrest.CoreMatchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.*;

/**
 * @author Dillon Wu
 * @Description:
 * @date 2021/9/3 11:23
 */
public class ArrayBlockingQueueExampleTest {

    private  ArrayBlockingQueueExample example;

    @Before
    public void setUp() {
        example = new ArrayBlockingQueueExample();
    }

    @After
    public void tearDown() {
        example=null;
    }

    @Test
    public void testAddMethodNotExceedCapacity(){
        ArrayBlockingQueue<String> queue = example.create(5);
        assertThat(queue.add("hello"), equalTo(true));
        assertThat(queue.add("hello"),equalTo(true));
        assertThat(queue.add("hello"),equalTo(true));
        assertThat(queue.add("hello"),equalTo(true));
        assertThat(queue.add("hello"),equalTo(true));
        assertThat(queue.size(),equalTo(5));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddMethodNotExceedError(){
        ArrayBlockingQueue<String> queue = example.create(5);
        assertThat(queue.add("hello"), equalTo(true));
        assertThat(queue.add("hello"),equalTo(true));
        assertThat(queue.add("hello"),equalTo(true));
        assertThat(queue.add("hello"),equalTo(true));
        assertThat(queue.add("hello"),equalTo(true));
        assertThat(queue.add("hello"),equalTo(true));
        fail("should not process to here");
    }

    @Test
    public void testOfferMethodNotExceedCapacity(){
        ArrayBlockingQueue<String> queue = example.create(5);
        assertThat(queue.offer("hello"),equalTo(true));
        assertThat(queue.offer("hello"),equalTo(true));
        assertThat(queue.offer("hello"),equalTo(true));
        assertThat(queue.offer("hello"),equalTo(true));
        assertThat(queue.offer("hello"),equalTo(true));
        assertThat(queue.offer("hello"),equalTo(false));
        assertThat(queue.size(),equalTo(5));
    }

    @Test
    public void testPutMethodNotExceedCapacity() throws InterruptedException {
        ArrayBlockingQueue<String> queue = example.create(5);
        ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
        service.schedule(()->{
            try{
                queue.take();
                System.out.println("=========");
            }catch (InterruptedException e){
                e.printStackTrace();
            }

        },1, TimeUnit.SECONDS);

        queue.put("hello1");
        queue.put("hello2");
        queue.put("hello3");
        queue.put("hello4");
        queue.put("hello5");
        queue.put("hello6");
        service.shutdown();


    }

    @Test
    public void testPoll(){
        ArrayBlockingQueue<String> queue = example.create(2);
        queue.add("hello4");
        queue.add("hello5");
        ////////////////////////////////////////
        assertThat(queue.poll(),equalTo("hello4"));
        assertThat(queue.poll(),equalTo("hello5"));

    }





}
