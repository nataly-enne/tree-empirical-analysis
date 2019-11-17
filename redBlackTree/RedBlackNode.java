package redBlackTree;

class RedBlackNode<ValueType extends Comparable> {
   
    public static final int black = 0; // Possível cor pra esse nó
    public static final int red = 1; // Possível cor pra esse nó.

    public ValueType key; // Chave de cada nó.
   
    // Nó pai.
    RedBlackNode<ValueType> parent;

    // Filho da esquerda.   
    RedBlackNode<ValueType> left;
   
    // Filho da direita.
    RedBlackNode<ValueType> right;
   
    // Número de elementos à esquerda de cada nó.
    public int numberLeft = 0;

    // Número de elementos à direita de cada nó.
    public int numberRight = 0;

    // Cor de um nó.
    public int color;

    public RedBlackNode() { // Inicializando os atributos no construtor padrão.
        color = black;
        numberLeft = 0;
        numberRight = 0;
        parent = null;
        left = null;
        right = null;
    }

    public RedBlackNode(ValueType key) { // Construtor que define a chave passada como argumento.
        this();
        this.key = key;
    }
}