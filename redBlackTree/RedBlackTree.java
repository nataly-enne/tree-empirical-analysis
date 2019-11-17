package redBlackTree;

import java.util.ArrayList;
import java.util.List;

/*  Código da RED BLACK TREE baseado em:
*   https://en.wikipedia.org/wiki/Red%E2%80%93black_tree
*   https://github.com/phishman3579/java-algorithms-implementation/blob/master/src/com/jwetherell/algorithms/data_structures/RedBlackTree.java
*   http://users.cis.fiu.edu/~weiss/dsaajava/code/DataStructures/RedBlackTree.java
*/

public class RedBlackTree<ValueType extends Comparable> {
   
    private  RedBlackNode<ValueType> nada = new RedBlackNode<>();
    
    // Inicializa a raiz com "nada".
    private  RedBlackNode<ValueType> root = nada;

    //  Atribuimos "nada" para os nós.
    public RedBlackTree() {  
        root.left = nada;
        root.right = nada;
        root.parent = nada;
    }

    // ""x"" é o nó no qual o leftTurn() deve ser executado.
    // Executa um "giro à esquerda" em torno de x.
    private void leftTurn(RedBlackNode<ValueType> x) {
        
            
        // Chama o leftTurnFix() que atualiza o "numberLeft" e valores do numberRight.
        leftTurnFix(x);
        
        
        // Executa a rotação à esquerda.
        RedBlackNode<ValueType> y;
        y = x.right;
        x.right = y.left;
        

        /* 
        * isNil é uma função que retornará true se o conteúdo da instância do elemento for nulo.
        */

        // Verifica se ha existência de 'y.left' e faz as alterações.
        if (!isNil(y.left))
            y.left.parent = x;
            y.parent = x.parent;
        
        if (isNil(x.parent)) // Verifica  se o pai de "x" é nulo.
            root = y;
        else if (x.parent.left == x) // Senão for nulo, "x" é o filho da  esquerda de seu pai.
            x.parent.left = y;
        // Senão, "x" é o filho da direita de seu pai.
        else  
            x.parent.right = y;
            
        y.left = x;
        x.parent = y;
    }

    // ""x"" é o nó no qual o leftTurn() deve ser executado.
    // Aqui atualiza os valores de numberLeft e de numberRight afetados por leftTurn().
    private void leftTurnFix(RedBlackNode x) {

        // Caso 1: SE apenas x, x.right e x.right.right nem sempre são nulos.
        if (isNil(x.left) && isNil(x.right.left)) {
            x.numberLeft        = 0;
            x.numberRight       = 0;
            x.right.numberLeft  = 1;
        }
        // Caso 2: x.right.left também existe (além do caso 1)
        else if (isNil(x.left) && !isNil(x.right.left)) {
            x.numberLeft        = 0;
            x.numberRight       = 1 + x.right.left.numberLeft + x.right.left.numberRight;
            x.right.numberLeft  = 2 + x.right.left.numberLeft + x.right.left.numberRight;
        }
        // Caso 3: x.left tabmém existe (além do caso 1)
        else if (!isNil(x.left) && isNil(x.right.left)) {
            x.numberRight       = 0;
            x.right.numberLeft  = 2 + x.left.numberLeft + x.left.numberRight;
        }
        // Caso 4: x.left e x.right.left existem 
        else{
            x.numberRight       = 1 + x.right.left.numberLeft + x.right.left.numberRight;
            x.right.numberLeft  = 3 + x.left.numberLeft + x.left.numberRight + x.right.left.numberLeft + x.right.left.numberRight;
        }
    }


    // ""x"" é o nó no qual o rightTurn() deve ser executado.
    // Atualiza os valores numberLeft e numberRight.
    private void rightTurn(RedBlackNode<ValueType> y) {
        

        // Chama o rightTurnFix para ajustar os valores de numberRight e numberLeft
        rightTurnFix(y);
        

        // Execute a rotação.
        RedBlackNode<ValueType> x = y.left;
        y.left = x.right;

        // Verifica a existencia de x.right
        if (!isNil(x.right))
            x.right.parent = y;
            x.parent = y.parent;
        if (isNil(y.parent)) // Se y.parent "é nada".
            root = x;
        else if (y.parent.right == y) // Senão, se y é o filho da direita do pai
            y.parent.right = x;
        // Senão, y é o filho da esquerda de seu pai.
        else
            y.parent.left = x;
            x.right = y;
            y.parent = x;
    }

