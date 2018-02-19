package kevin.cox.thesmartshopper;

import java.util.ArrayList;

/**
 * Created by kevin on 19/02/2018.
 */

public class Scanning {

    public static ArrayList<String> scanned = new ArrayList<String>();

    public ArrayList<String> getScanned(){
        return scanned;
    }

    public void addToScanned(String item){
        scanned.add(item);
    }

    public void removeFromScanned(String item){
        scanned.remove(item);
    }


}
