package com.basic.three.array;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Dillon Wu
 * @Description: 数组
 * @date 2021/6/16 21:26
 */
public class Array {
    //数组
    private int[] data;
    //将要出入元素的位置
    private int size;
    /**
     * Default initial capacity.
     */
    private static final int DEFAULT_CAPACITY = 10;

    /**
     * 构建函数，传入数组的容量capacity构造Array
     */
    public Array(int capacity) {
        data = new int[capacity];
        size = 0;
    }

    public Array() {
        this(DEFAULT_CAPACITY);
    }

    /**
     * 获取元素个数
     *
     * @return
     */
    public int getSize() {
        return size;
    }

    /**
     * 获取数组容量
     *
     * @return
     */
    public int getCapacity() {
        return data.length;
    }

    /**
     * 判断数组是否为空
     *
     * @return
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 在数组中添加一个新元素
     */
    public void addLast(int e) {
       /* if (size ==data.length){
            throw new IllegalArgumentException("AddLast failed.Array is full");
        }
        data[size] = e;
        size++;*/
        add(size, e);
    }

    /**
     * 在第index个位置插入一个新元素e
     *
     * @param index
     * @param e
     */
    public void add(int index, int e) {
        if (size == data.length) {
            throw new IllegalArgumentException("AddLast failed.Array is full");
        }
        //index 索引不能为负数，index大于size确认数组是连续存储
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("AddLast failed.Array is full");
        }
        //指定索引index位置插入数据
        for (int i = size - 1; i >= index; i--) {
            //后一个索引位置，赋值上前一个索引数据
            data[i + 1] = data[i];
        }
        //覆盖index索引位置的数据
        data[index] = e;
        size++;
    }

    /**
     * 获取index索引位置的元素
     *
     * @param index
     * @return
     */
    public int get(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Get failed.index is illegal");
        }
        return data[index];
    }

    /**
     * 修改index位置的元素e
     *
     * @param index
     * @param e
     */
    public void set(int index, int e) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Get failed.index is illegal");
        }
        data[index] = e;
    }

    /**
     * 查找数组中是否有元素e
     *
     * @param e
     * @return
     */
    public boolean contains(int e) {
        for (int i = 0; i < size; i++) {
            if (data[i] == e) {
                return true;
            }
        }
        return false;
    }

    /**
     * 查找数组中元素e所在的索引，如果不存在元素e，则返回-1
     *
     * @param e
     * @return
     */
    public int find(int e) {
        for (int i = 0; i < size; i++) {
            if (data[i] == e) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 从数组中删除index位置的元素，返回删除的元素
     *
     * @param index
     * @return
     */
    public int remove(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Get failed.index is illegal");
        }
        //返回数据
        int ret = data[index];
        for (int i = index; i < size; i++) {
            //元素向前一个覆盖
            data[i] = data[i + 1];
        }
        return ret;
    }

    /**
     * 删除第一个元素，返回删除的元素
     *
     * @return
     */
    public int removeFirst() {
        return remove(0);
    }

    /**
     * 删除最后一个元素，返回删除的元素
     *
     * @param
     * @return
     */
    public int removeLast() {
        return remove(size-1);
    }

    /**
     * 删除数组中的元素
     */
    public void removeElement(int e){
           int index = find(e);
           if (index !=-1){
               remove(index);
           }
    }

    @Override
    public String toString() {
        //和 StringBuffer 之间的最大不同在于 StringBuilder 的方法不是线程安全的（不能同步访问）,但是查询快
        StringBuilder res = new StringBuilder();
        res.append(String.format("Array:size=%d ,capacity =%d\n", size, data.length));
        res.append('[');
        for (int i = 0; i < size; i++) {
            res.append(data[i]);
            if (i != size - 1) {
                res.append(",");

            }
        }
        res.append("]");
        return res.toString();
    }

    /**
     * 扩容
     * @param newCapacity 扩容值
     */
    public void resize(int newCapacity){
        int[] newData = new int[newCapacity];
        for (int i=0;i<size;i++){
            newData[i]=data[i];
        }
        data = newData;
    }

    public static void main(String[] args) {
        List list = new ArrayList();
        //ArrayList 是线程不安全
        list.add(1);
        list.add("A");
        list.remove(1);
        System.out.println(list.size() + "list capacity:"+list.size());

        //默认将扩容至原来容量的 1.5 倍
        //如果1.5倍太小的话，则将我们所需的容量大小赋值给 newCapacity，如果1.5倍太大或者我们需要的容量太大，
        // 那就直接拿 newCapacity = (minCapacity > MAX_ARRAY_SIZE) ? Integer.MAX_VALUE(2^31-1) : MAX_ARRAY_SIZE 来扩容
        Array arr = new Array(20);
        for (int i = 0; i < 10; i++) {
            arr.addLast(i);
        }
        System.out.println(arr.toString());
        arr.add(1, 100);
        System.out.println(arr.toString());
        arr.remove(1);
        System.out.println(arr.toString());
    }
}