    // ""y"" é o nó em torno do qual o righTurn() deve ser executado.
    // Atualiza os valores numLeft e numRight afetados pela rotação
    private void rightTurnFix(RedBlackNode y) {


        // Caso 1: SE apenas y, y.left e y.left.left existem.
        if (isNil(y.right) && isNil(y.left.right)) {
            y.numberRight       = 0;
            y.numberLeft        = 0;
            y.left.numberRight  = 1;
        }
        // Caso 2: y.left.right também existe (além do caso 1)
        else if (isNil(y.right) && !isNil(y.left.right)) {
            y.numberRight       = 0;
            y.numberLeft        = 1 + y.left.right.numberRight + y.left.right.numberLeft;
            y.left.numberRight  = 2 + y.left.right.numberRight + y.left.right.numberLeft;
        }
        // Caso 3: y.right também existe (além do caso 1)
        else if (!isNil(y.right) && isNil(y.left.right)) {
            y.numberLeft        = 0;
            y.left.numberRight  = 2 + y.right.numberRight +y.right.numberLeft;
        }
        // Caso 4: y.right e y.left.right existem (além do caso 1)
        else {
            y.numberLeft        = 1 + y.left.right.numberRight + y.left.right.numberLeft;
            y.left.numberRight  = 3 + y.right.numberRight + y.right.numberLeft + y.left.right.numberRight + y.left.right.numberLeft;
        }
    }

    public void insert(ValueType key) {
        insert(new RedBlackNode<ValueType>(key));
    }


    //  ""z"" é o nó a ser inserido na Árvore enraizada na raiz
    // Insere z na posição apropriada na RedBlackTree enquanto atualiza os valores numberLeft e numberRight
    private void insert(RedBlackNode<ValueType> z) {
        
        // Cria uma referência pra root e inicializa um nó com "nada"
        RedBlackNode<ValueType> y = nada;
        RedBlackNode<ValueType> x = root;

        
        // Enquanto não chegar no final da árvore, continua tentando descobrir pra onde z deve ir
        while (!isNil(x)) {
            y = x;
            if (z.key.compareTo(x.key) < 0) { // Se z.key for menor que a chave atual, vá para a esquerda
                // Incrementa x.numberLeft já que z é maior que x
                x.numberLeft++;
                x = x.left;
            }           
            // Senão, z.key é maior (ou igual) a x.key, logo vá para a direita.
            else{
                x.numberRight++;
                x = x.right;
            }
        }
        
        // y recebe o pai de z.
        z.parent = y;
        
        // Dependendo do valor de y.key, coloca z à esquerda ou filho da direita de y.
        if (isNil(y))
            root    = z;
        else if (z.key.compareTo(y.key) < 0)
            y.left  = z;
        else
            y.right = z;
            
            // Inicializa os filhos de z em "nada" e a cor de z em vermelho.
            z.left  = nada;
            z.right = nada;
            z.color = RedBlackNode.red;
            
            // Chama insertFix()
            insertFix(z);
        }

    // ""z"" é o nó que foi inserido e pode ter causado alguma violação das propriedades da Árvore Rubro Negra.
    // Aqui corrige as violações das propriedades da Árvore Rubro Negra que podem ter ocorrido durante a inserção (z).
    private void insertFix(RedBlackNode<ValueType> z) {
        
        RedBlackNode<ValueType> y = nada;
        
        // Enquanto haja uma violação das propriedades da Árvore R.N., faça:
        while (z.parent.color == RedBlackNode.red) {
            
            // Se o pai de z é o filho esquerdo dele.
            if (z.parent == z.parent.parent.left) {
                y = z.parent.parent.right;
                
                // Caso 1: se y é vermelho, recolorir.
                if (y.color == RedBlackNode.red) {
                   
                    z.parent.color          = RedBlackNode.black;
                    y.color                 = RedBlackNode.black;
                    z.parent.parent.color   = RedBlackNode.red;
                    z = z.parent.parent;
                }
                // Caso 2: se y é preto e z é um filho da direita
                else if (z == z.parent.right) {
                    z = z.parent;
                    leftTurn(z);
                }
                // Caso 3: senão y é preto e z é filho da esquerda
                else{
                    // Recolore e ""gira"" o avô de z
                    z.parent.color = RedBlackNode.black;
                    z.parent.parent.color = RedBlackNode.red;
                    rightTurn(z.parent.parent);
                }
            }
            // Se o pai de z é o filho da direita dele.
            else{
                y = z.parent.parent.left;

                // Caso 1: se y é vermelho, recolorir.
                if (y.color == RedBlackNode.red) {
                    z.parent.color = RedBlackNode.black;
                    y.color = RedBlackNode.black;
                    z.parent.parent.color = RedBlackNode.red;
                    z = z.parent.parent;
                }
                // Caso 2: senão, y é preto e z é filho da esquerda.
                else if (z == z.parent.left) {
                    z = z.parent;
                    rightTurn(z); // Passa o pai de z
                }
                // Caso 3: senão, y é preto e z é um filho da direita.
                else{
                    // Recolore e "gira" em torno do avô de z
                    z.parent.color          = RedBlackNode.black;
                    z.parent.parent.color   = RedBlackNode.red;
                    leftTurn(z.parent.parent);
                }
            }
        }
        // Cor da raiz sempre preta.
        root.color = RedBlackNode.black;
    }


