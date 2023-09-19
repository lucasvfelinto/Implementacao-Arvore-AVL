package com.atividade;

/**
 * A classe `No` representa um nó em uma árvore AVL.
 */
public class No {
    public final Integer chave;  // A chave associada ao nó
    public int altura;          // A altura do nó na árvore
    public No direita;          // Referência para o filho direito do nó
    public No esquerda;         // Referência para o filho esquerdo do nó

    /**
     * Cria um novo nó com a chave especificada.
     *
     * @param chave A chave a ser associada ao nó.
     */
    public No(Integer chave) {
        this.chave = chave;
        this.altura = 0;         // Inicialmente, a altura do nó é definida como 0
        this.direita = null;     // Inicialmente, o nó não tem filho direito
        this.esquerda = null;    // Inicialmente, o nó não tem filho esquerdo
    }

    public Integer getChave() {
        return chave;
    }

    public int getAltura() {
        return altura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

    public No getDireita() {
        return direita;
    }

    public void setDireita(No direita) {
        this.direita = direita;
    }

    public No getEsquerda() {
        return esquerda;
    }

    public void setEsquerda(No esquerda) {
        this.esquerda = esquerda;
    }
    
    
}

