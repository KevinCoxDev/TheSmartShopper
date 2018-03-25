package kevin.cox.thesmartshopper;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {


    private ItemDataBaseHandler db = new ItemDataBaseHandler(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        BottomNavigationViewHelper.disableShiftMode(navigation);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



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
                    loadFragment(new FragmentList());
                    return true;
                case R.id.navigation_budget:
                    loadFragment(new FragmentBudget());
                    return true;
                case R.id.navigation_scan:
                    loadFragment(new FragmentScanned());
                    return true;
                case R.id.navigation_cart:
                    Intent loginIntent = new Intent(getBaseContext(), LoginActivity.class);
                    startActivity(loginIntent);
                    return true;
                case R.id.navigation_settings:
                    //loadFragment(new FragmentDrawer());
                    Intent myIntent = new Intent(getBaseContext(), ScanActivity.class);
                    startActivity(myIntent);
                    return true;

            }
            return false;
        }
    };

    public void switcherButton1(View v)
    {
        Toast.makeText(this, "Clicked on Button", Toast.LENGTH_SHORT).show();
        loadFragment(new FragmentList());
    }

    public void switcherButton2(View v)
    {
        Toast.makeText(this, "Clicked on Button2", Toast.LENGTH_SHORT).show();
        loadFragment(new FragmentListSelected());
    }

    public void addToListButton(View v)
    {
        Toast.makeText(this, "Added to Cart", Toast.LENGTH_SHORT).show();
        db.changeQuantity("17","items",1);
    }

    public void minusQuantityButton(View v)
    {
        Toast.makeText(this, "Added to Cart", Toast.LENGTH_SHORT).show();
        db.changeQuantity("17","items",-1);
    }

    public void toolbarMenu(View v)
    {
        Toast.makeText(this, "Added to Cart", Toast.LENGTH_SHORT).show();

    }

    public void toolbarSearch(View v)
    {
        Toast.makeText(this, "Added to Cart", Toast.LENGTH_SHORT).show();
        if(this.findViewById(R.id.search_text_input).getVisibility() == View.INVISIBLE){
            this.findViewById(R.id.search_text_input).setVisibility(View.VISIBLE);
        }
        else{
            this.findViewById(R.id.search_text_input).setVisibility(View.INVISIBLE);
        }
    }


}

