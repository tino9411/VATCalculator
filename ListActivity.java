package uk.ac.ucl.vatcalculator2;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

// Class for the list
public class ListActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rates);

        //creating list view object
        ListView countryList = (ListView) findViewById(R.id.listView);

        // Create an ArrayAdapter using the string array and filling list
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.countries_list, android.R.layout.simple_list_item_1);

        //apply the adapter to the list view object
        countryList.setAdapter(adapter);


    }



}

