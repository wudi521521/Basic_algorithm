package com.concurrent.phase.thread.advance.chapter5;

/**
 * @author Dillon Wu
 * @Description:
 * @date 2021/8/23 20:09
 */
public class ExecutionTask implements Runnable {

    private QueryFromDBAction queryAction = new QueryFromDBAction();
    private QueryFromHttpAction httpAction = new QueryFromHttpAction();

    @Override
    public void run() {
        final ContextData contextData = new ContextData();
        queryAction.executor(contextData);
        System.out.println("The name query successful "+contextData.getName());
        System.out.println("The carId query successful "+contextData.getCardId());
        httpAction.executor(contextData);
        System.out.println("The cardId query successful");
        System.out.println("The name is "+contextData.getName()+" cardId "+contextData.getCardId() );
    }
}