    // ""node"" é o nó com a menor chave enraizada no nó
    public RedBlackNode<ValueType> treeMin(RedBlackNode<ValueType> node) { 
        // Enquanto houver uma chave menor, continua indo para a esquerda.
        while (!isNil(node.left))
            node = node.left;
        return node;
    }
    // ""x"", um RedBlackNode cujo sucessor devemos encontrar.
    // return é o nó com a próxima maior chave (de x.key).
    public RedBlackNode<ValueType> treeSuccessor(RedBlackNode<ValueType> x) {
        
        // Se x.left não for nulo, chama treeMin(x.right) e retorna seu valor.
        if (!isNil(x.left) ) return treeMin(x.right);

        RedBlackNode<ValueType> y = x.parent;

        // Enquanto x é o filho da direita
        while (!isNil(y) && x == y.right) {
            // Continue subindo na árvore
            x = y;
            y = y.parent;
        }
        // Retorna o sucessor
        return y;
    }

    // ""z"" é o RedBlackNode que deve ser removido da árvore
    // Remove o z do RedBlackTree enraizado na raiz.
    public void remove(RedBlackNode<ValueType> v) {
        
        RedBlackNode<ValueType> z = search(v.key);
        
        RedBlackNode<ValueType> x = nada;
        RedBlackNode<ValueType> y = nada;
        
        // Se um dos filhos de z é nulo, remove z.
        if (isNil(z.left) || isNil(z.right)) y = z;
        
        // Senão, remove o sucessor de z.
        else y = treeSuccessor(z);
        
        // Seja "x" o filho da esquerda ou direita de y (y pode ter apenas um filho)
        if (!isNil(y.left)) x = y.left;
        
        else x = y.right;
        
        // linka o pai de x ao pai de y
        x.parent = y.parent;
        
        // Se o pai de y for "nada", x é a raiz.
        if (isNil(y.parent)) root = x;
        
        // Senão se y for um filho da esquerda, defina x como o irmão da esquerda de y
        else if (!isNil(y.parent.left) && y.parent.left == y)
            y.parent.left = x;
        // Senão se y é um filho da direita, defina x como o irmão da direita de y
        else if (!isNil(y.parent.right) && y.parent.right == y)
            y.parent.right = x;
        
        // se y != z, transfere os dados chave de y para z.
        if (y != z) z.key = y.key;

        // Atualiza os números numberLeft e numberRight que podem ser necessários.
        // Atualizando devido à exclusão do z.key.
        fixNodeData(x,y);

        // Se a cor de y for preta, é uma violação das propriedades da Árvore R.N., então chama o deleteFix ()
        if (y.color == RedBlackNode.black) deleteFix(x);
    }

