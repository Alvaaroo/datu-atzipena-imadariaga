
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author viguera.alvaro
 */
public class CopyCharactersOrdezkatuzArgumentuekin {
    public static void main(String[] args) throws IOException  {
        
        FileInputStream in = null;
        FileOutputStream out = null;
        
        try {
            in = new FileInputStream("xanadu.txt");
            out = new FileOutputStream("CopyCharactersOrdezkatuzArgumentuekin.txt");
            int c;

            while ((c = in.read()) != -1) {
                if(c == args[0].charAt(0)){
                    out.write(args[1].charAt(0));
                }else{
                    out.write(c);
                }
                             
            }
        } finally {
            if (in != null) {
                in.close();
            }
            if (out != null) {
                out.close();
            }
        }
    }
}
