package redBlackTree;

import java.util.Random;

public class RedBlackMain {
    RedBlackTree treeRB = new RedBlackTree();
    public int size;

    public RedBlackMain(int size) {
        this.size = size;
    }

    public void insert() {
        long firstTime = System.currentTimeMillis();

        for (int i = 0; i < size; i++) {
            treeRB.insert(i);
        }

        long lastTime = System.currentTimeMillis();
        long time = (lastTime - firstTime); // Duração em milissegundos.

        System.out.println("Árvore Rubro Negra [INSERÇÃO]: " + time + " ms");
    }

    public void search() {
        long firstTime = System.currentTimeMillis();

        for (int i = size - 1; i >= 0; i--) {
            treeRB.search(i);
        }

        long lastTime = System.currentTimeMillis();
        long time = (lastTime - firstTime);

        System.out.println("Árvore Rubro Negra [BUSCA]: " + time + " ms");
    }

    public void remove() {
        long firstTime = System.currentTimeMillis();

        for (int i = size - 1; i >= 0; i--) {
            treeRB.remove(treeRB.search(i));
        }

        long lastTime = System.currentTimeMillis();
        long time = (lastTime - firstTime);

        System.out.println("Árvore Rubro Negra [REMOÇÃO]: " + time + " ms");
    }

    /*  Parte que insere 500.000 elementos 1 entre 1 e 1.000.000 em ordem aleatória.
Busca 1.000.000 de elementos em ordem decrescente (de 1.000.000 até 1)
e remove 1.000.000 de elementos em ordem decrescente. */

    public void randomInsert() {
        long firstTime = System.currentTimeMillis();

        for (int i = 0; i < size; i++) {
            int value;
            while (true) {
                value = new Random().nextInt(size * 2) + 1;
                if (treeRB.search(value) == null)
                    break;
            }
            treeRB.insert(value);
        }

        long lastTime = System.currentTimeMillis();
        long time = (lastTime - firstTime);

        System.out.println("Árvore Rubro Negra [INSERÇÃO RANDOÔMICA]: " + time + "ms");
    }

    public void decreasingSearch() {
        long firstTime = System.currentTimeMillis();

        for (int i = (size * 2) - 1; i >= 0; i--) {
            treeRB.search(i);
        }

        long lastTime = System.currentTimeMillis();
        long time = (lastTime - firstTime);

        System.out.println("Árvore Rubro Negra [BUSCA DECRESCENTE]: " + time + "ms");
    }

    public void decreasingRemove() {
        long firstTime = System.currentTimeMillis();

        for (int i = (size * 2) - 1; i >= 0; i--) {
            RedBlackNode node = treeRB.search(i);
            if (node != null)
                treeRB.remove(node);
        }

        long lastTime = System.currentTimeMillis();
        long time = (lastTime - firstTime);

        System.out.println("Árvore Rubro Negra [REMOÇÃO DECRESCENTE]: " + time + "ms");
    }

    public void testRB() {
        insert();
        search();
        remove();
    }

    public void secondTestRB(){
        randomInsert();
        decreasingSearch();
        decreasingRemove();
    }
}
