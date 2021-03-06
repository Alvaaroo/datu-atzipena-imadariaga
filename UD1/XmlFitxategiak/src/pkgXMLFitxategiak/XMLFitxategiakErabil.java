/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkgXMLFitxategiak;

import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author viguera.alvaro
 */
public class XMLFitxategiakErabil {

    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();

        Document document = builder.parse(new File("Liburuak.xml"));

        Document documentBerria = builder.newDocument();
        
        
        

        System.out.println("Elementu erroa = Dokumentu elementua: " + document.getDocumentElement().getTagName());
        System.out.println("Liburu kopurua: " + document.getElementsByTagName("liburu").getLength());
        NodeList liburuNodoak = document.getElementsByTagName("liburu");
        for (int i = 0; i < liburuNodoak.getLength(); i++) {
            Node nodoa = liburuNodoak.item(i);
            Element elemLiburua = (Element) nodoa;
            System.out.print(elemLiburua.getAttribute("isbn") + " - ");
            NodeList liburuNodoarenSemeak = nodoa.getChildNodes();
            for (int j = 0; j < liburuNodoarenSemeak.getLength(); j++) {
                Node semea = liburuNodoarenSemeak.item(j);
                if (semea.getNodeName() == "izenburua") {
                    System.out.println(semea.getTextContent());
                }
            }
        }
    }

}
