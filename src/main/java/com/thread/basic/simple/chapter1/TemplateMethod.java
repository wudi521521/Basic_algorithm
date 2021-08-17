package com.thread.basic.simple.chapter1;

/**
 * @author Dillon Wu
 * @Description:
 * @date 2021/8/17 18:07
 */
public abstract class TemplateMethod {

    public final void print(String message) {
        System.out.println("****************");
        wrapPrint(message);
        System.out.println("****************");
    }

    protected void wrapPrint(String message) {

    }

    public static void main(String[] args) {
        TemplateMethod t1 = new TemplateMethod() {
            @Override
            protected void wrapPrint(String message) {
                System.out.println("&&&&&&"+message);
            }
        };
        t1.print("Hello Thread");

        TemplateMethod t2 = new TemplateMethod() {
            @Override
            protected void wrapPrint(String message) {
                System.out.println("^^^^^^"+message);
            }
        };
        t2.print("Hello Thread2---");
    }
}
