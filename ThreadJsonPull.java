package kevin.cox.thesmartshopper;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by kevin on 18/02/2018.
 */


public class ThreadJsonPull extends AsyncTask<String, Void, ArrayList<ShopItem>> {

    private ArrayList<ShopItem> itemList =  new ArrayList<>();

    protected void onPreExecute() {

    }

    protected void onPostExecute(ArrayList<String> result) {
        myMethod(result);
    }

    protected ArrayList<ShopItem> doInBackground(String... sURL) {
        ArrayList<ShopItem> items = pullJSON();
        return items;
    }

    private ArrayList<String> myMethod(ArrayList myValue) {
        //handle value
        return myValue;
    }

    public ArrayList<ShopItem> pullJSON(){

        ArrayList<ShopItem> pullItemList = new ArrayList<>();

        try {
            URL url =  new URL("https://api.myjson.com/bins/16wnsb");
            Log.d("PRINT URL", String.valueOf(url));
            BufferedReader reader = new BufferedReader(new InputStreamReader((url).openStream()));
            String line;
            if ((line = reader.readLine()) != null) {
                line.replace("\\", "");
                Log.d("DB POPULATE 4", line);
                Gson gson = new GsonBuilder().create();
                Type collectionType = new TypeToken<ArrayList<ShopItem>>(){}.getType();
                pullItemList = gson.fromJson(line, collectionType);}
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.d("DB POPULATE 1", pullItemList.toString());
        return pullItemList;
    }


}
