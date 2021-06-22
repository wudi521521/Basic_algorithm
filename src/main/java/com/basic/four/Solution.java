package com.basic.four;

/**
 * @author Dillon Wu
 * @Description:
 * @date 2021/6/22 14:36
 */
public class Solution {
    public ListNode removeElements(ListNode head, int val) {

        while (head != null && head.val == val) {
            ListNode delNode = head;
            head = head.next;
            delNode.next = null;
        }
        if (head == null) {
            return head;
        }
        //前置节点
        ListNode prev = head;
        while (prev.next != null) {
            if (prev.next.val == val) {
                ListNode delNode = prev.next;
                prev.next = delNode.next;
                delNode.next = null;
            } else {
                prev = prev.next;
            }
        }
        return head;
    }
}
