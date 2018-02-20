package kevin.cox.thesmartshopper;

import android.os.AsyncTask;

import java.util.ArrayList;

/**
 * Created by kevin on 18/02/2018.
 */


public class JSONPullThread extends AsyncTask<String,Void,ArrayList<ShopItem>> {

    private ArrayList<ShopItem> itemList =  new ArrayList<>();

    protected void onPreExecute() {
        //showDialog("Downloaded " + result + " bytes");
    }

    protected void onPostExecute(ArrayList<String> result) {
        //showDialog("Downloaded " + result + " bytes");
        myMethod(result);
    }

    protected ArrayList<ShopItem> doInBackground(String... sURL) {
        JSONParse list = new JSONParse();
        itemList = list.pullJSON();
        return itemList;
    }

    private ArrayList<String> myMethod(ArrayList myValue) {
        //handle value
        return myValue;
    }
}
