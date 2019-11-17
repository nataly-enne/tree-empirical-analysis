package binarySeacrhTree;

import java.util.Random;

public class BinarySearchMain {
    
    BinarySearchTree binaryTree = new BinarySearchTree();
    
    public int size;

    public BinarySearchMain(int size) {
        this.size = size;
    }

    public void insert() {
        long firstTime = System.currentTimeMillis();

        for (int i = 0; i < size; i++) {
            binaryTree.insert(i);
        }

        long lastTime   = System.currentTimeMillis();
        long time       = (lastTime - firstTime); // Duração em milissegundos.

        System.out.println("Árvore Binária de Busca [INSERÇÃO]: " + time + " ms");
    }

    public void search() {
        long firstTime = System.currentTimeMillis();

        for (int i = size - 1; i >= 0; i--) {
            binaryTree.search(i);
        }

        long lastTime   = System.currentTimeMillis();
        long time       = (lastTime - firstTime);

        System.out.println("Árvore Binária de Busca [BUSCA]: " + time + " ms");
    }

    public void remove() {
        long firstTime = System.currentTimeMillis();

        for (int i = size - 1; i >= 0; i--) {
            binaryTree.remove(i);
        }

        long lastTime   = System.currentTimeMillis();
        long time       = (lastTime - firstTime);

        System.out.println("Árvore Binária de Busca [REMOÇÃO]: " + time + " ms");
    }

    /* 
    * Parte que insere 500.000 elementos 1 entre 1 e 1.000.000 em ordem aleatória.
    * Busca 1.000.000 de elementos em ordem decrescente (de 1.000.000 até 1)
    * e remove 1.000.000 de elementos em ordem decrescente. 
    */

     public void randomInsert() {
        long firstTime = System.currentTimeMillis();

        for (int i = 0; i < size; i++) {
            int value;
            while (true) {
                value = new Random().nextInt(size * 2) + 1;
                if (!binaryTree.search(value))
                    break;
            }
            binaryTree.insert(value);
        }

        long lastTime   = System.currentTimeMillis();
        long time       = (lastTime - firstTime); // Duração em milissegundos.

        System.out.println("Árvore Binária de Busca [INSERÇÃO RANDÔMICA]: " + time + "ms");
    }

    public void decreasingSearch() {
        long firstTime = System.currentTimeMillis();

        for (int i = (size * 2) - 1; i >= 0; i--) {
            binaryTree.search(i);
        }

        long lastTime   = System.currentTimeMillis();
        long time       = (lastTime - firstTime);

        System.out.println("Árvore Binária de Busca [BUSCA DECRESCENTE]: " + time + "ms");
    }

    public void decreasingRemove() {
        long firstTime = System.currentTimeMillis();

        for (int i = (size * 2) - 1; i >= 0; i--) {
            binaryTree.remove(i);
        }

        long lastTime   = System.currentTimeMillis();
        long time       = (lastTime - firstTime);

        System.out.println("Árvore Binária de Busca [REMOÇÃO DECRESCENTE]: " + time + "ms");
    }

    public void testBS() {
        insert();
        search();
        remove();
    }

    public void secondTestBS() {
        randomInsert();
        decreasingSearch();
        decreasingRemove();
    }
}
