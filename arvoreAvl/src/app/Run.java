package app;

import estruturaDeDados.ArvoreAvl;
import java.io.IOException;
import java.util.Scanner;
import util.Comandos;

import util.MenuAvlRun;

public class Run {

    public static void main(String[] args) throws IOException, InterruptedException {

        MenuAvlRun menuAvl = new MenuAvlRun();
        ArvoreAvl avl = new ArvoreAvl();
        Scanner leitor = new Scanner(System.in);
        Comandos comando = new Comandos();
        
        String caminhoDados = "src/entrada/dados.txt"; // Para testes na IDE
        //String caminhoDados = ".\\dados.txt";

        avl = avl.carregarAvl(caminhoDados);
        
        int opcao;

        do {

            opcao = menuAvl.menu();

            switch (opcao) {
                case 1:
                    comando.limparTerminal();
                    System.out.println("\n| Inserção de novo Elemento ");
                    System.out.print("\nDigite o valor do elemento que deseja inserir: ");
                    int novoelemento = leitor.nextInt();

                    avl.inserirAvl(novoelemento, avl.getRaiz());
                    break;

                case 2:
                    comando.limparTerminal();
                    if (avl.arvoreVazia()) {
                        System.out.println("\n| Árvore está vazia [!]");
                    } else {
                        System.out.println("\n| Remoção de Elemento ");
                        System.out.print("\nDigite o valor do elemento que deseja remover: ");
                        int removerElemento = leitor.nextInt();

                        avl.removerNoAvl(removerElemento, avl.getRaiz());
                    }
                    break;

                case 3:
                    comando.limparTerminal();
                    
                    System.out.print("\nDigite o valor que deseja procurar na árvore: ");
                    int elementoProcurado = leitor.nextInt();
                    
                    avl.buscarAvl(elementoProcurado, avl.getRaiz());
                    
                    break;

                case 4:
                    comando.limparTerminal();

                    if (avl.arvoreVazia()) {
                        System.out.println("\n| Árvore está vazia [!]");
                    } else {
                        System.out.println("\n| Impressão da AVL ");
                        System.out.println("Obs 1: A árvore imprime valores inteiros, o valor que estiver dentro do parêntese () é o fator de balanceamento do nó");
                        System.out.println("\nObs 2: A árvore está sendo impressa na horizontal");

                        avl.imprimir(avl.getRaiz(), 1);
                    }

                    break;

                case 5:
                    comando.limparTerminal();

                    if (avl.arvoreVazia()) {
                        System.out.println("\n| Árvore está vazia [!]");
                    } else {
                        System.out.println("| Impressão de Elementos em Ordem\n\n");

                        avl.imprimirArvoreOrdem(avl.getRaiz());
                    }

                    break;

                case 6:
                    comando.limparTerminal();
                    System.out.println("Saindo...");
                    break;

                default:
                    comando.limparTerminal();
                    System.out.println("A opção informada é invalida [!]");
            }

            comando.pausar();
            comando.limparTerminal();

        } while (opcao != 6);

    }
}
