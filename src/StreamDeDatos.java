import java.io.FileWriter;
import java.io.IOException;

public class StreamDeDatos {

    public void escribir(){

        String texto = "Esto se escribio en el achivo del progama";

        try{

            FileWriter escritura = new FileWriter("C:\\Users\\Usuario\\OneDrive\\Escritorio\\SreamDeDatos.txt");


            for (int i = 0; i < texto.length(); i++){

                escritura.write((texto.charAt(i)));

            }



        } catch (Exception e) {
            e.printStackTrace();
        }


    }




}
