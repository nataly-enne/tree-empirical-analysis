import binarySeacrhTree.BinarySearchMain;
import avlTree.AVLMain;
import redBlackTree.RedBlackMain;

public class Main {
    public static final int size = 10;

    public static void main(String[] args) {
        System.out.println("Análise empírica para " + size + " entradas.");
        System.out.println("---------------------------------------");

        BinarySearchMain binary = new BinarySearchMain(size);
        AVLMain balanced = new AVLMain(size);
        RedBlackMain redBlack = new RedBlackMain(size);

        System.out.println("PRIMEIRO TESTE");
        System.out.println("---------------------------------------");

        binary.testBS();
        balanced.testAVL();
        redBlack.testRB();

        System.out.println("---------------------------------------");
        System.out.println("SEGUNDO TESTE");
        System.out.println("---------------------------------------");

        binary.secondTestBS();
        balanced.secondTestAVL();
        redBlack.secondTestRB();

    }
}
