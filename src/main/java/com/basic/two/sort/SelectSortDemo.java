package com.basic.two.sort;
import java.util.Arrays;

/**
 * @author Dillon Wu
 * @Description: 选择排序{按照小到到排序}
 * @date 2021/6/14 20:56
 */
public class SelectSortDemo {

    private SelectSortDemo(){}

    /**
     * 泛型方法
     * @param arr
     * @param <E> extends在泛型上使用是实现某个接口
     */
    public static<E extends Comparable<E>> void sort(E[] arr){
        for (int i=0;i<arr.length;i++){
            for (int j=i+1;j<arr.length;j++){
                //前者与后者比较{为1时是大于，等于0是相等，为-1是小于}
                //此数的compareTo是类自己定义的
                if (arr[i].compareTo(arr[j])==1){
                    //选择arr[i...n)中的最小值的索引
                    E minValue = arr[i];
                    arr[i]=arr[j];
                    arr[j]=minValue;
                }
            }
        }
    }

    public static void main(String[] args) {
        //Integer
        Integer[] arr={4,5,1,3,8,9};
        sort(arr);
        Arrays.stream(arr).forEach(item->{
            System.out.println(item);
        });

        //Student实体
        Student[] students ={new Student("A",3),new Student("C",2),new Student("D",7)} ;
        sort(students);
       for(Student student:students){
           System.out.print(student.toString()+"/n");
       }
    }
}
