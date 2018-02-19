package kevin.cox.thesmartshopper;

import android.os.AsyncTask;
import android.util.Log;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

/**
 * Created by kevin on 19/02/2018.
 */

public class CameraCheckThread extends AsyncTask<String,Void,BiMap> {

    private BiMap<Long, String> itemMap = HashBiMap.create();

    public CameraCheckThread() {
    }

    protected void onPreExecute() {
        //showDialog("Downloaded " + result + " bytes");
    }

    protected void onPostExecute(BiMap<Long,String> result) {
        //showDialog("Downloaded " + result + " bytes");
        myMethod(result);
    }

    protected BiMap<Long,String> doInBackground(String... sURL) {
        JSONParse list = new JSONParse();
        itemMap = list.idNameMap();
        Log.d("Thread: DoInBackground", itemMap.toString());
        return itemMap;
    }

    private BiMap myMethod(BiMap myValue) {
        //handle value
        return myValue;
    }
}