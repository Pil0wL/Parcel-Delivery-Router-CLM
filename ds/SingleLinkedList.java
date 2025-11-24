

package parceldeliveryproject.ds;
import parceldeliveryproject.model.Parcel;

public class SingleLinkedList {
    private Node head = null;

    public class Node {
        public Parcel deliveredParcel;

        public Node next = null;

        public Node(Parcel deliveredParcel) {
            this.deliveredParcel = deliveredParcel;
        }


        @Override
        public String toString() {
            return String.format("[%s]", deliveredParcel);
        }
    }

    public Node add(Parcel deliveredParcel) {
        Node created = new Node(deliveredParcel);

        if (head == null) { // its empty
            head = created;
            return created;
        }

        created.next = head; // usurp the head
        head = created;

        return created;
    }
    
    public void printWhole() {
        if (head == null) {
            System.out.println("The delivery log is empty");
            return;
        }
        System.out.println("");
        Node current = head;
        while (current.next != null) {
            System.out.print(current + " <- ");
            current = current.next;
        }

        System.out.print(current);
    }
}

    
