/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mendiak;

import java.io.FileOutputStream;
import java.io.IOException;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonWriter;

/**
 *
 * @author viguera.alvaro
 */
public class MendiakSortu {

    public static void main(String[] args) {
        try {
            JsonArrayBuilder jab = Json.createArrayBuilder();

            JsonObjectBuilder job = Json.createObjectBuilder();
            job.add("Mendia", "Aketegi").
                    add("Altuera", 1548).
                    add("Probintzia", "Gipuzkoa");
            JsonObject ob = job.build();

            JsonObjectBuilder job2 = Json.createObjectBuilder();
            job2.add("Mendia", "Anboto").
                    add("Altuera", 2000).
                    add("Probintzia", "Bizkaia");

            JsonObject ob2 = job2.build();

            jab.add(ob);
            jab.add(ob2);
            JsonArray ar = jab.build();

            FileOutputStream out = new FileOutputStream("Mendiak2.json");
            JsonWriter jsonWriter = Json.createWriter(out);
            jsonWriter.writeArray(ar);
            jsonWriter.close();
            out.close();
        } catch (IOException e) {
            System.out.println("kekw");
        }
    }

}
