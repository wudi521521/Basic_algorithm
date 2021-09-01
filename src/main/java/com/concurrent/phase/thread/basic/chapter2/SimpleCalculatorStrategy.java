package com.concurrent.phase.thread.basic.chapter2;

/**
 * @author Dillon Wu
 * @Description:
 * @date 2021/8/17 21:09
 */
public class SimpleCalculatorStrategy implements CalculatorStrategy {
    @Override
    public double calculate(double salary, double bonus) {
        return salary*0.5+bonus*0.1;
    }
}
