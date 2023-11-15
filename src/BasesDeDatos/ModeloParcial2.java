package BasesDeDatos;

import javax.print.Doc;
import java.sql.*;

//metodo para Agregar un nuevo doctor.
//Cambiar especialidad de doctor.
//eliminar un doctor



//1. define la clase abstracta Persona con atributos comunes para pacientes y doctores.
abstract class Persona {
    private String nombre;
    private int edad;


    //constructor y metodos necesarios
    public Persona( String nombre, int edad) {

        this.nombre = nombre;
        this.edad = edad;
    }


    public String getNombre() {
        return nombre;
    }

    public int getEdad() {
        return edad;
    }
}

//2. Implementa la calse Paciente que hereda de Persona con atributos adicionales como historial medico.
class Paciente extends Persona{

    private String historialMedico;
    private int doctorCabecera;
    protected Date fechaIngreso;


    public Paciente(String nombre, int edad, String historialMedico,  int doctorCabecera, Date fechaIngreso){

        super( nombre, edad);
        this.historialMedico = historialMedico;
        this.doctorCabecera = doctorCabecera;
        this.fechaIngreso = fechaIngreso;
    }


    public String getHistorialMedico() {
        return historialMedico;
    }

    public int getDoctorCabecera() {
        return doctorCabecera;
    }

    public Date getFechaIngreso() {return fechaIngreso;
    }
}

//3. Implementa la clase Doctor que hereda de Persona con atributos adicionales como especialidad.
class Doctor extends Persona{
    private String especialidad;


    public Doctor( String nombre, int edad, String especialidad){
        super( nombre, edad);
        this.especialidad = especialidad;

    }

    public String getEspecialidad() {
        return especialidad;
    }
}




class Hospital{

    public void agregarPaciente(Paciente paciente){
        //Agregar el paciente a la base de datos
        String consulta = "INSERT INTO pacientes (nombre, edad, historial_medico, doctor, fecha_ingreso) VALUES ('" + paciente.getNombre() + "', " + paciente.getEdad() + ", '" + paciente.getHistorialMedico() + "', " + paciente.getDoctorCabecera() + ", '" + paciente.getFechaIngreso() + "')";
        DBHelper.ejecutarConsulta(consulta);
    }

    public void agregarDoctor(Doctor doctor){
        //Sgregar un doctor a la base de datos
        String consulta = "INSERT INTO `doctores` (nombre, edad, especialidad) VALUES ('"+ doctor.getNombre()+ "', '" +doctor.getEdad()+ "', '" +doctor.getEspecialidad()+"' )";
        DBHelper.ejecutarConsulta(consulta);

    }

    //elimine un paciente undicando su nombre
    public void eliminarPaciente(String nombre){
        //eliminar paciente de la base de datos
        String consulta = "DELETE FROM pacientes WHERE nombre = '" + nombre + "'";
        DBHelper.ejecutarConsulta(consulta);
    }


    public void eliminarDoctor (String nombre){
        //eliminar doctor de la base de dato
        String consulta = "DELETE FROM doctores WHERE nombre = '" + nombre + "'";
        DBHelper.ejecutarConsulta(consulta);


    }




    //metodo para asignar un doctor de cabecera a un paciente indicando el nombre del doctor y el nombre del paciente
    public void asignarDoctorCabecera(String nombreDoctor, String nombrePaciente){
        String consulta = "UPDATE pacientes SET doctor = (SELECT id FROM doctores WHERE nombre = '"+nombreDoctor+"') WHERE nombre = '"+nombrePaciente+"'";
        DBHelper.ejecutarConsulta(consulta);
    }



    public void listarPacientes() {
        String consulta = "SELECT * FROM pacientes";
        ResultSet resultado = DBHelper.ejecutarConsultaConResultado(consulta);
        listarPacientes(resultado);
    }


    public void listarDoctores() {
        String consulta = "SELECT * FROM doctores";
        ResultSet resultado = DBHelper.ejecutarConsultaConResultado(consulta);
        listarDoctores(resultado);
    }


