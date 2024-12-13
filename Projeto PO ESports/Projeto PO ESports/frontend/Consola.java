package frontend;

import java.util.Scanner;

public class Consola {

    private Scanner scanner;

    
    public Consola() {
        this.scanner = new Scanner(System.in);
    }

   
    public int lerInteiro(String valorInput) {
        int numero = 0;
        boolean entradaValida = false;

        while (!entradaValida) {
            try {
                System.out.print(valorInput + ": ");
                numero = Integer.parseInt(scanner.nextLine());
                entradaValida = true; 
            } catch (NumberFormatException e) {
                System.out.println("Erro! O valor inserido não é um número inteiro válido. Tente novamente.");
            }
        }

        return numero; 
    }

    public float lerFloat(String valorInput) {
        float numero = 0;
        boolean entradaValida = false;

        while (!entradaValida) {
            try {
                System.out.print(valorInput + ": ");
                numero = Float.parseFloat(scanner.nextLine());
                entradaValida = true;
            } catch (NumberFormatException e) {
                System.out.println("Erro! O valor inserido não é um número inteiro válido. Tente novamente.");
            }
        }

        return numero;
    }

    
    public String lerString(String valorInput) {
        System.out.print(valorInput + ": ");
        String entrada = scanner.nextLine();
        if (entrada.trim().isEmpty()) {
            return "";
        } else {
            return entrada;
        }
    }

    public void pausar() {
        System.out.println("\nPressione Enter para voltar ao menu...");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
    }
}
