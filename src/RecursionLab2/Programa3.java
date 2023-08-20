//Factorial con Sobrecarga (Recursión e Iteración)

package RecursionLab2;

public class Programa3 {

    // Método recursivo
    public static int factorial(int n) {

        if (n == 0 || n == 1) {

            return 1;

        } else {

            return n * factorial(n - 1);
        }
    }

    // Método iterativo
    public static int factorialIterativo(int n) {

        int result = 1;

        for (int i = 2; i <= n; i++) {

            result *= i;
        }

        return result;
    }

    public static void main(String[] args) {
        int num1 = 5;

        int resultadoRecursivo = factorial(num1);

        int resultadoIterativo = factorialIterativo(num1);

        System.out.println("Factorial de " + num1 + " utilizando recursión: " + resultadoRecursivo);
        System.out.println("Factorial de " + num1 + " utilizando iteración: " + resultadoIterativo);
    }



}
