package kevin.cox.thesmartshopper;

import java.util.ArrayList;

/**
 * Created by kevin on 19/02/2018.
 */

public class Scanning {

    public static ArrayList<ShopItem> scanned = new ArrayList<>();

    public ArrayList<ShopItem> getScanned(){
        return scanned;
    }

    public void addToScanned(ShopItem item){
        scanned.add(item);
    }

    public void removeFromScanned(String item){
        scanned.remove(item);
    }


}
