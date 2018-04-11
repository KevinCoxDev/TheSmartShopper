package kevin.cox.thesmartshopper;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by kevin on 19/02/2018.
 */

public class FragmentListAvailible extends Fragment implements ListAdapterRecyclerView.ItemClickListener {


    private EditText editsearch;
    private ListAdapterRecyclerView adapter;
    private final int FRAGMENT_NUMBER = 0;
    private ListAdapterRecyclerView.ItemClickListener listener;
    private final String tableName = "items";
    private int quantity;
    private EditText edit;
    private ArrayList<ShopItem> filtered = new ArrayList<>();
    private Context context;

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_list, container, false);

        //Call populateList method. Creates thread to populate list from DB
        final ArrayList<ShopItem> itemList = populateList();
        listener = this;
        final RecyclerView recyclerView = view.findViewById(R.id.simpleListView);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2));
        adapter = new ListAdapterRecyclerView(getActivity(), itemList, listener, FRAGMENT_NUMBER);
        recyclerView.setAdapter(adapter);


        getActivity().findViewById(R.id.switcher_id).setVisibility(View.VISIBLE);

        context = getActivity();
        // Locate the EditText in listview_main.xml
        editsearch = (EditText) getActivity().findViewById(R.id.search_text_input_bar);
        // Capture Text in EditText
        editsearch.addTextChangedListener(new TextWatcher() {

            @SuppressLint("NewApi")
            @Override
            public void afterTextChanged(Editable arg0) {
                String text = editsearch.getText().toString().toLowerCase(Locale.getDefault());
                filter(text,itemList);
                adapter = new ListAdapterRecyclerView(context,filtered , listener, FRAGMENT_NUMBER);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1,
                                          int arg2, int arg3) {

            }

            @Override
            public void onTextChanged(CharSequence arg0, int arg1, int arg2,
                                      int arg3) {

            }
        });

        return view;
    }

    private ArrayList<ShopItem> populateList(){

        ItemDataBaseHandler db = new ItemDataBaseHandler(getActivity());
        return db.getAllItems("items");
    }

    @Override
    public void onItemClick(int itemId) {
        showDialog(getActivity(),itemId);
    }

    public void showDialog(Activity activity, final int item){
        final Dialog dialog = new Dialog(activity);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog_availiblelist);
        final ItemDataBaseHandler db = new ItemDataBaseHandler(getActivity());
        quantity = 1;

        edit = (EditText) dialog.findViewById(R.id.quantity_entry);
        

        ImageButton closeButton = (ImageButton) dialog.findViewById(R.id.close);
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        Button addButton = (Button) dialog.findViewById(R.id.add_to_list_button);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quantity = Integer.parseInt(edit.getText().toString());
                db.increaseQuantiy(item,tableName,quantity);
                dialog.dismiss();
            }
        });
        dialog.show();

    }

    public ArrayList<ShopItem> filter(String searchText, ArrayList<ShopItem> comparisionList) {
        filtered.clear();
        if (searchText.length() == 0) {
            filtered.addAll(comparisionList);
        }
        else
        {
            for (ShopItem item : comparisionList)
            {
                if (item.getItemName().toLowerCase(Locale.getDefault()).contains(searchText))
                {
                    filtered.add(item);
                    Log.d("Add to filter list", filtered.toString());

                }

            }
            return filtered;
        }
        return null;
    }
}