    public void listarPacientesEntreDosFechas(Date fechaDesde, Date fechaHasta){
        String consulta = "SELECT * FROM pacientes WHERE fecha_ingreso BETWEEN '"+fechaDesde+"' AND '"+fechaHasta+"';";
        ResultSet resultado = DBHelper.ejecutarConsultaConResultado(consulta);
        listarPacientes(resultado);
    }


    //mostrar listado de pacientes
    public void listarPacientes(ResultSet resultado){

        if(resultado != null){

            try{
                System.out.println("Lista de Pacientes:");
                System.out.println();
                System.out.printf("%-10s %-15s %-5s %-20s %-12s %-10s\n", "ID", "Nombre", "Edad", "Historial Médico", "Fecha Ingreso", "Doctor");

                while (resultado.next()){

                    int id = resultado.getInt("id");
                    String nombre = resultado.getString("nombre");
                    int edad = resultado.getInt("edad");
                    String historialMedico = resultado.getString("historial_medico");
                    Date fechaIngreso = resultado.getDate("fecha_ingreso");
                    int idDoctor = resultado.getInt("doctor");

                    System.out.printf("%-10d %-15s %-5d %-20s %-12s %-10d\n", id, nombre, edad, historialMedico, fechaIngreso, idDoctor);


                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }


    //listar doctores:
    public void listarDoctores(ResultSet resultado){

        if(resultado != null){

            try{
                System.out.println("Lista de Doctores:");
                System.out.println();
                System.out.printf("%-10s %-15s %-5s %-20s \n", "ID", "Nombre", "Edad", "Especialidad");

                while (resultado.next()){

                    int id = resultado.getInt("id");
                    String nombre = resultado.getString("nombre");
                    int edad = resultado.getInt("edad");
                    String especialidad = resultado.getString("Especialidad");


                    System.out.printf("%-10d %-15s %-5d %-20s \n", id, nombre, edad, especialidad);


                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }




}


class DBHelper {
    private static final String URL = "jdbc:mysql://localhost:3306/hospital_db";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    // Método para ejecutar una consulta sin devolver resultados
    public static void ejecutarConsulta(String consulta) {
        try {
            // Establecer la conexión con la base de datos
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);

            // Crear la declaración
            try (PreparedStatement statement = connection.prepareStatement(consulta)) {
                // Ejecutar la consulta
                statement.executeUpdate();
            }

            // Cerrar la conexión
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para ejecutar una consulta y devolver un conjunto de resultados
    public static ResultSet ejecutarConsultaConResultado(String consulta) {
        try {
            // Establecer la conexión con la base de datos
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);

            // Crear la declaración
            PreparedStatement statement = connection.prepareStatement(consulta);

            // Ejecutar la consulta y devolver el conjunto de resultados
            return statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

}


class HospitalBaseDeDatos{
    //main del programa
    public static void main(String[] args) {

        //creamos un objeto de la clasae hospital
        Hospital hospital = new Hospital();


        //PACIENTES:

        //agregar un paciente de ejemplo:
        Date fechaActual = new Date(2023 - 1900, 1 - 1, 15);

        //Paciente paciente = new Paciente( "juan pedro", 35, "le duele la espalda", 2,fechaActual );
        //hospital.agregarPaciente(paciente);

        //eliminar un paciente de ejemplo
        //hospital.eliminarPaciente("pepazo");

        //asignar un doctor de cabecera a un paciente de ejemplo:
        //hospital.asignarDoctorCabecera("Doctor2","Juan Bonete");

        //listar todos los pacientes:
        //hospital.listarPacientes();

        //listar pacientes entre dos fechas:
        //Date fechaDesde = new Date(2023 - 1900, 1 -1, 10);
        //Date fechaHasta = new Date( 2023 - 1900, 2 -1, 15);
        //hospital.listarPacientesEntreDosFechas(fechaDesde, fechaHasta);



        //DOCTORES:
        // Agregar un Doctor de ejemplo:
        //Doctor doctor = new Doctor("pedro", 65, "Odontologia");
        // hospital.agregarDoctor(doctor);

        //eliminar doctor de ejemplo
        //hospital.eliminarDoctor("Julio");

        //listar todos los doctores:
        hospital.listarDoctores();

    }


}