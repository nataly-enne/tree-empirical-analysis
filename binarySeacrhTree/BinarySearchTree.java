package binarySeacrhTree;

public class BinarySearchTree {
    private BinarySearchNode root = null;

    public boolean isEmpty() {
        return root == null;
    }

    public BinarySearchNode getRoot() {
        return root;
    }

    public void insert(int value) {
        BinarySearchNode n = new BinarySearchNode(value);
        insert(n);
    }

    private void insert(BinarySearchNode node) {
        if (root == null) {
            root = node;
            return;
        }

        BinarySearchNode parent = root;

        while (true) {
            if (parent.getValue() == node.getValue()) {
                return;
            }

            if (node.getValue() < parent.getValue()) {
                if (parent.getLeft() == null) {
                    parent.setLeft(node);
                    break;
                } 
                else{
                    parent = parent.getLeft();
                    continue;
                }
            }

            if (parent.getRight() == null) {
                parent.setRight(node);
                break;
            } 
            else{
                parent = parent.getRight();
            }
        }
    }

    public boolean search(int key) {
        BinarySearchNode current = root;

        while (current != null) {
            if (key == current.getValue())  {
                return true;
            } 
            else if (key < current.getValue()) {
                current = current.getLeft();
            } 
            else{
                current = current.getRight();
            }
        }

        return false;
    }

    public void remove(int key) {
        if (root == null) {
            return;
        }

        BinarySearchNode parent  = null;
        BinarySearchNode current = root;

        while (true) {
            if (key > current.getValue()) {
                if (current.getRight() != null) {
                    parent  = current;
                    current = current.getRight();
                } 
                else {
                    break;
                }
            } 
            else if (key < current.getValue()) {
                if (current.getLeft() != null) {
                    parent = current;
                    current = current.getLeft();
                } 
                else {
                    break;
                }
            } 
            else {
                if (current.isLeaf()) {
                    if (parent == null) {
                        root = null;
                    } else if (parent.getLeft() == current) {
                        parent.setLeft(null);
                    } else {
                        parent.setRight(null);
                    }
                } 
                else if (current.getRight() == null) {
                    if (parent == null) {
                        root = current.getLeft();
                    } else if (parent.getLeft() == current) {
                        parent.setLeft(current.getLeft());
                    } else {
                        parent.setRight(current.getLeft());
                    }
                } 
                else if (current.getLeft() == null) {
                    if (parent == null) {
                        root = current.getRight();
                    } else if (parent.getLeft() == current) {
                        parent.setLeft(current.getRight());
                    } else {
                        parent.setRight(current.getRight());
                    }
                } 
                else {
                    int sucessorKey = getSucessorKey(current);
                    remove(sucessorKey);
                    current.setValue(sucessorKey);
                }
                break;
            }
        }
    }

    private int getSucessorKey(BinarySearchNode node) {
        BinarySearchNode max = node.getLeft();
        while (max.getRight() != null) {
            max = max.getRight();
        }
        return max.getValue();
    }
}
