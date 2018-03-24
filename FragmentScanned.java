package kevin.cox.thesmartshopper;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by kevin on 19/02/2018.
 */

public class FragmentScanned extends Fragment {




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_list, container, false);

        //Call populateList method. Creates thread to populate list from DB
        ArrayList<ShopItem> jsonList = populateList();


        RecyclerView recyclerView = view.findViewById(R.id.simpleListView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        RecyclerViewAdapterList adapter = new RecyclerViewAdapterList(getActivity(), jsonList);
        recyclerView.setAdapter(adapter);



        return view;
    }

    private ArrayList<ShopItem> populateList(){

        ItemDataBaseHandler db = new ItemDataBaseHandler(getActivity());
        return db.getAllItems("scanned");
    }

    private void removeScannedItem(String itemId){

        ItemDataBaseHandler db = new ItemDataBaseHandler(getActivity());
        db.removeItem(itemId,"scanned");
    }
}
