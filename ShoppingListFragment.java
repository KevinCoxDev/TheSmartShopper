package kevin.cox.thesmartshopper;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

/**
 * Created by kevin on 19/02/2018.
 */

public class ShoppingListFragment extends Fragment {

    View view;
    private RecyclerViewAdapter adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
// Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_shopping_list, container, false);
        ArrayList<String> jsonList = new ArrayList<>();
        JSONPullThread pullJson = new JSONPullThread();
        try {
            jsonList = pullJson.execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        jsonList.add("Test");
        RecyclerView recyclerView = view.findViewById(R.id.simpleListView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new RecyclerViewAdapter(getActivity(), jsonList);
        //adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);

        return view;
    }
}
