package util;

import java.io.IOException;

public class Comandos {

    public void pausar() throws IOException {
        System.out.println("\n\n\nPressione Enter para continuar");
        System.in.read();
    }

    public void limparTerminal() throws IOException, InterruptedException {
        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
    }

}
