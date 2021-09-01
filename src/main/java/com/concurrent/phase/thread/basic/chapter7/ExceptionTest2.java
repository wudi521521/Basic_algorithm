package com.concurrent.phase.thread.basic.chapter7;

import java.util.Arrays;
import java.util.Optional;

/**
 * @author Dillon Wu
 * @Description:
 * @date 2021/8/20 11:14
 */
public class ExceptionTest2 {

    public void test(){
        //线程栈的追踪
        Arrays.asList(Thread.currentThread().getStackTrace())
                .stream()
                .forEach(item->{
                    Optional.of(item.getClassName()+" methodName:"+item.getMethodName()+" lineNumber:"+item.getLineNumber())
                            .ifPresent(System.out::println);
                });
    }
}
