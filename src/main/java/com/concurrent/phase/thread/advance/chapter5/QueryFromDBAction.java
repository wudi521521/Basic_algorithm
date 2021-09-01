package com.concurrent.phase.thread.advance.chapter5;

/**
 * @author Dillon Wu
 * @Description:
 * @date 2021/8/23 20:09
 */
public class QueryFromDBAction {

    public void executor(ContextData context){
          try{
              Thread.sleep(1_000);
              String name = "Alex"+" "+Thread.currentThread().getName();
              context.setName(name);
          }catch (InterruptedException e){
              e.printStackTrace();
          }
    }
}
