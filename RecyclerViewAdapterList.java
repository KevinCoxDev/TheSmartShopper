package kevin.cox.thesmartshopper;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by kevin on 17/02/2018.
 */

public class RecyclerViewAdapterList extends RecyclerView.Adapter<RecyclerViewAdapterList.ViewHolder> {

    private ArrayList<ShopItem> mData = new ArrayList<>();
    private ArrayList<ShopItem> filtered = mData;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;

    // data is passed into the constructor
    public RecyclerViewAdapterList(Context context, ArrayList<ShopItem> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
    }

    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.cardview_complete_layout, parent, false);
        ViewHolder viewHolder =  new ViewHolder(view);
        return viewHolder;
    }

    // binds the data to the textview in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ShopItem item = mData.get(position);
        holder.myTextView1.setText(item.getItemName());
        holder.myTextView2.setText("€"+String.format("%.02f",item.getItemPrice()));
        //holder.myTextView3.setText(item.getItemQuantity());
    }

    // total number of rows
    @Override
    public int getItemCount() {
        return mData.size();
    }



    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView myTextView1;
        public TextView myTextView2;
        public TextView myTextView3;
        public LinearLayout mLinearLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            myTextView1 = itemView.findViewById(R.id.TITLE_TEXT);
            myTextView2 = itemView.findViewById(R.id.SUB_TEXT);
            myTextView3 = itemView.findViewById(R.id.quantity_view);
            mLinearLayout = itemView.findViewById(R.id.simpleListView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    // convenience method for getting data at click position
    public String getItem(int id) {
        return mData.get(id).getItemName();
    }

    // allows clicks events to be caught
    public void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }

    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        filtered.clear();
        if (charText.length() == 0) {
            filtered.addAll(mData);
        }
        else
        {
            for (ShopItem item : mData)
            {
                if (item.getItemName().toLowerCase(Locale.getDefault()).contains(charText))
                {
                    filtered.add(item);
                    Log.d("Add to filter list", filtered.toString());
                }
            }
        }
        notifyDataSetChanged();
    }
}
