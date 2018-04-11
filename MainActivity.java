package kevin.cox.thesmartshopper;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;


public class MainActivity extends AppCompatActivity {


    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent myIntent = new Intent(MainActivity.this, LoginActivity.class);
        MainActivity.this.startActivity(myIntent);
        setContentView(R.layout.activity_main);
        ItemDataBaseHandler db = new ItemDataBaseHandler(this);
        Log.d("DB POPULATE 2",db.getAllItems("items").toString());
        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        BottomNavigationViewHelper.disableShiftMode(navigation);

        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        ThreadJsonPull thread = new ThreadJsonPull();
        try {
            ArrayList<ShopItem> items = thread.execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu
        // Add's any icons present in menu(toolbar.xml)
        getMenuInflater().inflate(R.menu.toolbar, menu);
        return true;
    }

    private void loadFragment(Fragment fragment) {
        // create a FragmentManager
        FragmentManager fm = getFragmentManager();
        // create a FragmentTransaction to begin the transaction and replace the Fragment
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        // replace the FrameLayout with new Fragment
        fragmentTransaction.replace(R.id.frameLayout, fragment);
        fragmentTransaction.commit(); // save the changes
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_list:
                    loadFragment(new FragmentListAvailible());
                    return true;
                case R.id.navigation_budget:
                    loadFragment(new FragmentBudget());
                    return true;
                case R.id.navigation_scan:
                    loadFragment(new FragmentScanned());
                    return true;
                case R.id.navigation_cart:
                    loadFragment(new FragmentCheckout());
                    return true;

            }
            return false;
        }
    };

    public void switcherButton1(View v)
    {

        loadFragment(new FragmentListAvailible());
        this.findViewById(R.id.left_tab_button).setBackgroundColor(getResources().getColor(R.color.colorWhite));
        this.findViewById(R.id.right_tab_button).setBackgroundColor(getResources().getColor(R.color.colorPrimary));
    }
    public void switcherButton2(View v)
    {

        loadFragment(new FragmentListMyList());
        this.findViewById(R.id.right_tab_button).setBackgroundColor(getResources().getColor(R.color.colorWhite));
        this.findViewById(R.id.left_tab_button).setBackgroundColor(getResources().getColor(R.color.colorPrimary));
    }

    public void toolbarSettings(View v)
    {
        Toast.makeText(this, "Settings", Toast.LENGTH_SHORT).show();
        loadFragment(new FragmentSettings());

    }

    public void toolbarSearch(View v)
    {
        if(this.findViewById(R.id.search_text_input_bar).getVisibility() == View.INVISIBLE){
            this.findViewById(R.id.search_text_input_bar).setVisibility(View.VISIBLE);
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.toggleSoftInput(InputMethodManager.SHOW_FORCED,0);
            switcherButton1(v);
        }
        else{
            this.findViewById(R.id.search_text_input_bar).setVisibility(View.INVISIBLE);
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.toggleSoftInput(InputMethodManager.RESULT_HIDDEN,0);
        }
    }

    public void editText(View v){

    }

    public void cameraButton(View v)
    {
        Intent myIntent = new Intent(getBaseContext(), ScanActivity.class);
        startActivity(myIntent);
    }
}

