

package parceldeliveryproject.ds;
import parceldeliveryproject.util.Platform;

public class DoubleyLinkedList { // I like to imagine this as a snake which head's facing the sun
    private Node head = null;
    private Node tail = null;
    public Node _WareHouse = null;
    
    public void _load(Platform Scope) {
        _WareHouse = addFirst(1000);
    }
    public class Node {
        public int ZIP;
        public Node up = null;
        public Node down = null;

        public Node(int ZIP) {
            this.ZIP = ZIP;
        }
    }
    public final Node addFirst(int value) {
        Node created = new Node(value);

        if (head == null) { // its empty
            head = created;
            tail = created;
            return created;
        }

        head.up = created; 

        created.down = head; // to replace the head
        //created.up = null; // by default its null anyways

        head = created;
        return created;
    }


   public void removeNode(Node node) {
        if (node == _WareHouse) {
            System.out.println("This is where the truck needs to go to!");
            return;
        }
        if (node == head) head = node.down;
        if (node == tail) tail = node.up;

        { // if this had a previous node: set that node's next to this one's next
            Node prev_node = node.down;
            if (prev_node != null) prev_node.up = node.up;
        }

        { // if this had a next node: set that node's prev to this one's prev
            Node next_node = node.up;
            if (next_node != null) next_node.down = node.down;
        }
    }

    public void printUpward() {
        Node current = tail;
        while (current != null) {
            System.out.print(current.ZIP + " ");
            current = current.up;
        }
        System.out.println();
    }

    public void printDownward() {
        Node current = head;
        while (current != null) {
            System.out.print(current.ZIP + " ");
            current = current.down;
        }
        System.out.println();
    }
}

    
