package com.basic.select.sort;

/**
 * @author Dillon Wu
 * @Description:
 * @date 2021/6/14 21:30
 */
public class Student implements Comparable<Student> {

    private  String name;
    private int score;
    public Student(String name,int score){
        this.name = name;
        this.score = score;
    }



    @Override
    public int compareTo(Student another) {
        return this.score-another.score;
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

    //格式化
    public String toString(){
        return String.format("Student(name:%s,score:%s)",name,score);
    }
}
