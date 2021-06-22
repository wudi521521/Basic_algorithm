package com.basic.four;

/**
 * @author Dillon Wu
 * @Description:链表
 * @date 2021/6/19 14:15
 */
public class LinkedList<E> {
    /**
     * 内部类
     */
    private class Node {
        public E e;
        public Node next;

        public Node(E e, Node next) {
            this.e = e;
            this.next = next;
        }

        public Node(E e) {
            this(e, null);
        }

        public Node() {
            this(null, null);
        }

        public String toString() {
            return e.toString();
        }
    }

    /**
     * 首部节点
     */
    private Node head;
    /**
     * 数据数
     */
    int size;

    public LinkedList() {
        head = null;
        size = 0;
    }

    //获取链表中的元素个数
    public int getSize() {
        return size;
    }

    //返回链表是否为空
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 链表头部新增元素
     *
     * @param e
     */
    public void addFirst(E e) {
        /*Node node = new Node(e);
        //node.next称为old head
        node.next=head;
        //新增节点，变为被head指向为首部
        head = node;*/
        head = new Node(e, head);
        size++;
    }

    //在链表的index(0-based)位置添加新的元素e
    //在链表中不是一个常用的操作,练习用:)
    public void add(int index, E e) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("Add failed.Illegal index");
        }
        if (index == 0) {
            addFirst(e);
        } else {
            Node prev = head;
            //从head节点，到index位置前一个节点
            for (int i = 0; i < index - 1; i++) {
                prev = prev.next;
            }
            //把数据放入node节点中
            /*Node node = new Node(e);
            node.next = prev.next;
            prev.next=node;*/
            prev.next = new Node(e, prev.next);
            size++;
        }
    }

    //在链表末尾添加新的元素e
    public void addLast(E e) {
        add(size, e);
    }

    //获取链表的第index(0-based)个位置的元素
    //在链表中不是一个常用的操作，练习用
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Get failed,Illegal index");
        }
        Node result = head;
        for (int i = 0; i < index; i++) {
            result = result.next;
        }
        return result.e;
    }

    //获取链表的第一个元素
    public E getFirst() {
        return get(0);
    }

    //获取链表的最后一个元素
    public E getLast() {
        return get(size - 1);
    }

    //修改链表的第index(0-based)个位置的元素为e
    //在链表中不是一个常用的操作，练习用
    public void set(int index, E e) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Get failed,Illegal index");
        }
        Node result = head;
        for (int i = 0; i < index; i++) {
            result = result.next;
        }
        result.e = e;
    }

    //查找链表中是否有元素e
    public boolean contains(E e) {
        Node result = head;
        while (result != null) {
            if (result.e.equals(e)) {
                return true;
            } else {
                //链表中最后的node.next为null;
                result = result.next;
            }
        }
        return false;
    }
    //从链表中删除index(0-based)位置的元素，返回删除的元素
    //在链表中不是一个常用的操作，练习用
    public E delete(int index){
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Get failed,Illegal index");
        }
        Node pre =head;
        for (int i=0;i<index-1;i++){
            //最终获取到的是index位置的前一个node
            pre=head.next;
        }
        //将被删除的node
        Node delNode=pre.next;
        pre.next=delNode.next;
        //设置删除node执行为空，垃圾处理器会自动处理
        delNode.next=null;
        size--;
         return delNode.e;
    }

    //从链表中删除第一个元素，返回删除的元素
    public E removeFirst(){
        return delete(0);
    }

    //从链表中删除最后一个元素，返回删除的元素
    public E removeLast(){
        return delete(size-1);
    }

    public String toString() {
        StringBuilder res = new StringBuilder();
        Node result = head;
        while (result != null) {
            res.append(result + "->");
            result = result.next;
        }
        res.append("null");
        return res.toString();
    }

    public static void main(String[] args) {
        LinkedList<Integer> linkedList = new LinkedList<>();
        for (int i=0;i<5;i++){
            linkedList.addFirst(i);
            System.out.println(linkedList);
        }
        linkedList.add(2,666);
        System.out.println(linkedList);
        linkedList.delete(2);
        System.out.println(linkedList);
    }

}
