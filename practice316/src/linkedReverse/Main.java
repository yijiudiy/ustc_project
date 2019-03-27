package linkedReverse;

import java.io.LineNumberInputStream;
import java.util.LinkedList;
class LinkNode{
    int value;
    LinkNode next;
    LinkNode(int value){
        this.value = value;
        this.next = null;
    }
}
public class Main {
    static LinkNode reverse(LinkNode head){
        LinkNode newHead = null;
        LinkNode cur = head;
        while(cur!=null){
            LinkNode q = cur.next;
            cur.next = newHead;
            newHead = cur;
            cur = q;
        }
        return newHead;
    }
    public static void main(String[] args) {
        LinkNode a = new LinkNode(3);
        LinkNode b = new LinkNode(8);
        LinkNode c = new LinkNode(9);
        LinkNode d = new LinkNode(999);
        a.next = b;
        b.next = c;
        c.next = d;
        LinkNode p = a;
        while(p!=null){
            System.out.print(p.value + " ");
            p = p.next;
        }
        System.out.println();

        LinkNode head = reverse(a);
        p = head;
        while(p!=null){
            System.out.print(p.value + " ");
            p= p.next;
        }
    }
}
