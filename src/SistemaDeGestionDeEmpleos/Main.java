package SistemaDeGestionDeEmpleos;

import java.util.Scanner;

// Programa principal
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        GestorEmpleados gestorEmpleados = new GestorEmpleados();

        while (true) {
            System.out.println("1. Agregar Empleado Por Horas");
            System.out.println("2. Agregar Empleado Asalariado");
            System.out.println("3. Agregar Empleado Comision");
            System.out.println("4. Mostrar Empleados");
            System.out.println("5. Salir");
            System.out.print("Selecciona una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea

            if (opcion == 5) {
                break;
            }

            System.out.print("Nombre: ");
            String nombre = scanner.nextLine();
            System.out.print("ID: ");
            int id = scanner.nextInt();
            System.out.print("Sueldo Base: ");
            double sueldoBase = scanner.nextDouble();

            if (opcion == 1) {
                System.out.print("Horas Trabajadas: ");
                int horasTrabajadas = scanner.nextInt();
                EmpleadoPorHorasImpuesto empleado = new EmpleadoPorHorasImpuesto(nombre, id, sueldoBase, horasTrabajadas);
                gestorEmpleados.agregarEmpleado(empleado);
            } else if (opcion == 2) {
                EmpleadoAsalariadoImpuesto empleado = new EmpleadoAsalariadoImpuesto(nombre, id, sueldoBase);
                gestorEmpleados.agregarEmpleado(empleado);
            } else if (opcion == 3) {
                System.out.print("Ventas Realizadas: ");
                double ventasRealizadas = scanner.nextDouble();
                EmpleadoComisionImpuesto empleado = new EmpleadoComisionImpuesto(nombre, id, sueldoBase, ventasRealizadas);
                gestorEmpleados.agregarEmpleado(empleado);
            }
        }

        gestorEmpleados.mostrarEmpleados();
    }
}
