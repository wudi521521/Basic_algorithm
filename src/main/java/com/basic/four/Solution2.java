package com.basic.four;

/**
 * @author Dillon Wu
 * @Description: 递归删除链表
 * @date 2021/6/22 15:29
 */
public class Solution2 {

    public ListNode removeElements(ListNode head, int val) {
        if (head == null) {
            return head;
        }
        ListNode res = removeElements(head.next, val);
        if (head.val == val) {
            return res;
        } else {
            head.next = res;
            return head;
        }
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 5, 6};
        ListNode head = new ListNode(nums);
        System.out.println(head);
        ListNode res = (new Solution2()).removeElements(head, 6);
        System.out.println(res);
    }
}
