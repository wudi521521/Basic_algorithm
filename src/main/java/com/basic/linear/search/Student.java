package com.basic.linear.search;

/**
 * @author Dillon Wu
 * @Description:
 * @date 2021/6/11 15:34
 */
public class Student {

    private String name;

    Student(String name){
        this.name = name;
    }

    public boolean equals(Object student){
        if (this == student){
            return true;
        }
        //传递的数据不为null
        if (null == student){
            return false;
        }
        //判断类型是否一致
        if (this.getClass() != student.getClass()){
            return false;
        }

        Student another = (Student)student;

        return this.name.equals(another.name);

    }
}
