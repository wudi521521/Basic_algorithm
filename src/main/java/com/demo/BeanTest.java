package com.demo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author wud
 * @desc todo
 * @date 2021/9/29 13:47
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class BeanTest {

    private Integer id;

    private String name;
}

class Test2{
    public static void main(String[] args) {
        BeanTest beanTest = new BeanTest(1,"name");
        BeanTest beanTest1 = new BeanTest();
    }
}
