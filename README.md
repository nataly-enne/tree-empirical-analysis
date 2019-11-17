#  Análise Empírica do Tempo de Execução de Árvores
> Este projeto é a implementação da Árvore Rubro Negra e criação dos testes das Árvores Binária de Busca e AVL (e também da Rubro Negra). Fazendo a análise do tempo de execução de cada uma para a disciplina de Estrutura de Dados Básicas II, ministrada pelo professor Waldson Patricio no Instituto Metrópole Digital, na Universidade Federal do Rio Grande do Norte.

# What is it?
Consiste em comparar o desempenho das operações de inserção, remoção e busca das árvores rubro negra, binária de busca e AVL. Na prática, basicamente, é rodar os casos de testes, armazenar o tempo de execução das operações e gerar um gráfico comparativo com os resultados.

### Casos de Teste
- Dois conjuntos de parâmetros de entrada:
  1. Inserir 500.000 elementos em ordem crescente, buscar em ordem decrescente e remover em ordem decrescente.
  2. Tentar inserir 500.000 elementos 1 entre 1 e 1.000.000 em ordem aleatória. Buscar 1.000.000 de elementos em ordem decrescente (de 1.000.000 até 1) e tentar remover 1.000.000 de elementos em ordem decrescente.

## Compilação

### Se você for executar no terminal, execute as seguintes linhas de código:

```bash
javac *.java
java Main
```

### Caso você não possua jdk (java) instalado, execute no terminal:

```bash
sudo apt install default-jdk
```

### Também é possível executar o código em qualquer IDE.
