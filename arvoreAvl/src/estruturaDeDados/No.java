package estruturaDeDados;

public class No {

    int elemento;
    No pai;
    No esquerda;
    No direita;
    int fatorB; // Fator de balanceamento

    public No(int elemento) {
        this.elemento = elemento;
        this.pai = null;
        this.esquerda = null;
        this.direita = null;
        this.fatorB = 0;
    }

    public int getElemento() {
        return elemento;
    }

    public void setElemento(int elemento) {
        this.elemento = elemento;
    }

    public No getPai() {
        return pai;
    }

    public void setPai(No pai) {
        this.pai = pai;
    }

    public No getEsquerda() {
        return esquerda;
    }

    public void setEsquerda(No esquerda) {
        this.esquerda = esquerda;
    }

    public No getDireita() {
        return direita;
    }

    public void setDireita(No direita) {
        this.direita = direita;
    }

    public int getFatorB() {
        return fatorB;
    }

    public void setFatorB(int fatorB) {
        this.fatorB = fatorB;
    }

}
