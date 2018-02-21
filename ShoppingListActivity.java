package kevin.cox.thesmartshopper;

import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class ShoppingListActivity extends AppCompatActivity {

    private RecyclerViewAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_list);


        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        BottomNavigationViewHelper.disableShiftMode(navigation);

        ArrayList<String> jsonList = new ArrayList<>();
        JSONPullThread pullJson = new JSONPullThread();


        jsonList.add("Test");
        RecyclerView recyclerView = findViewById(R.id.simpleListView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new RecyclerViewAdapter(this, jsonList);
        //adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);

    }
}
