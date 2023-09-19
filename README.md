[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-24ddc0f5d75046c5622901739e7c5dd533143b0c8e959d652212380cedb1ea36.svg)](https://classroom.github.com/a/FKa09AbV)
# Apresentação da Atividade
<img src="assets/images/Unicap_Icam_Tech-01.png" alt="drawing" width="250"/>

## Identificação
**Disciplina**: Estrutura de Dados II
\
**Atividade**: Implementação Árvore AVL
- [@mjcea - Prof. Marcos Canêjo](marcos.azevedo@unicap.br)

## Instruções
- Sua implementação deve estar dentro da pasta src/**main**/java 
- Não modifique nenhum código dentro da pasta src/**test**/java
- *Commits* fora do prazo **serão completamente desconsiderados**

##  Descrição
Implemente a ``Árvore AVL`` com o fator de balanceamento ``alturaDireita - alturaEsquerda`` e na remoção ``substituir o vertice pelo maior da esquerda`` de acordo com a seguinte assinatura:
```java
public class FilaEstatica {
  private No raiz;

  public void put(Integer chave){}
  public Integer get(Integer chave){}
  private No rotacaoEsquerda(No y){}
  private No rotacaoDireita(No y){}
  public int altura(No no){}
  private No balancear(No no){}
}

public class No {
    public final Integer chave;
    public int altura;
    public No direita;
    public No esquerda;

    public No(Integer chave){}
}

```