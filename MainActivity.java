package uk.ac.ucl.vatcalculator2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.text.Editable; // for EditText event handling
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;
import android.view.View;

import java.text.NumberFormat;


public class MainActivity extends Activity implements OnItemSelectedListener {
    private static final NumberFormat currencyFormat =
            NumberFormat.getCurrencyInstance();

    private double initialAmount = 0.0; // bill amount entered by the user
    private TextView amountTextView; // shows formatted initial amount
    Spinner countrySpinner; // shows selected country
    private TextView vatTextView; // shows calculated VAT amount
    private TextView totalTextView; // shows calculated total
    private double percent;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // get references to programmatically manipulated TextViews
        amountTextView = (TextView) findViewById(R.id.amountTextView);
        vatTextView = (TextView) findViewById(R.id.vatTextView);
        totalTextView = (TextView) findViewById(R.id.totalTextView);
        vatTextView.setText(currencyFormat.format(0));// set text to 0
        totalTextView.setText(currencyFormat.format(0));// set text to 0
        countrySpinner = (Spinner) findViewById(R.id.countrySpinnerView);
        countrySpinner.setOnItemSelectedListener(this);
        // Create an ArrayAdapter using the string array and default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.countries_list_for_settings, android.R.layout.simple_spinner_item);
        //Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //apply the adapter to the spinner
        countrySpinner.setAdapter(adapter);
        // set amountEditText's TextWatcher
        EditText amountEditText =
                (EditText) findViewById(R.id.amountEditText);
            amountEditText.addTextChangedListener(amountEditTextWatcher);
        }

    /*When a country on the spinner is selected
    Calculate method ensures that total and vat is calculated
    when the user changes the country selected */
    @Override
    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id){
        Object countrySelected = parent.getItemAtPosition(pos).toString();

            switch (countrySelected.toString()){
            case "Austria":
                    percent = 0.20;
                calculate();
                break;

                case "Belgium":
                    percent = 0.21;
                    calculate();
                    break;

                case "Croatia":
                    percent = 0.25;
                    calculate();
                    break;
                case "Czech Republic":
                    percent = 0.21;
                    calculate();
                    break;

                case "Denmark":
                    percent = 0.25;
                    calculate();
                    break;
                case "Estonia":
                    percent = 0.20;
                    calculate();
                    break;
                case "Finland":
                    percent = 0.24;
                    calculate();
                    break;
                case "France":
                    percent = 0.20;
                    calculate();
                    break;
                case "Germany":
                    percent = 0.19;
                    calculate();
                    break;
                case "Great Britain":
                    percent = 0.20;
                    calculate();
                    break;
                case "Greece":
                    percent = 0.23;
                    calculate();
                    break;
                case "Hungary":
                    percent = 0.27;
                    break;
                case "Ireland":
                    percent = 0.23;
                    break;
                case "Italy":
                    percent = 0.22;
                    calculate();
                    break;
                case "Latvia":
                    percent = 0.21;
                    calculate();
                    break;
                case "Lithuania":
                    percent = 0.21;
                    calculate();
                    break;
                case "Luxembourg":
                    percent = 0.15;
                    calculate();
                    break;
                case "Netherlands":
                    percent = 0.21;
                    calculate();
                    break;
                case "Norway":
                    percent = 0.25;
                    calculate();
                    break;
                case "Poland":
                    percent = 0.23;
                    calculate();
                    break;
                case "Portugal":
                    percent = 0.23;
                    calculate();
                    break;
                case "Romania":
                    percent = 0.24;
                    calculate();
                    break;
                case "Slovakia":
                    percent = 0.20;
                    calculate();
                    break;
                case "Slovenia":
                    percent = 0.22;
                    calculate();
                    break;
                case "Spain":
                    percent = 0.21;
                    calculate();
                    break;
                case "Sweden":
                    percent = 0.25;
                    calculate();
                    break;
                case "Switzerland":
                    percent = 0.08;
                    calculate();
                    break;
                case "Turkey":
                    percent = 0.18;
                    calculate();
                    break;

            }
        }

    // method to calculate the vat amount
    public void calculate(){

                double vat = initialAmount * percent;
                double total = initialAmount + vat;

                //display vat and total formatted as currency
                vatTextView.setText(currencyFormat.format(vat));
                totalTextView.setText(currencyFormat.format(total));

    }


    @Override
    public void onNothingSelected(AdapterView<?> parent){

    }


    // listener object for the EditText's text-changed events
    private final TextWatcher amountEditTextWatcher = new TextWatcher() {
        //called when the user modifies the initial amount
        @Override
        public void onTextChanged(CharSequence s, int start,
                                  int before, int count) {

            try { // get initial amount and display currency formulated value
                initialAmount = Double.parseDouble(s.toString()) / 100.0;
                amountTextView.setText(currencyFormat.format(initialAmount));
            }
            catch (NumberFormatException e) {//if s is empty or non-numeric
                amountTextView.setText("");
                initialAmount = 0.0;
            }
            calculate();

        }

        @Override
        public void afterTextChanged(Editable s) {}

        @Override
        public void beforeTextChanged(
                CharSequence s, int start, int count, int after) {}
    };



    //called when the user touches the button
    public void openRatesActivity(View view){
        Intent intent = new Intent(this, ListActivity.class);
        startActivity(intent);


    }


}
