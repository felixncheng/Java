package com.chengmboy.algorithms;

/**
 * @author cheng_mboy
 */
public class AddTwoNumbers {

    public static void main(String[] args) {
        ListNode l1 = new ListNode(2);
        ListNode l2 = new ListNode(4);
        ListNode l3 = new ListNode(3);
        l1.next = l2;
        l2.next = l3;

        ListNode l4 = new ListNode(5);
        ListNode l5 = new ListNode(6);
        ListNode l6 = new ListNode(4);
        l4.next = l5;
        l5.next = l6;

        AddTwoNumbers twoNumbers = new AddTwoNumbers();
        ListNode listNode = twoNumbers.addTwoNumbersPerfect(l1, l4);
        while (listNode != null) {
            System.out.print(listNode.val);
            listNode = listNode.next;
        }
    }

    private int getNum(ListNode l) {
        int num = 0;
        int i = 1;
        while (l != null) {
            num += l.val * i;
            l = l.next;
            i = i * 10;
        }
        return num;
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int num1 = getNum(l1);
        int num2 = getNum(l2);
        int r = num1 + num2;
        ListNode l3 = null;
        ListNode l4 = null;
        while (r > 0) {
            int n = r % 10;
            r = r / 10;
            ListNode l = new ListNode(n);
            if (l3 == null) {
                l4 = l3 = l;
            } else {
                l3.next = l;
                l3 = l3.next;
            }
        }
        return l4;
    }


    public ListNode addTwoNumbersPerfect(ListNode l1, ListNode l2) {
        ListNode head = new ListNode(0);
        ListNode p = l1, q = l2, curr = head;
        int carry = 0;
        while (p != null || q != null) {
            int x = (p != null) ? p.val : 0;
            int y = (q != null) ? q.val : 0;
            int sum = carry + x + y;
            carry = sum / 10;
            curr.next = new ListNode(sum % 10);
            curr = curr.next;
            if (p != null) p = p.next;
            if (q != null) q = q.next;
        }
        if (carry > 0) {
            ListNode l = new ListNode(carry);
            curr.next = l;
        }
        return head.next;
    }
}

class ListNode {

    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }
}

