package tutoriala;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonWriter;

public class ZerotikSortu {

    public static void main(String[] args){

        try{
        JsonArray model = Json.createArrayBuilder()
                .add(Json.createObjectBuilder()
                    .add("Mendia","Oiz")
                    .add("Altuera",915)
                    .add("Probintzia","Bizkaia"))
                .add(Json.createObjectBuilder()
                    .add("Mendia","Gorbea")
                    .add("Altuera",975)
                    .add("Probintzia","Gipuzkoa"))
              
                .build();
        System.out.println(model);
        
        FileOutputStream out = new FileOutputStream("datuberriak.json");
        JsonWriter jsonWriter = Json.createWriter(out);
        jsonWriter.writeArray(model);
        jsonWriter.close();
        out.close();
        }catch(IOException e){
            System.out.println("kekw");
        }
    }

}
