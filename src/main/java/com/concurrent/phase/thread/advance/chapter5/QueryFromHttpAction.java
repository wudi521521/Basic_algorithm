package com.concurrent.phase.thread.advance.chapter5;

/**
 * @author Dillon Wu
 * @Description:
 * @date 2021/8/23 20:16
 */
public class QueryFromHttpAction {

    public void executor(ContextData context){

            String name = context.getName();
            String cardId = getCardId(name);
            context.setCardId(cardId);

    }

    private String getCardId(String name){
        try {
            Thread.sleep(10_000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
         return "1232323";

    }
}
