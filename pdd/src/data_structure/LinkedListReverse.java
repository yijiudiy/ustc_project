package data_structure;

public class LinkedListReverse {
    static class ListNode{
        int value;
        ListNode next;
        ListNode(int value){
            this.value = value;
            this.next = null;
        }
    }
    public static ListNode reverse(ListNode head){
        if(head == null){
            return null;
        }
        ListNode newHead = null;
        ListNode cur = head;
        while(cur!=null){
            ListNode q = cur.next;
            cur.next = newHead;
            newHead = cur;
            cur = q;
        }
        return newHead;
    }

    public static void main(String[] args) {
        ListNode a = new ListNode(1);
        ListNode b = new ListNode(2);
        ListNode c = new ListNode(3);
        ListNode d = new ListNode(4);
        a.next = b;
        b.next = c;
        c.next = d;
        ListNode head = a;
        while(head!=null){
            System.out.print(head.value + " ");
            head = head.next;
        }
        System.out.println();


        a = reverse(a);


        while(a!=null){
            System.out.print(a.value + " ");
            a = a.next;
        }
    }
}
