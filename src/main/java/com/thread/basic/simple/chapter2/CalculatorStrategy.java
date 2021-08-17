package com.thread.basic.simple.chapter2;

/**
 * @author Dillon Wu
 * @Description:函数式接口
 * @date 2021/8/17 21:07
 */
@FunctionalInterface
public interface CalculatorStrategy {

    double calculate(double salary,double bonus);
}
