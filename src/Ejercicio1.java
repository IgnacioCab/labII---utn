import java.util.Scanner;

public class Ejercicio1 {

    public static void main (String args[]){

        Scanner scanner = new Scanner(System.in);

        System.out.println("Ingresar tres numeros enteros: ");

        System.out.println("Numero 1: ");
        int num1 = scanner.nextInt();

        System.out.println("Numero 2: ");
        int num2 = scanner.nextInt();

        System.out.println("Numero 3: ");
        int num3 = scanner.nextInt();



        int aux;

        if (num1 < num2) {

            aux = num1;
            num1 = num2;
            num2 = aux;
        }

        if (num2 < num3) {

            aux = num2;
            num2 = num3;
            num3 = aux;
        }

        if (num1 < num2) {

            aux = num1;
            num1 = num2;
            num2 = aux;
        }

        System.out.println("Numeros ordenados de mayor a menor: " + num1 + "," + num2 + "," + num3);

    }
}
