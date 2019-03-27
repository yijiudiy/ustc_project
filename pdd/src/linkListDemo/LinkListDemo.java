package linkListDemo;

class LinkList{
    Node head;
    class Node{
        int value;
        Node next;

        int getValue(Node p){
            return p.value;
        }

        void setValue(Node p,int k){
            p.value = k;
        }

        Node getNext(Node p){
            return p.next;
        }

        void setNext(Node p, Node q){
            p.next = q;
        }

        Node(int value){
            this.value = value;
            this.next = null;
        }
    }
    void print(){
        Node p = head;
        while(p!=null){
            System.out.print(p.value + "  ");
            p = p.next;
        }
    }


}




public class LinkListDemo {

}
