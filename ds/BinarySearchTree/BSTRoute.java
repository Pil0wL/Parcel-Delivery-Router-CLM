

package parceldeliveryproject.ds.BinarySearchTree;

import parceldeliveryproject.util.Platform;
import parceldeliveryproject.ds.DoubleyLinkedList;

public class BSTRoute {


    private Node root = null;
    
    public void _load(Platform Scope) {
        root = new Node(Scope.RouteDLL._WareHouse);
    }
    
    public class Node {
        public DoubleyLinkedList.Node location;
        public Node left = null;
        public Node right = null;

        public Node(DoubleyLinkedList.Node location) {
            this.location = location;
        }
    }
    public Node add(DoubleyLinkedList.Node location) {
        Node created = new Node(location);


        int currentZIP = location.ZIP;
        Node current = root;
        while (current != null) {
            if (currentZIP < current.location.ZIP) { // if the value is lesser than the current node, attempt to add it to the left
                if (root.left != null) { // if there is already a node try to add it their instead
                    current = root.left;
                } else {
                    root.left = created;
                }
            } else { // ...otherwise try to add it to the right
                if (root.right != null) {
                    current = root.right;
                } else {
                    root.right = created;
                }
            }
        }
        return created;
    }
    public void remove(DoubleyLinkedList.Node location) {
        if (location == root.location) {
            System.out.println("This is where the truck needs to go to!");
            return;
        }

        int targetZip = location.ZIP;
        Node parentNode = root;
        while(true) { // initiating a search for this location
            Node nextNode;
            if (parentNode.location.ZIP < targetZip) {
                nextNode = parentNode.left;
            } else {
                nextNode = parentNode.right;
            }

            if (nextNode == null) {
                System.out.println("This location does not exist!");
                return;
            }
            if (nextNode.location == location) break;
            parentNode = nextNode;
        }

        /*
        boolean hasLeftNode = target.left != null;
        if (hasLeftNode && (target.right != null)) {

        }
        */
    }
}

    
