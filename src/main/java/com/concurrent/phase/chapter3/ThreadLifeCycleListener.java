package com.concurrent.phase.chapter3;

import java.util.List;

/**
 * @author Dillon Wu
 * @Description: 目标者
 * @date 2021/8/22 20:51
 */
public class ThreadLifeCycleListener implements LifeCycleListener {

    private final Object LOCK = new Object();

    public void concurrentQuery(List<String> ids) {
        if (ids == null || ids.isEmpty()) {
            return;
        }
        ids.stream().forEach(id -> new Thread(new ObservableRunnable(this) {
            @Override
            public void run() {
                try {
                    notifyChange(new RunnableEvent(RunnableState.RUNNING, Thread.currentThread(), null));
                    System.out.println("query for the id" + id);
                    Thread.sleep(1_000l);
                    notifyChange(new RunnableEvent(RunnableState.DONE, Thread.currentThread(), null));
                } catch (Exception e) {
                    e.printStackTrace();
                    notifyChange(new RunnableEvent(RunnableState.ERROR, Thread.currentThread(), e));

                }
            }
        }, id).start());
    }

    @Override
    public void onEvent(ObservableRunnable.RunnableEvent event) {
        synchronized (LOCK) {
            System.out.println("The runnable" + event.getThread().getName() + "} data changed and state is " + event.getState());
            if (event.getCause() !=null){
                System.out.println("The Runnable "+event.getThread().getName()+" error "+event.getCause());
            }
        }
    }
}
