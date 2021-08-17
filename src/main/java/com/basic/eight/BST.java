package com.basic.eight;
/**
 * @author Dillon Wu
 * @Description: 二分搜索树
 * @date 2021/8/13 13:44
 */
public class BST<E extends Comparable<E>> {

    /**
     * 二分搜索树内的节点
     */
    private class Node {

        public E e;
        public Node left, right;

        public Node(E e) {
            this.e = e;
            left = null;
            right = null;
        }
    }

    /**
     * 构造函数
     */
    private Node root;
    private int size;

    public BST() {
        root = null;
        size = 0;
    }

    /**
     * 节点个数
     *
     * @return
     */
    public int size() {
        return size;
    }

    /**
     * 是否为空
     *
     * @return
     */
    public boolean isEmpty() {
        return size == 0;
    }
    //向二分搜索树中添加新的元素e
    public void add(E e){
      if (root ==null){
          root = new Node(e);
          size++;
      }else{
          add(root,e);
      }
    }
    //向以node为根的二分搜索树中插入元素E,递归算法
    /*private void add(Node node,E e){
        //含有元素e
        if (e.equals(node.e)){
            return;
        }else if (e.compareTo(node.e)<0 && node.left==null){
            node.left=new Node(e);
            size ++;
            return;
        }else if (e.compareTo(node.e)>0 && node.right==null){
            size++;
            return;
        }
        //递归
        if (e.compareTo(node.e)<0){
            add(node.left,e);
        }else {
            add(node.right,e);
        }
    }*/
    //向以node为根的二分搜索树中插入元素E,递归算法
    //返回插入新节点后二分搜索树的根
    private Node add(Node node,E e){
        if (node ==null){
            size++;
            return new Node(e);
        }
        //插入的节点小于根节点,插入节点在根节点左侧
        if (e.compareTo(node.e)<0){
           node.left= add(node.left,e);
        }else{
            //插入的节点大于根节点,插入节点在根节点右侧
            node.right= add(node.right,e);
        }
        return node;
    }

    //二分搜索树中是否包含元素e
    public boolean contains(E e){
         return contains(root,e);
    }

    //看以node为根的二分搜索树中是否包含元素e,递归算法
    private boolean contains(Node node,E e){
        if (node ==null){
            return false;
        }
        if (e.compareTo(node.e)==0){
            return true;
        }else if (e.compareTo(node.e)<0){
            return contains(node.left,e);
        }else {
            return contains(node.right,e);
        }
    }

    //二分搜索树的前序遍历
    public void preOrder(){
           preOrder(root);
    }
    //前序遍历以node为根的二分搜索树，递归算法
    private void preOrder(Node node){
        if (node ==null){
            return;
        }
        if (node !=null){
            System.out.println(node.e);
            preOrder(node.left);
            preOrder(node.right);
        }
    }
    //二分搜索树的中序遍历
    public void inOrder(){
        inOrder(root);
    }

    private void inOrder(Node node){
        if (node == null){
            return;
        }
        inOrder(node.left);
        System.out.println(node.e);
        inOrder(node.right);
    }
    //二分搜索树的后序遍历
    public void postOrder(){
        postOrder(root);
    }
    //后序遍历以node为根的二分搜索树，递归算法
    private void postOrder(Node node){
        if (node==null){
            return;
        }
        postOrder(node.left);
        postOrder(node.right);
        System.out.println(node.e);
    }

    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();
        generateBSTString(root,0,res);

        return res.toString();
    }

    private void generateBSTString(Node node,int depth,StringBuilder res){
        if (node==null){
            res.append(generateDepthString(depth)+"null\n");
            return;
        }
        res.append(generateDepthString(depth)+node.e+"\n");
        generateBSTString(node.left,depth+1,res);
        generateBSTString(node.right,depth+1,res);

    }

    private String generateDepthString(int depth){
        StringBuilder res = new StringBuilder();
        for (int i=0;i<depth;i++){
            res.append("--");
        }
        return res.toString();
    }
}