    // y é um RedBlackNode que foi realmente excluído da árvore.
    // key é o valor da chave que costumava estar em y.
    private void fixNodeData(RedBlackNode<ValueType> x, RedBlackNode<ValueType> y) {
        
        // Inicializando duas variáveis ​​que ajudarão a percorrer a Árvore R.N.
        RedBlackNode<ValueType> current = nada;
        RedBlackNode<ValueType> track = nada;
        
         // Se x for nulo, dá inicio a atualização em y.parent
        // Seta a faixa como y, filho de y.parent.
        if (isNil(x)) {
            current = y.parent;
            track = y;
        }
        // Se x não for "nada", então começa a atualizar em x.parent.
        else{
            current = x.parent;
            track = x;
        }
        // Enquanto não chega na raiz, faça:
        while (!isNil(current)) {
            // Se o nó que foi excluido tiver uma chave diferente do nó atual.
            if (y.key != current.key) {
                // Se o nó que foi excluído for maior que current.key, então decrementa current.numberRight
                if (y.key.compareTo(current.key) > 0) current.numberRight--;
                // Se o nó que foi exclído for menor que current.key, então decrementa current.numberLeft.
                if (y.key.compareTo(current.key) < 0) current.numberLeft--;
            }
            // Senão, o nó que foi excluído tiver a mesma chave que o nó atual que estava sendo verificando.
            else{

                // Os casos em que o nó atual possui filhos nulos (nil/nada) e atualiza adequadamente.
                if (isNil(current.left)) current.numberLeft--;
                else if (isNil(current.right)) current.numberRight--;
                

                /* Os casos em que current tem dois filhos e
                * precisamos determinar se track está a esquerda ou 
                * o filho da direita e atualizar adequadamente */
                else if (track == current.right) current.numberRight--;
                else if (track == current.left) current.numberLeft--;
            }
            // Atualizando track e current.
            track = current;
            current = current.parent;
        }
    }
    
    // ""x"" é o filho do nó excluído de 'remove(RedBlackNode v)'
    /* Restaura as propriedades que possam ter sido violadas durante
    * a remoção de um nó em 'remove(RedBlackNode v)' */
    private void deleteFix(RedBlackNode<ValueType> x) {
        
        RedBlackNode<ValueType> n;
        
        // Enquanto árvore não é "consertada" completamente.
        while (x != root && x.color == RedBlackNode.black) {
            // Se x é o filho da esquerda do pai
            if (x == x.parent.left) {
                // define o irmão de n = x
                n = x.parent.right;
                // Caso 1: a cor de n é vermelho.
                if (n.color == RedBlackNode.red) {
                    n.color = RedBlackNode.black;
                    x.parent.color = RedBlackNode.red;
                    leftTurn(x.parent);
                    n = x.parent.right;
                }
                // Caso 2: os dois filhos de n são pretos.
                if (n.left.color == RedBlackNode.black &&
                        n.right.color == RedBlackNode.black) {
                    n.color = RedBlackNode.red;
                    x = x.parent;
                }
                else{
                    // Caso 3: o filho da direita de n é preto.
                    if (n.right.color == RedBlackNode.black) {
                        n.left.color = RedBlackNode.black;
                        n.color = RedBlackNode.red;
                        rightTurn(n);
                        n = x.parent.right;
                    }
                    // caso 4: n é preto, n.right é vermelho.
                    n.color = x.parent.color;
                    x.parent.color = RedBlackNode.black;
                    n.right.color = RedBlackNode.black;
                    leftTurn(x.parent);
                    x = root;
                }
            }
            // Se x é o filho da direita do pai
            else{
                // Define n como irmão de x
                n = x.parent.left;
                // Caso 1: a cor de n é vermelho.
                if (n.color == RedBlackNode.red) {
                    n.color = RedBlackNode.black;
                    x.parent.color = RedBlackNode.red;
                    rightTurn(x.parent);
                    n = x.parent.left;
                }
                //Caso 2: os dois filhos de n são pretos.
                if (n.right.color == RedBlackNode.black &&
                        n.left.color == RedBlackNode.black) {
                    n.color = RedBlackNode.red;
                    x = x.parent;
                }
                else{
                    // Caso 3: o filho esquerdo de n é preto.
                    if (n.left.color == RedBlackNode.black) {
                        n.right.color = RedBlackNode.black;
                        n.color = RedBlackNode.red;
                        leftTurn(n);
                        n = x.parent.left;
                    }
                    // Caso 4: n é preto e n.left é vermelho.
                    n.color = x.parent.color;
                    x.parent.color = RedBlackNode.black;
                    n.left.color = RedBlackNode.black;
                    rightTurn(x.parent);
                    x = root;
                }
            }
        }

        // define x como preto para garantir que não haja violação das propriedades da árvore rubro negra.
        x.color = RedBlackNode.black;
    }
    
