package kevin.cox.thesmartshopper;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class MainActivity extends AppCompatActivity {


    private ItemDataBaseHandler db = new ItemDataBaseHandler(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        BottomNavigationViewHelper.disableShiftMode(navigation);

        Toolbar toolbar = (Toolbar)findViewById(R.id.settings_toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton scanButton = (FloatingActionButton) findViewById(R.id.fab);
        scanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                scanActivity();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu
        // Add's any icons present in menu(toolbar.xml)
        getMenuInflater().inflate(R.menu.toolbar, menu);
        return true;
    }

    public void scanActivity(){
        Intent myIntent = new Intent(getBaseContext(), ScanActivity.class);
        startActivity(myIntent);
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
                    loadFragment(new ShoppingListFragment());
                    return true;
                case R.id.navigation_budget:
                    Intent loginIntent = new Intent(getBaseContext(), LoginActivity.class);
                    startActivity(loginIntent);
                    return true;
                case R.id.navigation_scan:
                    loadFragment(new ScannedListFragment());
                    return true;
                case R.id.navigation_cart:

                    return true;
                case R.id.navigation_settings:
                    loadFragment(new SettingsFragment());
                    return true;



            }
            return false;
        }
    };

}

