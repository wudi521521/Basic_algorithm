
package com.concurrent.phase.thread.advance.chapter2;

/**
 * @author Dillon Wu
 * @Description:
 * @date 2021/8/23 13:44
 */
public class ImmutablPerformance {

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        SynObj synObj = new SynObj();
        for (long l=0l;l<10000000;l++){
            System.out.println(synObj);
        }
        long endTime = System.currentTimeMillis();
        System.out.println("Elapsed "+(endTime-startTime));
    }
}

/**
 * 不可变对象
 */
class ImmutableObj{
    private final String name;

    ImmutableObj(String name){
        this.name = name;
    }

    @Override
    public String toString() {
        return "ImmutableObj{" +
                "name='" + name + '\'' +
                '}';
    }
}

/**
 * 可变对象
 */
class SynObj{
    private String name;

    public synchronized void setName(String name) {
        this.name = name;
    }

    @Override
    public synchronized String toString() {
        return "SynObj{" +
                "name='" + name + '\'' +
                '}';
    }
}
