package binarySeacrhTree;

class BinarySearchNode {

    private BinarySearchNode left;
    private BinarySearchNode right;
    private int value;

    public BinarySearchNode(int value) {
        this.value = value;
    }

    public BinarySearchNode getLeft() {
        return left;
    }

    public BinarySearchNode getRight() {
        return right;
    }

    public int getValue() {
        return value;
    }

    public void setLeft(BinarySearchNode left) {
        this.left = left;
    }

    public void setRight(BinarySearchNode right) {
        this.right = right;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public boolean isLeaf()
    {
        return left == null && right == null;
    }
}
