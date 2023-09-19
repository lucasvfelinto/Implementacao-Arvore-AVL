package com.atividade;

/**
 * Uma classe que implementa uma Árvore AVL (Árvore Binária de Busca
 * Balanceada).
 * Cada nó da árvore contém uma chave inteira.
 */

public class AVL {
    private No raiz; // Nó raiz da árvore AVL

    /**
     * Obtém a raiz da árvore AVL.
     *
     * @return O nó raiz da árvore.
     */
    public No getRaiz() {
        return raiz;
    }

    /**
     * Insere uma nova chave na árvore AVL.
     *
     * @param chave A chave a ser inserida.
     */
    public void put(Integer chave) {
        // Chama o método inserir para adicionar a chave à árvore, atualizando a raiz se necessário.
        raiz = inserir(raiz, chave);
    }

    /**
     * Obtém o valor associado a uma chave na árvore AVL.
     *
     * @param chave A chave a ser buscada.
     * @return O valor associado à chave, ou null se a chave não existe na árvore.
     */
    public Integer get(Integer chave) {
        // Chama o método buscar para encontrar o nó com a chave na árvore.
        No no = buscar(raiz, chave);

        // Verifica se o nó foi encontrado e, se sim, retorna o valor da chave, caso contrário, retorna null.
        return (no != null) ? no.chave : null;
    }

    /**
     * Deleta uma chave da árvore AVL.
     *
     * @param chave A chave a ser deletada.
     */
    public void deletar(Integer chave) {
        raiz = deletar(raiz, chave);
    }

    // Métodos privados para as operações da árvore AVL

    /**
     * Executa uma rotação para a esquerda em um nó desbalanceado.
     *
     * @param y O nó desbalanceado.
     * @return O novo nó após a rotação.
     */
    private No rotacaoEsquerda(No y) {
        // 1. Armazena a referência para o nó à direita de 'y' em 'x'.
        No x = y.direita;

        // 2. Armazena a subárvore à esquerda de 'x' (T2).
        No T2 = x.esquerda;

        // 3. Realiza a rotação para a esquerda:
        // - 'x' se torna o novo nó raiz da subárvore.
        // - 'y' se torna o filho à esquerda de 'x'.
        // - 'T2' se torna o filho à direita de 'y'.
        x.esquerda = y;
        y.direita = T2;

        // 4. Atualiza as alturas de 'y' e 'x'.
        y.altura = Math.max(altura(y.esquerda), altura(y.direita)) + 1;
        x.altura = Math.max(altura(x.esquerda), altura(x.direita)) + 1;

        // 5. Retorna o novo nó (anteriormente 'x') como resultado da rotação
        return x;
    }

    /**
     * Executa uma rotação para a direita em um nó desbalanceado.
     *
     * @param x O nó desbalanceado.
     * @return O novo nó após a rotação.
     */
    private No rotacaoDireita(No x) {
        // 1. Armazena a referência para o nó à esquerda de 'x' em 'y'.
        No y = x.esquerda;

        // 2. Armazena a subárvore à direita de 'y' (T2).
        No T2 = y.direita;

        // 3. Realiza a rotação para a direita:
        // - 'y' se torna o novo nó raiz da subárvore.
        // - 'x' se torna o filho à direita de 'y'.
        // - 'T2' se torna o filho à esquerda de 'x'.
        y.direita = x;
        x.esquerda = T2;

        // 4. Atualiza as alturas de 'x' e 'y'.
        x.altura = Math.max(altura(x.esquerda), altura(x.direita)) + 1;
        y.altura = Math.max(altura(y.esquerda), altura(y.direita)) + 1;

        // 5. Retorna o novo nó (anteriormente 'y') como resultado da rotação.
        return y;
    }

    /**
     * Calcula a altura de um nó na árvore AVL.
     *
     * @param no O nó cuja altura deve ser calculada.
     * @return A altura do nó.
     */
    public int altura(No no) {
        if (no == null) {
            // Altura de um nó nulo
            return -1; 
            // Retornava 0, mas estavamos acrescentando 1 e não retirando a altura estava
            // com +1 do que era esperado
        }
        // Retorna a altura do nó
        return no.altura;
    }

