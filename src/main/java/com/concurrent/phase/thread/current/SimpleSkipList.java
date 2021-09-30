package com.concurrent.phase.thread.current;



import java.util.Random;

/**
 * @author Dillon Wu
 * @Description:
 * @date 2021/9/3 9:23
 */
public class SimpleSkipList {

    private final static byte HEAD_BIT = (byte) -1;
    private final static byte DATA_BIT = (byte) 0;
    private final static byte TAIL_BIT = (byte) -1;


    /**
     * 定义节点
     */
    private static class Node {
        private Integer value;
        private Node up, down, left, right;
        private byte bit;

        public Node(Integer value, byte bit) {
            this.value = value;
            this.bit = bit;
        }

        public Node(Integer value) {
            this(value, DATA_BIT);
        }
    }

    private Node head;
    private Node tail;
    private int size;
    private int height;
    private Random random;

    public SimpleSkipList() {
        this.head = new Node(null, HEAD_BIT);
        this.tail = new Node(null, TAIL_BIT);
        head.right = tail;
        tail.left = head;
        this.random = new Random(System.currentTimeMillis());

    }

    private Node find(Integer element) {

        Node current = head;
        for (; ; ) {
            while (current.right.bit != TAIL_BIT && current.right.value <= element) {
                current = current.right;
            }
            if (current.down != null) {
                current = current.down;
            } else {
                break;
            }
        }
        return current;//the current<= element <current.right(if exist)
    }

    private boolean contains(Integer element) {
        Node node = this.find(element);
        return (node.value == element);
    }

    private Integer get(Integer element) {
        Node node = this.find(element);
        return (node.value == element) ? node.value : null;
    }

    public void add(Integer element) {
        Node nearNode = this.find(element);
        Node newNode = new Node(element);
        newNode.left = nearNode;
        newNode.right = nearNode.right;
        nearNode.right.left = newNode;
        nearNode.right = newNode;

        int currentLevel = 0;
        while (random.nextDouble() < 0.5d) {

            if (currentLevel>=height){
                height++;
                Node dumyHead=new Node(null,HEAD_BIT);
                Node dumyTail = new Node(null,TAIL_BIT);
                dumyHead.right=dumyTail;
                dumyHead.down=head;
                head.up=dumyHead;

                dumyTail.left=dumyHead;
                dumyTail.down=tail;
                tail.up=dumyTail;

                head=dumyHead;
                tail=dumyTail;

            }
            while ((nearNode != null) && nearNode.up == null) {
                nearNode = nearNode.left;
            }
            nearNode = nearNode.up;
            Node upNode = new Node(element);
            upNode.left = nearNode;
            upNode.right = nearNode.right;
            upNode.down = newNode;

            nearNode.right.left = upNode;
            nearNode.right = upNode;

            newNode.up = upNode;
            newNode=upNode;
            currentLevel++;
        }
        size++;
    }

    public void dumpSkipList(){
        Node temp = head;
        for (int i=height+1;i>0;i--){
            System.out.printf("Total [%d] height [%d]",height+1,i);
            Node node = temp.right;
            while (node.bit==DATA_BIT){
                System.out.printf("->%d",node.value);
                node=node.right;
            }
            System.out.printf("\n");
            temp=temp.down;
        }
        System.out.println("===============");
    }

    public boolean isEmpty() {
        return (size() == 0);
    }

    public int size() {
        return size;
    }

    public static void main(String[] args) {
        SimpleSkipList skipList = new SimpleSkipList();
        skipList.add(10);
        skipList.dumpSkipList();
    }
}
