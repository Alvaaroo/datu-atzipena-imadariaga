/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csvkontsola;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Probintziaka {

    public static void main(String[] args) throws IOException {
        BufferedReader br = null;
        PrintWriter outputStream1 = null;
        PrintWriter outputStream2 = null;
        PrintWriter outputStream3 = null;
        PrintWriter outputStream4 = null;
        String linea = "";

        try {
            br = new BufferedReader(new FileReader("Mendiak.csv"));
            outputStream1 = new PrintWriter(new FileWriter("Bizkaia.txt"));
            outputStream2 = new PrintWriter(new FileWriter("Gipuzkoa.txt"));
            outputStream3= new PrintWriter(new FileWriter("Araba.txt"));
            outputStream4 = new PrintWriter(new FileWriter("Nafarroa.txt"));

            int lerroak = 1;

            while ((linea = br.readLine()) != null) {
                if (lerroak != 1) {
                    String[] mendiak = linea.split(";");
                    if (mendiak[2].equals("Bizkaia")) {
                         outputStream1.write(linea+"\n");
                         
                         
                    }else if(mendiak[2].equals("Gipuzkoa")){
                        outputStream2.write(linea+"\n");
                    }else if(mendiak[2].equals("Araba")){
                        outputStream3.write(linea+"\n");
                    }else{
                        outputStream4.write(linea+"\n");
                    }
                }

                lerroak++;
            }
            outputStream1.close();
            outputStream2.close();
            outputStream3.close();
            outputStream4.close();
            
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
