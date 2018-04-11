
package kevin.cox.thesmartshopper;

/**
 * The FragmentScanned Class contains displays for list of items
 * which have been scanned/are yet to be scanned as well as
 * functionality for changing their quantities
 *
 * @author  Kevin Cox
 * @version 1.2
 * @since   2018-04-10
 */

import android.app.Dialog;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.ToggleButton;
import java.util.ArrayList;


public class FragmentScanned extends Fragment implements ListAdapterRecyclerView.ItemClickListener{


    private ListAdapterRecyclerView.ItemClickListener listener;
    private final int FRAGMENT_NUMBER = 2;
    private ArrayList<ShopItem> scannedList = new ArrayList<>();
    private ArrayList<ShopItem> unScannedList = new ArrayList<>();
    private String tableName = "SCANNED";
    private int displayInt;
    private boolean listChoice = true;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_scanned_list, container, false);
        // Populate both the scanned and unscanned lists
        scannedList = populateScannedList();
        unScannedList = populateUnscannedList();
        // Initialise Recyclerview on click listener
        listener = this;
        // Initialise  final vars recyclerView and b from scanned layout
        final RecyclerView recyclerView = view.findViewById(R.id.simpleListViewScanned);
        final ToggleButton b = (ToggleButton) view.findViewById(R.id.switch_scanned_list);

        b.setTextOff("Scanned");
        b.setTextOn("Unscanned");
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


        // get your ToggleButton

        listDisplay(b,recyclerView,view);
        // attach an OnClickListener
        b.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                listDisplay(b,recyclerView,view);
            }
        });
        getActivity().findViewById(R.id.switcher_id).setVisibility(View.GONE);

        return view;
    }


    private void listDisplay(ToggleButton b, RecyclerView r, View v){
        TextView sub = (TextView) v.findViewById(R.id.scanned_sub_title);
        TextView title = (TextView) v.findViewById(R.id.scanned_title);
        if(!b.isChecked()){

            sub.setText(R.string.scanned_text);
            title.setText(R.string.scanned_title);
            ListAdapterRecyclerView adapter = new ListAdapterRecyclerView(getActivity(), scannedList, listener, FRAGMENT_NUMBER);
            r.setAdapter(adapter);
            listChoice= false;
        }
        else{
            sub.setText(R.string.unscanned_text);
            title.setText(R.string.unscanned_title);
            ListAdapterRecyclerView adapter = new ListAdapterRecyclerView(getActivity(), unScannedList, listener, FRAGMENT_NUMBER);
            r.setAdapter(adapter);
            listChoice= true;
        }
    }

    private ArrayList<ShopItem> populateScannedList(){

        ItemDataBaseHandler db = new ItemDataBaseHandler(getActivity());
        ArrayList<ShopItem> items = db.getAllSelectedItems("scanned");
        Log.d("CHECK",items.toString());
        db.close();
        return items;
    }

    private ArrayList<ShopItem> populateUnscannedList(){

        ItemDataBaseHandler db = new ItemDataBaseHandler(getActivity());
        ArrayList<ShopItem> itemlist = db.getAllSelectedItems("items");
        for(int i = 0; i<itemlist.size();i++){
            if(db.getItem(itemlist.get(i).getItemId(),"scanned") != null){
                ShopItem item = itemlist.get(i);
                int remaining = item.getItemQuantity() - getItemById(item.getItemId()).getItemQuantity();
                if(remaining > 0){
                    itemlist.get(i).setItemQuantity(remaining);
                    unScannedList.add(itemlist.get(i));
                }
            }
            else{
                unScannedList.add(itemlist.get(i));
            }
        }

        return unScannedList;
    }

    private ShopItem getItemById(int id){
        for(int i = 0; i<scannedList.size();i++){
            if(scannedList.get(i).getItemId() == id){
                return scannedList.get(i);
            }
        }
        return null;
    }


    @Override
    public void onItemClick(int itemId) {
        if(!listChoice) {
            showDialog(getActivity(), itemId);
        }
    }

    public void showDialog(Context context, final int item){
        final Dialog dialog = new Dialog(getActivity());
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog_mylist);
        final ItemDataBaseHandler db = new ItemDataBaseHandler(context);

        final TextView text = (TextView) dialog.findViewById(R.id.quantity_entry);
        displayInt = db.getItem(item,tableName).getItemQuantity();

        final int quant = displayInt;
        text.setText(String.valueOf(displayInt));
        ImageButton closeButton = (ImageButton) dialog.findViewById(R.id.close);
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        Button plusButton = (Button) dialog.findViewById(R.id.plus_button);
        plusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayInt += 1;
                text.setText(String.valueOf(displayInt));
            }
        });
        Button minusButton = (Button) dialog.findViewById(R.id.minus_button);
        minusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayInt -= 1;
                text.setText(String.valueOf(displayInt));
            }
        });
        Button clearButton = (Button) dialog.findViewById(R.id.approve_quantity_change);
        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.increaseQuantiy(item,tableName,displayInt-quant);
                dialog.dismiss();
            }
        });
        dialog.show();

    }
}
