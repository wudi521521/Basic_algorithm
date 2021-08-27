package com.thread.three.automic;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @author Dillon Wu
 * @Description:
 * @date 2021/8/27 21:02
 */
public class AtomicReferenceTest {

    public static void main(String[] args) {
        AtomicReference atomic = new AtomicReference<>(new simple("Dillon",17));
        System.out.println(atomic.get());
        boolean result =atomic.compareAndSet(new simple("sd",1),new simple("dillon",14));
        System.out.println(result);
    }

    static class simple{
        private String name;

        public int getAge() {
            return age;
        }
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setAge(int age) {
            this.age = age;
        }

        private int age;

        public simple(String name,int age){
            this.name=name;
            this.age=age;
        }
    }
}
