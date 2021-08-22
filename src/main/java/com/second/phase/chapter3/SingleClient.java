package com.second.phase.chapter3;

/**
 * @author Dillon Wu
 * @Description:
 * @date 2021/8/22 21:27
 */
public class SingleClient {
    public static void main(String[] args) {
        Gate gate = new Gate();
        User bj = new User("Beibao","Beijing",gate);
        User gz = new User("Guangbao","Guangzhou",gate);
        User sh = new User("Shangbao","Shanghai",gate);
        bj.start();
        gz.start();
        sh.start();
    }
}
