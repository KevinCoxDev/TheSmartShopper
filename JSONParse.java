package kevin.cox.thesmartshopper;

import android.util.Log;

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

    public ArrayList<ShopItem> pullJSON(){

        ArrayList<ShopItem> pullItemList = new ArrayList<>();
        try {
            URL url =  new URL("https://api.myjson.com/bins/18q4nj");
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

        return pullItemList;
    }
}
