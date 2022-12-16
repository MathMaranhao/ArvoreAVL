package util;

import java.util.Scanner;

public class MenuAvlRun {

    public int menu() {

        int opcao;

        System.out.println("Algoritmo Árvore AVL \n");

        System.out.println("| 1 - Inserir novo elemento");
        System.out.println("| 2 - Remover elemento");
        System.out.println("| 3 - Buscar elemento");
        System.out.println("| 4 - Imprimir árvore AVL");
        System.out.println("| 5 - Imprimir elementos da árvore AVL em Ordem");
        System.out.println("| 6 - Sair");

        System.out.print("\n Selecione uma opção: ");

        Scanner leitor = new Scanner(System.in);

        opcao = leitor.nextInt();

        return opcao;
    }

}
