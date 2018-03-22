package kevin.cox.thesmartshopper;

import android.os.AsyncTask;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by kevin on 18/02/2018.
 */


public class JSONPullThread extends AsyncTask<String,Void,ArrayList<ShopItem>> {

    private ArrayList<ShopItem> itemList =  new ArrayList<>();

    protected void onPreExecute() {

    }

    protected void onPostExecute(ArrayList<String> result) {
        myMethod(result);
    }

    protected ArrayList<ShopItem> doInBackground(String... sURL) {
        JSONParse list = new JSONParse();
        Log.v("Pulling From JSON", list.toString());
        itemList = list.pullJSON();
        return itemList;
    }

    private ArrayList<String> myMethod(ArrayList myValue) {
        //handle value
        return myValue;
    }
}
