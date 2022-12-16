package estruturaDeDados;

import java.io.IOException;
import java.util.List;
import util.ManipuladorArquivos;

public class ArvoreAvl {

    private No raiz;

    public ArvoreAvl() {
        this.raiz = null;
    }

    public No getRaiz() {
        return raiz;
    }

    public boolean arvoreVazia() {
        return this.raiz == null;
    }

    public void imprimirArvoreOrdem(No raiz) {
        if (raiz != null) {
            imprimirArvoreOrdem(raiz.getEsquerda());
            System.out.print(" " + raiz.getElemento());
            imprimirArvoreOrdem(raiz.getDireita());
        }
    }

    public int calcularAltura(No noAtual) {
        if (noAtual == null) {
            return -1;
        }
        if (noAtual.getEsquerda() == null && noAtual.getDireita() == null) {
            return 0;
        } else if (noAtual.getEsquerda() != null && noAtual.getDireita() == null) {
            return 1 + calcularAltura(noAtual.getEsquerda());
        } else if (noAtual.getEsquerda() == null && noAtual.getDireita() != null) {
            return 1 + calcularAltura(noAtual.getDireita());
        } else {
            return 1 + Math.max(calcularAltura(noAtual.getEsquerda()), calcularAltura(noAtual.getDireita()));
        }
    }

    public void calcularFatorBalanceamento(No noAtual) {
        noAtual.setFatorB(calcularAltura(noAtual.getDireita()) - calcularAltura(noAtual.getEsquerda()));
    }

    public void inserirAvl(int elemento, No noAtual) {

        if (arvoreVazia()) {
            No novoNo = new No(elemento);
            this.raiz = novoNo;
            calcularFatorBalanceamento(this.raiz);
        } else {
            if (elemento >= noAtual.getElemento()) { // Inserir elementos maiores que a raiz

                if (noAtual.getDireita() == null) {
                    No novoNo = new No(elemento);
                    noAtual.setDireita(novoNo);
                    novoNo.setPai(noAtual);
                    balancearArvore(noAtual);

                } else {

                    inserirAvl(elemento, noAtual.getDireita());
                }
            } else if (elemento <= noAtual.getElemento()) { // Inserir elementos menores que a raiz
                if (noAtual.getEsquerda() == null) {
                    No novoNo = new No(elemento);
                    noAtual.setEsquerda(novoNo);
                    novoNo.setPai(noAtual);
                    balancearArvore(noAtual);
                } else {
                    inserirAvl(elemento, noAtual.getEsquerda());
                }
            }
        }
    }

    public void removerNoAvl(int elemento, No noAtual) {
        if (arvoreVazia()) {
            System.out.println("Arvore Vazia!");
        } else if (noAtual == null) {
            return;
        } else {
            if (elemento > noAtual.getElemento()) {
                removerNoAvl(elemento, noAtual.getDireita());
            } else if (elemento < noAtual.getElemento()) {
                removerNoAvl(elemento, noAtual.getEsquerda());
            } else if (elemento == noAtual.getElemento()) {
                removerNo(noAtual);
            }
        }
    }

    public No procura_sucessor(No noAtual) {

        if (noAtual.getEsquerda() != null) {
            No auxiliar = noAtual.getEsquerda();
            while (auxiliar.getDireita() != null) {
                auxiliar = auxiliar.getDireita();
            }
            return auxiliar;
        }
        return noAtual;
    }

    public void removerNo(No noAtual) {
        No noAuxiliar;

        if (noAtual.getEsquerda() == null && noAtual.getDireita() == null) {

            if (noAtual.getPai() == null) {
                this.raiz = null;
                noAtual = null;
                return;
            }
            noAuxiliar = noAtual;

        } else {
            noAuxiliar = procura_sucessor(noAtual);
            noAtual.setElemento(noAuxiliar.getElemento());
        }

        No controle;
        if (noAuxiliar.getEsquerda() != null) {
            controle = noAuxiliar.getEsquerda();
        } else {
            controle = noAuxiliar.getDireita();
        }

        if (controle != null) {
            controle.setPai(noAuxiliar.getPai());
        }

        if (noAuxiliar.getPai() == null) {
            this.raiz = controle;
        } else {
            if (noAuxiliar == noAuxiliar.getPai().getEsquerda()) {
                noAuxiliar.getPai().setEsquerda(controle);
            } else {
                noAuxiliar.getPai().setDireita(controle);
            }
            balancearArvore(noAuxiliar.getPai());
        }
        noAuxiliar = null;
    }

