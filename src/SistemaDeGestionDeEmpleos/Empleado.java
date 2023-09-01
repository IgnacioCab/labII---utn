package SistemaDeGestionDeEmpleos;
import java.util.ArrayList;
import java.util.Scanner;

public  abstract class Empleado {
    //Atributos
    protected String nombre;
    protected int id;
    protected double sueldoBase;



    public Empleado(String nombre, int id, double sueldoBase) {
        this.nombre = nombre;
        this.id = id;
        this.sueldoBase = sueldoBase;


    }

    //metodos abstractos
    public abstract double calcularSueldo();

}
//Clase empleado por horas------------------------------------------------------
class EmpleadoPorHoras extends Empleado{

    private int horasTrabajadas;

    public EmpleadoPorHoras(String nombre, int id, double sueldoBase, int horasTrabajadas) {
        super(nombre, id, sueldoBase);
        this.horasTrabajadas = horasTrabajadas;

    }

    @Override
    public double calcularSueldo() {
        return sueldoBase * horasTrabajadas;

    }
}

//Clase Empleado Asalariado____________________________________________________
class EmpleadoAsalariado extends Empleado{


    public EmpleadoAsalariado(String nombre, int id, double sueldoBase) {
        super(nombre, id, sueldoBase);
    }

    @Override
    public double calcularSueldo() {
        return sueldoBase;
    }
}


//Clase Empleado Comision------------------------------------------------------
class EmpleadoComision extends Empleado {

    private double ventasRealizadas;

    public EmpleadoComision(String nombre, int id, double sueldoBase, double ventasRealizadas) {
        super(nombre, id, sueldoBase);
        this.ventasRealizadas = ventasRealizadas;
    }

    @Override
    public double calcularSueldo() {
        return (ventasRealizadas * 0.2) + sueldoBase;// Agrega 20% de comison por cada venta
    }
}

// Interfaz Impuesto
interface Impuesto {
    double calcularImpuesto();
}

// Implementación de las clases concretas de Empleado con la interfaz Impuesto

class EmpleadoPorHorasImpuesto extends EmpleadoPorHoras implements Impuesto {
    public EmpleadoPorHorasImpuesto(String nombre, int id, double sueldoBase, int horasTrabajadas) {
        super(nombre, id, sueldoBase, horasTrabajadas);
    }

    @Override
    public double calcularImpuesto() {
        return calcularSueldo() * 0.15; // 15% de impuesto
    }
}

class EmpleadoAsalariadoImpuesto extends EmpleadoAsalariado implements Impuesto {
    public EmpleadoAsalariadoImpuesto(String nombre, int id, double sueldoBase) {
        super(nombre, id, sueldoBase);
    }

    @Override
    public double calcularImpuesto() {
        return calcularSueldo() * 0.2; // 20% de impuesto
    }
}

class EmpleadoComisionImpuesto extends EmpleadoComision implements Impuesto {
    public EmpleadoComisionImpuesto(String nombre, int id, double sueldoBase, double ventasRealizadas) {
        super(nombre, id, sueldoBase, ventasRealizadas);
    }

    @Override
    public double calcularImpuesto() {
        return calcularSueldo() * 0.18; // 18% de impuesto
    }
}

// Clase GestorEmpleados
class GestorEmpleados {
    private ArrayList<Empleado> empleados;

    public GestorEmpleados() {
        empleados = new ArrayList<>();
    }

    public void agregarEmpleado(Empleado empleado) {
        empleados.add(empleado);
    }

    public void mostrarEmpleados() {
        for (Empleado empleado : empleados) {
            System.out.println(empleado.nombre + " - Sueldo: " + empleado.calcularSueldo() +
                    " - Impuesto: " + ((Impuesto) empleado).calcularImpuesto());
        }
    }
}

