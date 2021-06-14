package com.basic.linear.search;

/**
 * @author Dillon Wu
 * @Description: 泛型处理数组
 * @date 2021/6/11 14:56
 */
public class GenericParadigm {

    /**
     * 泛型不可以是基本数据类型，只能是类对象
     * == 的作用：
     * 　　基本类型：比较的就是值是否相同
     * 　　引用类型：比较的就是地址值是否相同
     * equals 的作用:
     * 　　引用类型：默认情况下，比较的是地址值。
     * @param data
     * @param target
     * @param <E>
     * @return
     */
    public static<E> int staticSearch(E[] data, E target) {
        for (int i = 0; i < data.length; i++) {
            if (data[i].equals(target) ) {
                return i;
            }
        }
        //没有数据返回-1
        return -1;
    }

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        Integer[] data = {24, 18, 12, 9, 16, 66, 32, 4};
        //jdk1.8中可以省略<Integer>
        int result = GenericParadigm.staticSearch(data, 16);
        //自定义实体
        Student[] students ={new Student("A"),new Student("C"),new Student("D")} ;
        Student wudi = new Student("C");
        int resultStudent = GenericParadigm.staticSearch(students,wudi);
    }
}
