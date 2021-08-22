package com.second.phase.chapter3;

/**
 * @author Dillon Wu
 * @Description: SharedResource
 * @date 2021/8/22 21:19
 */
public class Gate {

    private int counter =0;

    private String name = "Nobody";

    private String address = "NowWhere";

    /**
     * 临界点
     * @param name
     * @param address
     */
    public synchronized void pass(String name,String address){
        this.counter++;
        this.name =name;
        this.address = address;
        verify();
    }

    private void verify(){
        if (this.name.charAt(0)!=this.address.charAt(0)){
            System.out.println("********BROKEN*********"+toString());
        }
    }

    public synchronized String toString(){
        return "NO"+counter+":"+name+","+address;
    }
}
