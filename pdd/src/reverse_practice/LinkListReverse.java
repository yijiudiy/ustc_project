package reverse_practice;
class LinkNode{
    int value;
    LinkNode next;
    LinkNode(int value){
        this.value = value;
        this.next = null;
    }
}
public class LinkListReverse {
    public static LinkNode reverse(LinkNode head){
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
        LinkNode a = new LinkNode(1);
        LinkNode b = new LinkNode(3);
        LinkNode c = new LinkNode(6);
        LinkNode d = new LinkNode(5);
        a.next = b;
        b.next = c;
        c.next = d;
        LinkNode p = a;
        while(p != null){
            System.out.print(p.value + " ");
            p = p.next;
        }
        System.out.println();
        a = reverse(a);
        p = a;
        while(p != null){
            System.out.print(p.value + " ");
            p = p.next;
        }
    }
}
