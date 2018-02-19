package kevin.cox.thesmartshopper;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class BaseActivity extends AppCompatActivity {


    RadioGroup radioGroup1;
    RadioButton deals;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);


        radioGroup1=(RadioGroup)findViewById(R.id.radioGroup1);
        deals = (RadioButton)findViewById(R.id.deals);
        radioGroup1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId)
            {
                Intent in;
                Log.i("matching", "matching inside1 bro" + checkedId);
                switch (checkedId)
                {
                    case R.id.navigation_list:
                        Log.i("matching", "matching inside1 matching" +  checkedId);
                        in=new Intent(getBaseContext(),ListActivity.class);
                        startActivity(in);
                        overridePendingTransition(0, 0);
                        break;
                    /*case R.id.watchList:
                        Log.i("matching", "matching inside1 watchlistAdapter" + checkedId);

                        in = new Intent(getBaseContext(), WatchlistActivity.class);
                        startActivity(in);
                        overridePendingTransition(0, 0);

                        break;*/
                    default:
                        break;
                }
            }
        });
    }
}
