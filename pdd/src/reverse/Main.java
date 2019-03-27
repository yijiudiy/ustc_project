package reverse;
class Node {
    int value;
    Node next;
    Node(int k){
        this.value = k;
        this.next = null;
    }

}
public class Main {
    public static void main(String[] args) {
        Node a = new Node(1);
        Node b = new Node(2);
        Node c = new Node(3);
        a.next = b;
        b.next = c;

        for(Node p = a; p!=null; p=p.next){
            System.out.print(p.value + " ");
        }
        System.out.println();
        a = reverse(a);

        for(Node p = a; p!=null; p=p.next){
            System.out.print(p.value + " ");
        }

    }
    static Node reverse(Node head){
        Node newHead = null;
        Node cur = head;
        while(cur != null){
            Node q = cur.next;
            cur.next = newHead;
            newHead = cur;
            cur = q;
        }
        return newHead;
    }
}
