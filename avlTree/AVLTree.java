package avlTree;

import misc.Indexable;

public class AVLTree<ValueType extends Indexable> {

    private AVLNode<ValueType> root;

    public void insert(ValueType value) {
        root = insert(new AVLNode<ValueType>(value), root);
    }

    public void remove(ValueType value) {
        root = remove(value.getKey(), root);
    }

    public void remove(int key) {
        root = remove(key, root);
    }

    public boolean isBalanced() {
        if (root == null) {
            return true;
        }

        return Math.abs(root.getBalanceFactor()) <= 1;
    }

    public ValueType search(int key) {
        return search(key, root);
    }

    private ValueType search(int key, AVLNode<ValueType> node) {
        if (node == null) {
            return null;
        }
        if (key < node.getValue().getKey()) {
            return search(key, node.getLeft());
        } 
        else if (key > node.getValue().getKey()) {
            return search(key, node.getRight());
        }

        return node.getValue();
    }

    private AVLNode<ValueType> remove(int key, AVLNode<ValueType> node) {
        if (node == null) {
            return null;
        }

        if (key < node.getValue().getKey()) {
            node.setLeft(remove(key, node.getLeft()));
        } 
        else if (key > node.getValue().getKey()) {
            node.setRight(remove(key, node.getRight()));
        } 
        else {
            if (node.getLeft() == null) {
                return balance(node.getRight());
            } 
            else if (node.getRight() == null){
                return balance(node.getLeft());
            }
            int balanceFactor = node.getBalanceFactor();
            if (balanceFactor < 0) {
                AVLNode<ValueType> sucessor = getMax(node.getLeft());
                node.setValue(sucessor.getValue());
                node.setLeft(remove(sucessor.getValue().getKey(), node.getLeft()));
            } else {
                AVLNode<ValueType> sucessor = getMin(node.getRight());
                node.setValue(sucessor.getValue());
                node.setRight(remove(sucessor.getValue().getKey(), node.getRight()));
            }
        }

        return balance(node);
    }

    private AVLNode<ValueType> getMin(AVLNode<ValueType> root) {
        AVLNode<ValueType> min = root;
        while (min.getLeft() != null) {
            min = min.getLeft();
        }

        return min;
    }

    private AVLNode<ValueType> getMax(AVLNode<ValueType> root) {
        AVLNode<ValueType> max = root;
        while (max.getRight() != null) {
            max = max.getRight();
        }
        return max;
    }

    private AVLNode<ValueType> insert(AVLNode<ValueType> node, AVLNode<ValueType> parent) {
        if (parent == null) {
            return node;
        }
        if (node.getValue().getKey() == parent.getValue().getKey()) {
            parent.setValue(node.getValue());
            return parent;
        }
        if (node.getValue().getKey() < parent.getValue().getKey()) {
            parent.setLeft(insert(node, parent.getLeft()));
        } 
        else {
            parent.setRight(insert(node, parent.getRight()));
        }

        return balance(parent);
    }

    private AVLNode<ValueType> balance(AVLNode<ValueType> parent) {
        if (parent == null) {
            return null;
        }

        if (parent.getBalanceFactor() >= -1 && parent.getBalanceFactor() <= 1) {
            return parent;
        }

        if (parent.getBalanceFactor() > 1) {
            // L
            if (parent.getLeft().getBalanceFactor() > 0) {
                // LL
                return rotateRight(parent);
            } 
            else{
                // LR
                parent.setLeft(rotateLeft(parent.getLeft()));
                return rotateRight(parent);
            }
        } 
        else{
            // R
            if (parent.getRight().getBalanceFactor() < 0) {
                // RR
                return rotateLeft(parent);
            } else {
                // RL
                parent.setRight(rotateRight(parent.getRight()));
                return rotateLeft(parent);
            }
        }
    }

    private AVLNode<ValueType> rotateRight(AVLNode<ValueType> parent) {
        AVLNode<ValueType> newRoot = parent.getLeft();
        parent.setLeft(newRoot.getRight());
        newRoot.setRight(parent);

        return newRoot;
    }

    private AVLNode<ValueType> rotateLeft(AVLNode<ValueType> parent) {
        AVLNode<ValueType> newRoot = parent.getRight();
        parent.setRight(newRoot.getLeft());
        newRoot.setLeft(parent);

        return newRoot;
    }
}
