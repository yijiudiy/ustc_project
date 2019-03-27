package csdnReverse;

import java.util.List;

class LinkedListReverse {

    static class ListNode{
        int data;
        ListNode next;
        public ListNode(int data){
            this.data = data;
        }
    }

    public static ListNode reverse(ListNode head){
        if (head == null){
            return null;
        }
        ListNode cur = head;
        ListNode p = head;  // p 用来记录插入节点的下一个节点
        ListNode newHead = null; // 新链表还没有，当然是空
        while(cur != null){  // cur 用来遍历单链表
            p = cur.next;     // 首先用p记录当前节点的下一个节点。
            cur.next = newHead;  // 让当前节点指向新链表的头结点。
            newHead = cur;      // 更新新链表的头结点
            cur = p;            // 更新cur
        }

        return newHead;
    }

    public static void main(String[] args) {
        // 测试
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        node1.next = node2;
        node2.next = node3;
        ListNode p = node1;
        while(p!=null){
            System.out.print(p.data + " ");
            p = p.next;
        }
        ListNode head = new ListNode(0);
        head = reverse(node1);
        System.out.print(head.data+" "+head.next.data+" "+head.next.next.data);
    }

}
