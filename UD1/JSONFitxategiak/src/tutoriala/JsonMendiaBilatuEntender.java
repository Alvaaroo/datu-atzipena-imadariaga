/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tutoriala;

import java.io.FileReader;
import java.io.StringReader;
import javax.json.Json;
import javax.json.stream.JsonParser;

public class JsonMendiaBilatuEntender {

    public static void main(String[] args) {
        JsonParser parser;
        try {
            parser = Json.createParser(new FileReader("Mendiak.json"));
            while (parser.hasNext()) {
                JsonParser.Event event = parser.next();
                switch (event) {
                    case START_ARRAY:
                    case END_ARRAY:
                    case START_OBJECT:
                    case END_OBJECT:
                    case VALUE_FALSE:
                    case VALUE_NULL:
                    case VALUE_TRUE:
                        System.out.println(event.toString());
                        break;
                    case KEY_NAME:
                        System.out.print(event.toString() + " "
                                + parser.getString() + " - ");
                        break;
                    case VALUE_STRING:
                    case VALUE_NUMBER:
                        System.out.println(event.toString() + " "
                                + parser.getString());
                        break;
                }
            }
        } catch (Exception e) {

        }

    }
}