    public No rotacaoSimplesEsquerda(No noAtual) {

        No novoPai = noAtual.getDireita(); // O novo pai é o no intermediario

        noAtual.setDireita(novoPai.getEsquerda()); // A direita do noAtual recebe os valores da esquerda do novoPai
        novoPai.setPai(noAtual.getPai());

        novoPai.setEsquerda(noAtual); // O novoPai agora é a raiz da subArvore

        if (noAtual.getPai() != null) {
            if (novoPai.getPai().getDireita() == noAtual) {
                novoPai.getPai().setDireita(novoPai);
            } else if (novoPai.getPai().getEsquerda() == noAtual) {
                novoPai.getPai().setEsquerda(novoPai);
            }
        }

        if (noAtual.getPai() == null) { // Se o no atual foi a raiz pricipal, devemos informar que o novoPai é a nova
            // raiz, caso não seja o novo pai é filho do pai anteirior ao no atual
            this.raiz = novoPai;
            novoPai.setPai(null);
        }

        noAtual.setPai(novoPai);
        calcularFatorBalanceamento(noAtual);
        calcularFatorBalanceamento(novoPai);

        return novoPai;
    }

    public No rotacaoSimplesDireita(No noAtual) {

        No novoPai = noAtual.getEsquerda(); // O novo pai é o no intermediario

        noAtual.setEsquerda(novoPai.getDireita()); // A direita do noAtual recebe os valores da esquerda do novoPai
        novoPai.setPai(noAtual.getPai());

        novoPai.setDireita(noAtual); // O novoPai agora é a raiz da subArvore

        if (noAtual.getPai() != null) {
            if (novoPai.getPai().getDireita() == noAtual) {
                novoPai.getPai().setDireita(novoPai);
            } else if (novoPai.getPai().getEsquerda() == noAtual) {
                novoPai.getPai().setEsquerda(novoPai);
            }
        }

        if (noAtual.getPai() == null) { // Se o no atual foi a raiz pricipal, devemos informar que o novoPai é a nova
            this.raiz = novoPai;       // raiz, caso não seja o novo pai é filho do pai anteirior ao no atual
            novoPai.setPai(null);
        }

        noAtual.setPai(novoPai);
        calcularFatorBalanceamento(noAtual);
        calcularFatorBalanceamento(novoPai);

        return novoPai;
    }

    public void rotacaoDireitaEsquerda(No noAtual) {
        No noFilho = noAtual.getDireita();
        rotacaoSimplesDireita(noFilho);
        rotacaoSimplesEsquerda(noAtual);
    }

    public void rotacaoEsquerdaDireita(No noAtual) {
        No noFilho = noAtual.getEsquerda();
        rotacaoSimplesEsquerda(noFilho);
        rotacaoSimplesDireita(noAtual);
    }

    public void balancearArvore(No noAtual) {

        calcularFatorBalanceamento(noAtual);

        if (noAtual.getFatorB() == 2 && noAtual.getDireita().getFatorB() >= 0) {
            rotacaoSimplesEsquerda(noAtual);
        } else if (noAtual.getFatorB() == -2 && noAtual.getEsquerda().getFatorB() <= 0) {
            rotacaoSimplesDireita(noAtual);
        } else if (noAtual.getFatorB() == 2 && noAtual.getDireita().getFatorB() < 0) {
            rotacaoDireitaEsquerda(noAtual);
        } else if (noAtual.getFatorB() == -2 && noAtual.getEsquerda().getFatorB() > 0) {
            rotacaoEsquerdaDireita(noAtual);
        }

        if (noAtual.getPai() != null) {
            balancearArvore(noAtual.getPai());
        }

    }

    public void imprimir(No noAtual, int nivel) {

        if (noAtual != null) {
            imprimir(noAtual.getDireita(), nivel + 1);
            System.out.println();
            System.out.println();

            for (int i = 0; i <= nivel; i++) {
                System.out.print("      ");
            }

            System.out.print(noAtual.getElemento() + "(" + noAtual.getFatorB() + ")");
            imprimir(noAtual.getEsquerda(), nivel + 1);
        }
    }

    public void buscarAvl(int elemento, No noAtual) {
        if (arvoreVazia()) {
            System.out.println("Arvore Vazia!");
            return;
        } else if (noAtual == null) {
            System.out.println("Elemento não encontrado!");
            return;
        } else {
            if (elemento > noAtual.getElemento()) {
                buscarAvl(elemento, noAtual.getDireita());
            } else if (elemento < noAtual.getElemento()) {
                buscarAvl(elemento, noAtual.getEsquerda());
            } else if (elemento == noAtual.getElemento()) {
                System.out.println("Elemento: " + noAtual.getElemento() + " Encontrado!");
                return;
            }
        }
    }

    public ArvoreAvl carregarAvl(String caminhoDados) throws IOException {

        ManipuladorArquivos manipulaArquivos = new ManipuladorArquivos();

        List<String> dadosArquivo = ManipuladorArquivos.lerArquivo(caminhoDados);

        ArvoreAvl avl = new ArvoreAvl();

        for (int i = 0; i < dadosArquivo.size(); i++) {

            String elementoInteiro = dadosArquivo.get(i);
            String elementoSeparado[] = elementoInteiro.split(";");

            avl.inserirAvl(Integer.parseInt(elementoSeparado[0]), avl.getRaiz());
        }

        return avl;
    }

}
