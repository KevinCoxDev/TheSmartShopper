package kevin.cox.thesmartshopper;

import android.os.AsyncTask;

import java.util.ArrayList;

/**
 * Created by kevin on 18/02/2018.
 */


public class JSONPullThread extends AsyncTask<String,Void,ArrayList<String>> {

    private ArrayList<String> nameList =  new ArrayList<>();

    protected void onPreExecute() {
        //showDialog("Downloaded " + result + " bytes");
    }

    protected void onPostExecute(ArrayList<String> result) {
        //showDialog("Downloaded " + result + " bytes");
        myMethod(result);
    }

    protected ArrayList<String> doInBackground(String... sURL) {
        JSONParse list = new JSONParse();
        nameList = list.nameList();
        return nameList;
    }

    private ArrayList<String> myMethod(ArrayList myValue) {
        //handle value
        return myValue;
    }
}