    /**
     * Calcula o fator de balanceamento de um nó na árvore AVL.
     *
     * @param no O nó cujo fator de balanceamento deve ser calculado.
     * @return O fator de balanceamento do nó. Se o nó for nulo, retorna 0.
     */
    private int balancear(No no) {
        if (no == null) {
            return 0; // Fator de balanceamento de um nó nulo
        }

        // Calcula o fator de balanceamento subtraindo a altura da subárvore esquerda da altura da subárvore direita. Isso indica o desequilíbrio da árvore.
        return altura(no.direita) - altura(no.esquerda);
    }

    /**
     * Insere uma nova chave na árvore AVL de acordo com as propriedades da árvore.
     *
     * @param no    O nó atual onde a inserção deve ser realizada.
     * @param chave A chave do novo nó a ser inserido.
     * @return O nó atual após a inserção e possíveis rotações.
     */
    private No inserir(No no, Integer chave) {
        if (no == null) { 
            // Se o nó for nulo, significa que você alcançou uma folha da árvore onde pode inserir o novo valor chave
            // Cria um novo nó com a chave especificada.
            return new No(chave); 
        }

        if (chave < no.chave) { 
            // Se a chave for menor que a chave do nó atual (no.chave), insira na subárvore esquerda do nó no.
            no.esquerda = inserir(no.esquerda, chave); 
        } else if (chave > no.chave) {
            // Se a chave for maior que a chave do nó atual (no.chave), insira na subárvore direita.
            no.direita = inserir(no.direita, chave); 
        } else {
            // Chave já existe, não faz nada
            return no; 
        }
        // Atualiza a altura do nó atual com base nas alturas das subárvores esquerda e direita.
        no.altura = 1 + Math.max(altura(no.esquerda), altura(no.direita));

        // Calcula o fator de balanceamento do nó.
        int balance = balancear(no);

        // Verifica os casos de desbalanceamento e realiza rotações, se necessário.
        if (balance > 1) {
            if (chave > no.direita.chave) {
                // Caso de desbalanceamento RR:
                // Rotação à esquerda é realizada no nó atual (no).
                return rotacaoEsquerda(no);
            } else {
                // Caso de desbalanceamento RL:
                // Primeiro, uma rotação à direita é realizada na subárvore direita (no.direita).
                no.direita = rotacaoDireita(no.direita);
                // Em seguida, uma rotação à esquerda é realizada no nó atual (no).
                return rotacaoEsquerda(no);
            }
        }
        if (balance < -1) {
            if (chave < no.esquerda.chave) {
                // Caso de desbalanceamento LL:
                // Rotação à direita é realizada no nó atual (no).
                return rotacaoDireita(no);
            } else {
                // Caso de desbalanceamento LR:
                // Primeiro, uma rotação à esquerda é realizada na subárvore esquerda (no.esquerda).
                no.esquerda = rotacaoEsquerda(no.esquerda);
                // Em seguida, uma rotação à direita é realizada no nó atual (no).
                return rotacaoDireita(no);
            }
        }

        // Retorna o nó atual após a inserção e possíveis rotações.
        return no;
    }

    /**
     * Busca por um nó com a chave especificada na árvore AVL.
     *
     * @param no    O nó atual onde a busca será realizada.
     * @param chave A chave a ser buscada na árvore.
     * @return O nó com a chave se encontrado, ou null se a chave não existir na árvore.
     */
    private No buscar(No no, Integer chave) {
         // Verifica se o nó atual é nulo (chegou a uma folha da árvore) ou se a chave foi encontrada.
        if (no == null || chave.equals(no.chave)) {
            return no;
        }
        // Se a chave for menor que a chave do nó atual, continue a busca na subárvore esquerda.
        if (chave < no.chave) {
            return buscar(no.esquerda, chave);
        }
        // Caso contrário, continue a busca na subárvore direita.
        return buscar(no.direita, chave);
    }

