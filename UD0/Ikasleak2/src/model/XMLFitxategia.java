/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.File;
import java.io.IOException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;

public class XMLFitxategia {

    private static Document document;

    public static ObservableList<Ikaslea> datuakMemorianKargatu(String fitxategia) throws ParserConfigurationException, SAXException, IOException {

        ObservableList<Ikaslea> data = FXCollections.observableArrayList();

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        // Fitxategikitik abiatuta
        Document document = builder.parse(new File(fitxategia));

        NodeList liburuNodoak = document.getElementsByTagName("liburu");
        for (int i = 0; i < liburuNodoak.getLength(); i++) {

        }

        return data;
    }

    public static boolean datuakFitxategianGorde(ObservableList<Ikaslea> lista, String fitxategia) throws TransformerConfigurationException, TransformerException, ParserConfigurationException {

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        // Fitxategikitik abiatuta
        document = builder.newDocument();
        
        

        for (int i = 0; i < lista.size(); i++) {

            Element elemIkaslea = document.createElement("Ikaslea");
            Element elemId = document.createElement("id");
            Element elemIzena = document.createElement("izena");
            Element elemAbizena = document.createElement("abizena");
            Text textId = document.createTextNode(lista.get(i).getZenbakia() + "");
            Text textIzena = document.createTextNode(lista.get(i).getIzena());
            Text textAbizena = document.createTextNode(lista.get(i).getAbizena1());
            document.getDocumentElement().appendChild(elemIkaslea);
            elemIkaslea.appendChild(elemId);
            elemId.appendChild(textId);
            elemIkaslea.appendChild(elemIzena);
            elemIzena.appendChild(textIzena);
            elemIkaslea.appendChild(elemAbizena);
            elemAbizena.appendChild(textAbizena);

        }

        DOMSource source = new DOMSource(document);

        StreamResult result = new StreamResult(new java.io.File(fitxategia));
        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.transform(source, result);

        return true;
    }
}
