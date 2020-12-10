/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkgXMLFitxategiak;

import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XMLtoCSV {

    public static void main(String[] args) {
        String izena;
        String abizena;
        String zenbakia;
        String linea;
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(new File("ikasleak.xml"));
            FileWriter Writer = new FileWriter("ikasleak.csv");
            NodeList ikasleNodoak = document.getElementsByTagName("Ikaslea");
            for(int i=0; i<ikasleNodoak.getLength();i++){
                Node nodoa = ikasleNodoak.item(i);
                Element elemIkaslea = (Element) nodoa;
                zenbakia= elemIkaslea.getAttribute("id");
                izena= elemIkaslea.getElementsByTagName("Izena").item(0).getTextContent();
                abizena=elemIkaslea.getElementsByTagName("Abizena").item(0).getTextContent();
                linea= zenbakia + ";" + izena + ";" + abizena + "\n";
                Writer.write(linea);
                System.out.println("Ikaslea berria sartu da CSVan");
                
            }
            Writer.close();
            System.out.println("CSV fitxategia sortu da");
            

        } catch (Exception e) {

        }
    }

}
