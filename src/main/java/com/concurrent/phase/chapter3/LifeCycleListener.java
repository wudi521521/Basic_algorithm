package com.concurrent.phase.chapter3;

/**
 * @author Dillon Wu
 * @Description:
 * @date 2021/8/22 20:49
 */
public interface LifeCycleListener {

    void onEvent(ObservableRunnable.RunnableEvent event);
}
