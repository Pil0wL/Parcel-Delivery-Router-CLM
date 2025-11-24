

package parceldeliveryproject.ds.BinarySearchTree;

import parceldeliveryproject.ds.StackX;

public class BSTZIP {


    private Node root = null;
    
    public class Node {
        public final int ZIP;
        public Node left;
        public Node right;

        public Node(int ZIP) {
            this.ZIP = ZIP;
        }
    }
    public void insert(int ZIP) {
        Node newNode = new Node(ZIP);

        if (root == null) {
            root = newNode;
            return;
        }

        Node current = root;
        Node parent = current; // this is supposed to be null, but seing how current will always be something, and the loop sets parent to current, i just pre-added it here

        while (current != null) {
            parent = current;

            if (ZIP < current.ZIP) {
                current = current.left;
            } else { // if it's a duplicate, it will always go right
                current = current.right;
            }
        }

        if (ZIP < parent.ZIP) {
            parent.left = newNode;
        } else {
            parent.right = newNode;
        }
    }

    public boolean search(Node root, int ZIP) {
        Node current = root;

        while (current != null) {
            if (ZIP == current.ZIP) {
                return true;
            }

            if (ZIP < current.ZIP) {
                current = current.left;
            } else {
                current = current.right;
            }
        }

        return false;
    }


    public void remove(int ZIP) {
        root = removeIterative(root, ZIP); // update root if needed
    }

    private Node removeIterative(Node root, int ZIP) {
        Node parent = null;
        Node current = root;

        while (current != null && current.ZIP != ZIP) {
            parent = current;
            if (ZIP < current.ZIP) {
                current = current.left;
            } else {
                current = current.right;
            }
        }

        if (current == null) return root; // ZIP not found

        
        if (current.left == null || current.right == null) { // node has atleast one child
            // left = true, right = false -> proceed
            // left = false, right = false -> this has two children
            Node newChild = (current.left != null) ? current.left : current.right;

            if (parent == null) return newChild; // deleting root

            if (parent.left == current) parent.left = newChild;
            else parent.right = newChild;
        } else { // node has two children
            // Find inorder successor (smallest in right subtree)
            Node succParent = current;
            Node succ = current.right;
            while (succ.left != null) {
                succParent = succ;
                succ = succ.left;
            }

            // Detach successor from its parent
            if (succParent != current) {
                succParent.left = succ.right; 
                succ.right = current.right;   // attach right child safely
            }

            succ.left = current.left;

            if (parent == null) return succ; // root deleted

            if (parent.left == current) parent.left = succ;
            else parent.right = succ;
        }

        return root;
    }

    public void printWhole() {
        if (root == null) {
            System.out.println("The ZIP index is empty!");
            return;
        }

        StackX<Node> stack = new StackX<>(128); // pretty sure it wont exceed this hopefully :D
        Node current = root;

        System.out.println("All ZIP codes (in order):");

        while (current != null || !stack.isEmpty()) {
            // Go left as far as possible
            while (current != null) {
                if (stack.isFull()) {
                    System.out.println("\nStack is full! Cannot continue traversal!");
                    return;
                }
                stack.push(current);
                current = current.left;
            }

            // Visit node
            current = stack.pop();
            System.out.print(current.ZIP + " ");

            // Go right
            current = current.right;
        }

        System.out.println();
    }
}

    
