package csvkontsola;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Handiena {

    public static void main(String[] args) throws IOException {

        int altuera = 0;
        String izena = "";
        BufferedReader br = null;
        String linea = "";

        try {
            br = new BufferedReader(new FileReader("Mendiak.csv"));
            int lerroak = 1;

            while ((linea = br.readLine()) != null) {
                if (lerroak != 1) {
                    String[] mendiak = linea.split(";");
                    if (Integer.parseInt(mendiak[1]) > altuera) {
                        altuera = Integer.parseInt(mendiak[1]);
                        izena = mendiak[0];
                    }
                }

                lerroak++;
            }
            System.out.println("Mendi altuena " + izena + " da. " + altuera + " metro ditu");
        } catch (FileNotFoundException e) {
            System.out.println("Artxiboa ez dago bere tokian");
        } catch (IOException e) {
            System.out.println("Sarrera/irteera errore bat gertatu da");
        } finally {
            if (br != null) {
                br.close();
            }
        }
    
}
}
