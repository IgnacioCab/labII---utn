//División con Restas (Recursión e Iteración)

package RecursionLab2;

public class Programa1 {

    // Enfoque recursivo
    public static int divisionRecursiva(int a, int b) {

        if (b == 0) {

            System.out.println("Error: No es posible dividir por cero");
            return 0;  // O devuelve cualquier otro valor apropiado
        }
        if (a < b) {

            return 0;
        }

        return 1 + divisionRecursiva(a - b, b);
    }

    // Enfoque iterativ
    public static int divisionIterativa(int a, int b) {

        if (b == 0) {

            System.out.println("Error: No es posible dividir por cero");
            return 0;  // O devuelve cualquier otro valor apropiado
        }

        int cociente = 0;

        while (a >= b) {

            a -= b;
            cociente++;
        }

        return cociente;
    }

    public static void main(String[] args) {

        int num1 = 50;
        int num2 = 5;

        //Imprime resultados
        System.out.println("Enfoque recursivo:");
        System.out.println(num1 + " / " + num2 + " = " + divisionRecursiva(num1, num2));

        System.out.println("Enfoque iterativo:");
        System.out.println(num1 + " / " + num2 + " = " + divisionIterativa(num1, num2));
    }


    }

