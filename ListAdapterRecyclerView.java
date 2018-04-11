package kevin.cox.thesmartshopper;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by kevin on 17/02/2018.
 */

public class ListAdapterRecyclerView extends RecyclerView.Adapter<ListAdapterRecyclerView.ViewHolder> {

    private ArrayList<ShopItem> mData = new ArrayList<>();
    private LayoutInflater mInflater;
    private ItemClickListener clickListener;
    private View view;
    private int fragment_number;


    // data is passed into the constructor
    public ListAdapterRecyclerView(Context context, ArrayList<ShopItem> data, ItemClickListener listener, int fragmentNumber) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
        this.clickListener = listener;
        this.fragment_number = fragmentNumber;
    }

    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        view = mInflater.inflate(R.layout.cardview_mylist_layout, parent, false);
        ViewHolder viewHolder =  new ViewHolder(view);
        return viewHolder;
    }

    // binds the data to the textview in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        ShopItem item = mData.get(position);
        holder.myTextView1.setText(item.getItemName());
        if(fragment_number == 0){
            holder.myTextView2.setText("€"+String.format("%.02f",item.getItemPrice()));
        }
        else{
            holder.myTextView2.setText("Quantity: " + item.getItemQuantity());
        }
        holder.itemId = item.getItemId();
        holder.itemQuantity = item.getItemQuantity();
        Picasso.get().load(item.getItemImage()).into(holder.thumbnail);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView myTextView1;
        public TextView myTextView2;
        public ImageView thumbnail;
        public LinearLayout mLinearLayout;
        public int itemId;
        public int itemQuantity;

        public ViewHolder(final View itemView) {
            super(itemView);
            myTextView1 = itemView.findViewById(R.id.TITLE_TEXT);
            myTextView2 = itemView.findViewById(R.id.SUB_TEXT);
            mLinearLayout = itemView.findViewById(R.id.simpleListView);
            thumbnail = itemView.findViewById(R.id.thumbnail);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickListener.onItemClick(itemId);
                }
            });
        }
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(int itemId);
    }


    public ShopItem getSelectedItem(int position){
        return mData.get(position);
    }

}
