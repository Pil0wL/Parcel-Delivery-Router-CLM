

package parceldeliveryproject.ds;
import parceldeliveryproject.util.Platform;

public class DoubleyLinkedList { // I like to imagine this as a snake which head's facing the sun
    private Node head = null;
    private Node tail = null;
    public Node _WareHouse = null;
    
    public void _load(Platform Scope) {
        _WareHouse = _addFirst(1000);
    }
    public class Node { // think of each node as a location
        public int ZIP;

        // these two dictate the location in which the truck will go to
        public Node up = null;
        public Node down = null;

        // these two are the realistic route legs ( sorted by ZIP )
        public Node leftLocation = null;
        public Node rightLocation = null;

        public Node(int ZIP) {
            this.ZIP = ZIP;
        }


        @Override
        public String toString() {
            return String.format("[%d]", ZIP);
        }
    }
    private Node _addFirst(int ZIP) { // for creation of the _WareHouse
        Node created = new Node(ZIP);

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
    public final Node addFirst(int ZIP) {
        Node created = _addFirst(ZIP);

        if (ZIP < _WareHouse.ZIP) { // place this realistically (Sorted by ZIP) going toward left
            Node placeLeftOf = _WareHouse;
            while (true) {
                if (placeLeftOf.leftLocation != null) { // there is already a location at the target
                    if (ZIP < placeLeftOf.leftLocation.ZIP) { // the target's left location is greater than this ZIP; attempt to place there
                        placeLeftOf = placeLeftOf.leftLocation;
                    } else { 
                        // (the target's left location) is supposed to be to the left of this and (the target) is supposed to be the right of this
                        // ;place inbetween (the target) and (the target's left location)

                        Node ttll = placeLeftOf.leftLocation; //(the target's left location)

                        // this has our creation point to the two
                        created.leftLocation = ttll;
                        created.rightLocation = placeLeftOf;

                        // this has the two outer point to our creation
                        ttll.rightLocation = created;
                        placeLeftOf.leftLocation = created;

                        break;
                    }
                } else { // empty location AND our creation's ZIP is lesser; place here
                    placeLeftOf.leftLocation = created;
                    created.rightLocation = placeLeftOf;
                    break;
                }
            }
        } else { // this is going towards right, now; this code below is just the reflection of the above
            
            Node placeRightOf = _WareHouse;
            while (true) {
                if (placeRightOf.rightLocation != null) { // there is already a location at the target
                    if (placeRightOf.rightLocation.ZIP < ZIP) { // the target's right location is lesser than this ZIP; attempt to place there
                        placeRightOf = placeRightOf.rightLocation;
                    } else { 
                        // (the target's right location) is supposed to be to the right of this and (the target) is supposed to be the left of this
                        // ;place inbetween (the target's right location) and (the target)

                        Node ttrl = placeRightOf.rightLocation; //(the target's right location)

                        // this has our creation point to the two
                        created.rightLocation = ttrl;
                        created.leftLocation = placeRightOf;

                        // this has the two outer point to our creation
                        ttrl.leftLocation = created;
                        placeRightOf.rightLocation = created;

                        break;
                    }
                } else { // empty location AND our creation's ZIP is greater; place here
                    placeRightOf.rightLocation = created;
                    created.leftLocation = placeRightOf;
                    break;
                }
            }
        }

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
            Node down_node = node.down;
            if (down_node != null) down_node.up = node.up;
        }

        { // if this had a next node: set that node's prev to this one's prev
            Node up_node = node.up;
            if (up_node != null) up_node.down = node.down;
        }


        { // do the same thing for up but instead its the realistic route
            Node left_node = node.leftLocation;
            if (left_node != null) left_node.rightLocation = node.rightLocation;
        }

        { // do the same thing for down but instead its the realistic route
            Node right_node = node.rightLocation;
            if (right_node != null) right_node.leftLocation = node.leftLocation;
        }
    }



    public void printWhole() {
        Node current = head;
        while (current != null) {
            System.out.print(current + " <-> ");
            current = current.down;
        }
    }

}

    
