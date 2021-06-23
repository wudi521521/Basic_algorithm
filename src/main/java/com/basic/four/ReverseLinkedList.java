package com.basic.four;

/**
 * @author Dillon Wu
 * @Description:反转链表：节点不变，变化的只是反转的方向
 * @date 2021/6/23 16:25
 */
public class ReverseLinkedList {

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
        public ListNode(int[] arr) {
            if (arr == null || arr.length == 0) {
                throw new IllegalArgumentException("arr can not be empty");
            }
            this.val = arr[0];
            ListNode cur = this;
            for (int i = 1; i < arr.length; i++) {
                cur.next = new ListNode(arr[i]);
                cur = cur.next;
            }
        }

        //以当前节点为头结点的链表信息字符串
        public String toString(){
            StringBuilder res= new StringBuilder();
            ListNode cur =this;
            while(cur !=null){
                res.append(cur.val+"->");
                cur = cur.next;
            }
            res.append("NULL");
            return res.toString();
        }
    }

    /**
     * 非递归实现反转链表
     * @param head
     * @return
     */
    public static ListNode reverseList(ListNode head) {
        //前一个节点
        ListNode pre = null;
        //当前节点
        ListNode cur = head;
        //下一个节点
        while (cur != null) {
            ListNode next = cur.next;
            cur.next=pre;
            pre=cur;
            cur=next;
        }
        return pre;
    }

    public static void main(String[] args) {
      int[] arr ={1,2,3,4,5,6};
        ListNode node = new ListNode(arr);
        System.out.println(node.toString());
        ListNode listNode = reverseList(node);
        System.out.println(listNode.toString());

    }
}