    // ""key"" é a chave cujo nó é o que queremos procurar
    // "return" retorna um nó com a chave key se não for encontrada retorna nulo.
    // Procura um nó com a chave k e retorna o primeiro nó, se não encontrar esse nó, retorna nulo.
    public RedBlackNode<ValueType> search(ValueType key) {
        
        RedBlackNode<ValueType> current = root;
        
        // Enquanto não tenha chegado no fim da árvore, faça:
        while (!isNil(current)) {
            // Se encontrar um nó com uma chave igual a key
            if (current.key.equals(key)) 
                // Retorna esse nó e sai da 'search(int)'
                return current;
            // Vai para a esquerda ou direita com base no valor atual e da chave.
            else if (current.key.compareTo(key) < 0) current = current.right;
            // Vai para a esquerda ou direita com base no valor atual e da chave.
            else current = current.left;
        }
        // Retorna null caso não encontre um nó cuja chave seja "key"
        return null;
    }
    
    // ""key"" é qualquer objeto comparável
    // ""return"" é o número de elementos maior que 'key'
    public int biggerNumber(ValueType key) {
        /* Chama findBiggerNumber(root, key) que retornará 
        * o número de nós cuja chave é maior que key */
        return findBiggerNumber(root,key);
    }
    
    // ""key"" é qualquer objeto comparável
    // ""return"" retorna o número de elementos menor que a chave.
    public int SmallerNumber(ValueType key) {]
        /* Chama findSmallerNumber(root, key) que retornará 
        * o número de nós cuja chave é maior que key */
        return findSmallerNumber(root,key);
    }
    
    // ""node"" é a raiz da árvore, a chave que compara outras chaves do nó.
    // "return" retorna o número de nós maior que a chave.
    public int findBiggerNumber(RedBlackNode<ValueType> node, ValueType key) {
        // Caso base: se o nó for nulo, retorna 0.
        if (isNil(node)) return 0;
        
        /* Se a chave for menor que o node.key, todos os elementos à direita do nó serão
        * maior que a chave. Adiciona isso ao total e olha para a esquerda */
        else if (key.compareTo(node.key) < 0)
            return 1 + node.numberRight + findBiggerNumber(node.left,key);
        /* Se a chave for maior que o node.key, olha para a direita, 
        * pois todos os elementos à esquerda do nó são menores que a chave */
        else
            return findBiggerNumber(node.right,key);
    }
    
    // Retorna a lista classificada de chaves maior que a chave. O tamanho da lista não excederá 'maxReturned'.
    public List<ValueType> getBiggerThan(ValueType key, Integer maxReturned) {
        
        List<ValueType> list = new ArrayList<ValueType>();
        
        getBiggerThan(root, key, list);
        
        return list.subList(0, Math.min(maxReturned, list.size()));
    }
    
    private void getBiggerThan(RedBlackNode<ValueType> node, ValueType key, List<ValueType> list) {
        if (isNil(node)){
            return;
        } 
        else if (node.key.compareTo(key) > 0) {
            getBiggerThan(node.left, key, list);
            list.add(node.key);
            getBiggerThan(node.right, key, list);
        } 
        else {
            getBiggerThan(node.right, key, list);
        }
    }
    
    // "node" é a raiz da árvore, a chave com a qual deve-se comparar as outras chaves do nó.
    // "return" retorna o número de nós menor que a chave.
    public int findSmallerNumber(RedBlackNode<ValueType> node, ValueType key) {
        
        // Caso base: se o nó for nulo, retorna 0.
        if (isNil(node)) return 0;
        
        /* Se key for menor que node.key, olha para a esquerda, 
        * pois todos os elementos à direita do nó são maiores que key */
        else if (key.compareTo(node.key) <= 0) return findSmallerNumber(node.left,key);
        
        /* Se a chave for maior que o node.key, todos os elementos à esquerda do nó 
        * são menores que a chave. Assim, adiciona-o  ao total e olha para a direita. */
        else
            return 1+ node.numberLeft + findSmallerNumber(node.right,key);
    }
    
    // "node" é um RedBlackNode. Aqui é uma espécie de @Override que verifica se é nulo/nada.
    // "return" retorna verdadeiro se o nó é nulo/nada/não existe e falso caso contrário.
    private boolean isNil(RedBlackNode node) {
        return node == nada;
    }
    
    // "return" retorna o tamanho da árvore
    // "return" é o número de nós, incluindo a raiz que a Árvore R.N enraizou na raiz.
    public int size() {
        // Retorna o número de nós à esquerda da raiz + o número de nós à direita da raiz + a própria raiz.
        return root.numberLeft + root.numberRight + 1;
    }
}
