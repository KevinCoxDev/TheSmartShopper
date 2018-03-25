package kevin.cox.thesmartshopper;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by kevin on 19/02/2018.
 */

public class FragmentList extends Fragment {


    EditText editsearch;
    RecyclerViewAdapterList adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_list, container, false);

        //Call populateList method. Creates thread to populate list from DB
        ArrayList<ShopItem> jsonList = populateList();

        RecyclerView recyclerView = view.findViewById(R.id.simpleListView);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2));
        adapter = new RecyclerViewAdapterList(getActivity(), jsonList);
        recyclerView.setAdapter(adapter);

        getActivity().findViewById(R.id.switcher_id).setVisibility(View.VISIBLE);





        // Locate the EditText in listview_main.xml
        editsearch = (EditText) getActivity().findViewById(R.id.search_text_input);

        // Capture Text in EditText
        editsearch.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable arg0) {
                // TODO Auto-generated method stub
                String text = editsearch.getText().toString().toLowerCase(Locale.getDefault());
                //adapter.filter(text);
            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1,
                                          int arg2, int arg3) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onTextChanged(CharSequence arg0, int arg1, int arg2,
                                      int arg3) {
                // TODO Auto-generated method stub
            }
        });

        return view;
    }

    private ArrayList<ShopItem> populateList(){

        ItemDataBaseHandler db = new ItemDataBaseHandler(getActivity());
        return db.getAllItems("items");
    }

}
