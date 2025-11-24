

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
    private void placeRealistically(Node created, int ZIP) {
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
    }
    public final Node addFirst(int ZIP) {
        Node created = _addFirst(ZIP);
        placeRealistically(created, ZIP);

        return created;
    }
    public Node addLast(int ZIP) {
        Node created = new Node(ZIP);

        if (tail == null) { // its empty
            head = created;
            tail = created;
            return created;
        }

        tail.down = created; 

        created.up = tail; // to replace the tail
        //created.down = null; // by default its null anyways

        tail = created;

        placeRealistically(created, ZIP);
        
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


    public int moveNodeLeftBy(Node thisNode, int n) {
        
        if (n < 1) {
            n = 1;
        }
        Node placeUpOf = thisNode;

        int total_moved = 0;
        for (int i = 0; i < n; i++) {
            Node temp = placeUpOf.up;
            if (temp == null) break;
            total_moved++;
            placeUpOf = temp;
        }
        if (total_moved == 0) return 0; // cannot move at all; this is already at maximum left

        if (thisNode == tail) tail = thisNode.up; // new tail

        // set the old location's two nodes that were next to each other to well... each other
        thisNode.up.down = thisNode.down; // target.up is guaranteed
        {
            Node targetsDown = thisNode.down;
            if (targetsDown != null) targetsDown.up = thisNode.up;
        }

        // set this node's links
        thisNode.up = placeUpOf.up;
        thisNode.down = placeUpOf;

        // set the destination's nodes to this
        {
            Node hasUp = placeUpOf.up;
            if (hasUp != null) hasUp.down = thisNode;
        }
        placeUpOf.up = thisNode; // has to be after the codeblock above

        if (placeUpOf == head) head = thisNode; // new head

        return total_moved;
    }

    public int moveNodeRightBy(Node thisNode, int n) { // again... just a reflection of the one above
        
        if (n < 1) {
            n = 1;
        }
        Node placeDownOf = thisNode;

        int total_moved = 0;
        for (int i = 0; i < n; i++) {
            Node temp = placeDownOf.down;
            if (temp == null) break;
            total_moved++;
            placeDownOf = temp;
        }
        if (total_moved == 0) return 0; // cannot move at all; this is already at maximum right

        if (thisNode == head) head = thisNode.down; // new head

        // set the old location's two nodes that were next to each other to well... each other
        thisNode.down.up = thisNode.up; // target.up is guaranteed
        {
            Node targetsUp = thisNode.up;
            if (targetsUp != null) targetsUp.down = thisNode.down;
        }

        // set this node's links
        thisNode.up = placeDownOf;
        thisNode.down = placeDownOf.down;

        // set the destination's nodes to this
        {
            Node hasDown = placeDownOf.down;
            if (hasDown != null) hasDown.up = thisNode;
        }
        placeDownOf.down = thisNode; // has to be after the codeblock above

        if (placeDownOf == tail) tail = thisNode; // new tail

        return total_moved;
    }


    public void printWhole(Node selectedNode) {
        System.out.println("");
        Node current = head;
        while (current != null) {
            if (current == tail) break;
            String to_print;
            if (current != selectedNode) {
                to_print = current.toString();
            } else {
                to_print = current.toString() + " (currently selected)";
            }
            System.out.print(to_print + " <-> ");
            current = current.down;
        }

        if (current != null) {
            if (current != selectedNode) { // current being actaully null is not possible if everything is correct; i just added it here so java can be quiet
                System.out.println(current.toString());
            } else {
                System.out.println(current.toString() + " (currently selected)");
            }
        }
    }


    public Node getHead() {
        return head;
    }
}

    
