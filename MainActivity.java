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
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.List;
import java.util.concurrent.ExecutionException;


public class MainActivity extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        populateDatabase();
        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        BottomNavigationViewHelper.disableShiftMode(navigation);

        //Creates toolbar object
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);


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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.action_settings:
                Intent myIntent = new Intent(getBaseContext(), SettingsActivity.class);
                startActivity(myIntent);

            case R.id.action_logout:
                // User chose the "Favorite" action, mark the current item
                // as a favorite...
                return true;
            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }

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
                    Intent myIntent = new Intent(getBaseContext(), ShoppingListActivity.class);
                    startActivity(myIntent);
                    return true;
                case R.id.navigation_budget:

                    return true;
                case R.id.navigation_scan:
                    loadFragment(new ScannedListFragment());
                    return true;
                case R.id.navigation_cart:
                    loadFragment(new ScannedListFragment());
                    return true;

            }
            return false;
        }
    };

    private void populateDatabase(){
        ItemDataBaseHandler db = new ItemDataBaseHandler(this);

        //ShopItem shop1 = new ShopItem(167,"Tea Bags",3.55,"https://cdn.shopify.com/s/files/1/0561/3553/products/UK-112_Barry_s_Tea_Gold_Blend_Tea_Bags_80_ct._8.8oz._250g.jpg?v=1504902460");
        //db.addItem(shop1);
        try {
            db.populateFromJson("Replace with url","items");
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // Reading all items
        Log.d("Reading: ", "Reading all items..");
        List<ShopItem> items = db.getAllItems("items");
        for (ShopItem item : items) {
            String log = "Id: "+item.getItemId()+" ,Name: " + item.getItemName() + " ,Price: " + item.getItemPrice() + " ,URL: " + item.getImageURL();
            // Writing Contacts to log
            Log.d("Name: ", log);
        }

        Log.d("SHOP ITEM",items.toString());
    }

}

