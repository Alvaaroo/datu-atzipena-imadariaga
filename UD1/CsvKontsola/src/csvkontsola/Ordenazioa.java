package csvkontsola;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Ordenazioa {

    public static void main(String[] args) throws IOException {

        BufferedReader br = null;
        ArrayList<String> arraylista = new ArrayList();
        String linea = "";
        String erantzuna;
        Scanner sc = new Scanner(System.in);

        try {
            br = new BufferedReader(new FileReader("Mendiak.csv"));
            

            System.out.println("Nola ordenatu nahi dituzu mendiak?(altuera/izena)");
            erantzuna = sc.next();
            int lerroak = 1;

            if (erantzuna == "altuera") {
                while ((linea = br.readLine()) != null) {
                    if (lerroak != 1) {
                        String[] mendiak = linea.split(";");
                        
                        
                    }
                    lerroak++;

                }
            }else{
                System.out.println("kaka");
            }
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
