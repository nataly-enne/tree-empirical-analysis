package avlTree;

import java.util.Random;

import misc.Pessoa;

public class AVLMain {
    
    AVLTree<Pessoa> avl = new AVLTree<>();
    
    public int size;

    public AVLMain(int size) {
        this.size = size;
    }

    public void insert() {
        long firstTime = System.currentTimeMillis();

        for (int i = 0; i < size; i++) {
            avl.insert(new Pessoa(i));
        }

        long lastTime = System.currentTimeMillis();
        long time = (lastTime - firstTime); // Duração em milissegundos.

        System.out.println("ÁRVORE AVL [INSERÇÃO]: " + time + " ms");
    }

    public void search() {
        long firstTime = System.currentTimeMillis();

        for (int i = size - 1; i >= 0; i--) {
            avl.search(i);
        }

        long lastTime = System.currentTimeMillis();
        long time = (lastTime - firstTime);

        System.out.println("ÁRVORE AVL [BUSCA]: " + time + " ms");
    }

    public void remove() {
        long firstTime = System.currentTimeMillis();

        for (int i = size - 1; i >= 0; i--) {
            avl.remove(i);
        }

        long lastTime = System.currentTimeMillis();
        long time = (lastTime - firstTime);

        System.out.println("ÁRVORE AVL [REMOÇÃO]: " + time + " ms");
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
                if (avl.search(value) == null)
                    break;
            }
            avl.insert(new Pessoa(value));
        }

        long lastTime = System.currentTimeMillis();
        long time = (lastTime - firstTime); // Duração em milissegundos.

        System.out.println("Árvore AVL [INSERÇÃO RANDÔMICA]: " + time + "ms");
    }

    public void decreasingSearch() {
        long firstTime = System.currentTimeMillis();

        for (int i = (size * 2) - 1; i >= 0; i--) {
            avl.search(i);
        }

        long lastTime = System.currentTimeMillis();
        long time = (lastTime - firstTime);

        System.out.println("Árvore AVL [BUSCA DECRESCENTE]: " + time + "ms");
    }

    public void decreasingRemove() {
        long firstTime = System.currentTimeMillis();

        for (int i = (size * 2) - 1; i >= 0; i--) {
            avl.remove(i);
        }

        long lastTime = System.currentTimeMillis();
        long time = (lastTime - firstTime);

        System.out.println("Árvore AVL [REMOÇÃO DECRESCENTE]: " + time + "ms");
    }

    public void testAVL() {
        insert();
        search();
        remove();
    }

    public void secondTestAVL(){
        randomInsert();
        decreasingSearch();
        decreasingRemove();
    }
}
