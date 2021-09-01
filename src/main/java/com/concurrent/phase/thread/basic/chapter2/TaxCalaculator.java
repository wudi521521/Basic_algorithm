package com.concurrent.phase.thread.basic.chapter2;

/**
 * @author Dillon Wu
 * @Description:
 * @date 2021/8/17 20:51
 */

public class TaxCalaculator {

    private final double salary;

    public CalculatorStrategy getCalculatorStrategy() {
        return calculatorStrategy;
    }

    public void setCalculatorStrategy(CalculatorStrategy calculatorStrategy) {
        this.calculatorStrategy = calculatorStrategy;
    }

    private CalculatorStrategy calculatorStrategy;

    public double getSalary() {
        return salary;
    }

    public double getBonus() {
        return bonus;
    }

    private final double bonus;

    public TaxCalaculator(double salary, double bonus,CalculatorStrategy calculatorStrategy) {
        this.salary = salary;
        this.bonus = bonus;
        this.calculatorStrategy = calculatorStrategy;
    }

    public double calcTax() {
        return calculatorStrategy.calculate(salary,bonus);
    }

    public double calculator() {
        return this.calcTax();
    }

    public static void main(String[] args) {
//        TaxCalaculator taxCalaculator = new TaxCalaculator(1000d,2000d){
//            @Override
//            public double calcTax() {
//                return getBonus()*0.1+getSalary()*0.5;
//            }
//        };
//        double tax = taxCalaculator.calculator();
//        System.out.println(tax);
        //策略模式
        TaxCalaculator taxCalaculator = new TaxCalaculator(1000d,2000d,(s,b)->s*0.1+b*0.5);
        System.out.println(taxCalaculator.calculator());
        Thread thread = new Thread();
    }
}
