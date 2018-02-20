package kevin.cox.thesmartshopper;

import android.util.Log;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by kevin on 18/02/2018.
 */

public class JSONParse {

    private ArrayList<Integer> itemIds = new ArrayList<>();
    private ArrayList<String> itemNames = new ArrayList<>();
    private ArrayList<ShopItem> parseItemList = new ArrayList<>();
    private ArrayList<ShopItem> pullItemList = new ArrayList<>();
    private Gson gson = new Gson();
    private Type listType = new TypeToken<ArrayList<ShopItem>>() {}.getType();


    public ArrayList<ShopItem> pullJSON(){

        try {
            URL url =  new URL("https://api.myjson.com/bins/ar7kh");
            Log.d("PRINT URL", String.valueOf(url));
            Reader reader = new InputStreamReader((url).openStream()); //Read the json output
            Gson gson = new GsonBuilder().create();
            Type collectionType = new TypeToken<Collection<ShopItem>>(){}.getType();
            pullItemList = gson.fromJson(reader, collectionType);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.d("PRINT", String.valueOf(pullItemList));
        return pullItemList;
    }

    public ArrayList<String> nameList() {

        parseItemList = pullJSON();
        Log.d("PRINT",parseItemList.toString());
        if(parseItemList != null) {
            for (int i = 0; i < parseItemList.size(); i++) {
                itemNames.add(parseItemList.get(i).getItemName());
            }
        }
        return itemNames;
    }

    public ArrayList<Integer> idList() {

        parseItemList = pullJSON();
        Log.d("PRINT",parseItemList.toString());
        if(parseItemList != null) {
            for (int i = 0; i < parseItemList.size(); i++) {
                //itemIds.add(parseItemList.get(i).getItemId());
            }
        }
        return itemIds;
    }

    public BiMap<Integer,String> idNameMap(){

        BiMap<Integer, String> idName = HashBiMap.create();
        parseItemList = pullJSON();
        Log.d("JSONParse: idNameMap",parseItemList.toString());
        if(parseItemList != null) {
            for (int i = 0; i < parseItemList.size(); i++) {
                idName.put(parseItemList.get(i).getItemId(),parseItemList.get(i).getItemName());
            }
        }

        return idName;
    }

}