    /**
     * Encontra e retorna o nó com o valor mínimo na subárvore.
     *
     * @param no O nó raiz da subárvore onde a busca será realizada.
     * @return O nó com o valor mínimo na subárvore, ou null se a subárvore estiver vazia.
     */
    private No getMinimoValorNo(No no) {
        // Inicia a busca a partir do nó atual, descendo pela subárvore esquerda até encontrar o nó mais à esquerda.
        No atual = no;
        while (atual.esquerda != null) {
            atual = atual.esquerda;
        }
        // O nó mais à esquerda é o nó com o valor mínimo na subárvore.
        return atual;
    }

    /**
     * Remove o nó com a chave especificada da árvore AVL, mantendo o balanceamento.
     *
     * @param no    O nó atual onde a exclusão será realizada.
     * @param chave A chave do nó a ser removido.
     * @return O nó resultante após a exclusão e possíveis rotações, ou null se a chave não for encontrada.
     */
    private No deletar(No no, Integer chave) {
        // Verifica se o nó atual é nulo, indicando que a chave não foi encontrada na árvore.
        if (no == null) {
            return no;
        }

        // Caso a chave seja menor que a chave do nó atual, continue a busca na subárvore esquerda.
        if (chave < no.chave) {
            no.esquerda = deletar(no.esquerda, chave);
        } 
        // Caso a chave seja maior que a chave do nó atual, continue a busca na subárvore direita.
        else if (chave > no.chave) {
            no.direita = deletar(no.direita, chave);
        } 
        // Caso a chave seja igual à chave do nó atual, encontramos o nó a ser excluído.
        else {
            // Se o nó atual tem zero ou um filho, substituímos o nó atual pelo seu filho.
            if ((no.esquerda == null) || (no.direita == null)) {
            No temp = (no.esquerda != null) ? no.esquerda : no.direita;
            // Se não há filho, definimos o nó atual como null, indicando sua remoção.
            if (temp == null) {
                no = null;
            } else {
                // Caso contrário, substituímos o nó atual pelo seu filho.
                no = temp;
            }

            }
            // Se o nó atual tem dois filhos, encontramos o sucessor in-order.
            else {
                No temp = getMinimoValorNo(no.direita);

                // Criamos um novo nó com a chave do sucessor in-order.
                No novoNo = new No(temp.chave);

                // Copiamos as referências dos filhos do nó atual para o novo nó.
                novoNo.esquerda = no.esquerda;
                novoNo.direita = deletar(no.direita, temp.chave);

                // Atualizamos o nó atual para o novo nó
                no = novoNo;
                no.direita = deletar(novoNo.direita, temp.chave);
            }
        }

        // Atualiza a altura do nó atual.
        if (no == null) {
            return no;
        }

        no.altura = Math.max(altura(no.esquerda), altura(no.direita)) + 1;
        // Verifica o balanceamento e aplica rotações, se necessário.
        int balance = balancear(no);

        // Verifica os casos de desbalanceamento e realiza rotações, se necessário.
        if (balance > 1) {
            if (chave > no.direita.chave) {
                // Caso de desbalanceamento RR:
                // Rotação à esquerda é realizada no nó atual (no).
                return rotacaoEsquerda(no);
            } else {
                // Caso de desbalanceamento RL:
                // Primeiro, uma rotação à direita é realizada na subárvore direita (no.direita).
                no.direita = rotacaoDireita(no.direita);
                // Em seguida, uma rotação à esquerda é realizada no nó atual (no).
                return rotacaoEsquerda(no);
            }
        }
        if (balance < -1) {
            if (chave < no.esquerda.chave) {
                // Caso de desbalanceamento LL:
                // Rotação à direita é realizada no nó atual (no).
                return rotacaoDireita(no);
            } else {
                // Caso de desbalanceamento LR:
                // Primeiro, uma rotação à esquerda é realizada na subárvore esquerda (no.esquerda).
                no.esquerda = rotacaoEsquerda(no.esquerda);
                // Em seguida, uma rotação à direita é realizada no nó atual (no).
                return rotacaoDireita(no);
            }
        }

        // Retorna o nó atual após a inserção e possíveis rotações.
        return no;
    }
}