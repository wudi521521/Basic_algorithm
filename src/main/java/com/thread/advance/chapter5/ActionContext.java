package com.thread.advance.chapter5;

import javax.swing.*;

/**
 * @author Dillon Wu
 * @Description:
 * @date 2021/8/23 20:50
 */
final public class ActionContext {
    private static final ThreadLocal<ContextData> threadLocal = new ThreadLocal<ContextData>(){
        @Override
        protected ContextData initialValue() {
            return new ContextData();
        }
    };

    private static class ContextHolder{
        private final static ActionContext actionContext = new ActionContext();
    }

    public static ActionContext getActionContext(){
        return ContextHolder.actionContext;
    }
    public ContextData getContext(){
        return threadLocal.get();
    }


}
