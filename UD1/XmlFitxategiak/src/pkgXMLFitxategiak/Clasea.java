/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkgXMLFitxategiak;

import java.util.ArrayList;


/**
 *
 * @author maneru.mikel
 */
public class Clasea {
    String clasea;
    ArrayList<Ikaslea> ikaslea;

    public String getClasea() {
        return clasea;
    }

    public void setClasea(String clasea) {
        this.clasea = clasea;
    }

    public ArrayList<Ikaslea> getClaseak() {
        return ikaslea;
    }

    public void setClaseak(ArrayList<Ikaslea> claseak) {
        this.ikaslea = claseak;
    }

    public Clasea(String clasea, ArrayList<Ikaslea> claseak) {
        this.clasea = clasea;
        this.ikaslea = claseak;
    }
   
}
