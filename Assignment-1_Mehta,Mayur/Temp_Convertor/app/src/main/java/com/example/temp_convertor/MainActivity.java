package com.example.temp_convertor;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    boolean start;
    TextView output_Val;
    TextView history_Val;
    private static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        output_Val = findViewById(R.id.temp_Output);
        history_Val = findViewById(R.id.history_Box);
        Button clrbtn = findViewById(R.id.clr_btn);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        clrbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Set the first EditText empty
                history_Val.setText("");

            }
        });
    }


    public void onStartInstanceState(Bundle savedInstanceState) {

        super.onRestoreInstanceState(savedInstanceState);

        // Restoring the UI state from the savedInstanceState.

        String rotatehistory = savedInstanceState.getString("SavedHistoryVal");
        String outputConverted = savedInstanceState.getString("FinalValue");
        history_Val.setText(rotatehistory);
        output_Val.setText(outputConverted);
        history_Val.setMovementMethod(new ScrollingMovementMethod());
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putString("SavedHistoryVal", history_Val.getText().toString());
        outState.putString("FinalValue", output_Val.getText().toString());
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {

        super.onRestoreInstanceState(savedInstanceState);

        // Restoring the UI state from the savedInstanceState.

        String rotatehistory = savedInstanceState.getString("SavedHistoryVal");
        String outputConverted = savedInstanceState.getString("FinalValue");
        history_Val.setText(rotatehistory);
        history_Val.setMovementMethod(new ScrollingMovementMethod());
        output_Val.setText(outputConverted);
    }


    // Functions for maintaining the life cycle.
    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }


    //Functions for refreshing value of TextView on changing the value of EditText
    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        TextView output=(TextView) findViewById(R.id.temp_Output);
        output.setText(null);
        return super.onKeyUp(keyCode, event);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        TextView output=(TextView) findViewById(R.id.temp_Input1);
        output.setText(null);
        return super.onKeyDown(keyCode, event);
    }



    //Functions for validating the null input value.
    public void alert(String a, String b) {
        AlertDialog.Builder alBuilder = new AlertDialog.Builder(this);
        alBuilder.setTitle(a);
        alBuilder.setMessage(b);
        alBuilder.setPositiveButton("OK", null);
        alBuilder.setCancelable(true);
        alBuilder.create().show();

    }

    public void onClickCel(View v) {
        EditText CelsiusToFahrenheit = findViewById(R.id.temp_Input1);
        TextView FahrenheitToCelsius = findViewById(R.id.temp_Output);
        CelsiusToFahrenheit.setText(null);
        FahrenheitToCelsius.setText(null);

    }

    public void onClickFeh(View v) {
        EditText CelsiusToFahrenheit = findViewById(R.id.temp_Input1);
        TextView FahrenheitToCelsius = findViewById(R.id.temp_Output);
        CelsiusToFahrenheit.setText(null);
        FahrenheitToCelsius.setText(null);
    }


    //Function for Temperature Conversion
    public void tempConvert(View V) {

        Log.d(TAG, "tempConvert: Start");
        RadioButton CelsiusToFahrenheit = findViewById(R.id.radioButtonCelsiusToFahrenheit);
        RadioButton FahrenheitToCelsius = findViewById(R.id.radioButtonFahrenheitToCelsius);
        EditText input = findViewById(R.id.temp_Input1);

        String value = input.getText().toString();
        if (!CelsiusToFahrenheit.isChecked() && !FahrenheitToCelsius.isChecked()) {
            alert("Alert", "Please Select Radio button");

        } else if (value.equals("") || value == "" || value == null) {

            alert("Alert", "Enter Any Value");
        } else {

            double previous = Double.parseDouble(input.getText().toString());
            double current = 0.0;
            String finalTemp = "";
            if (CelsiusToFahrenheit.isChecked()) {
                current = previous * (9.0 / 5.0) + 32.0;
                finalTemp = "\n" + " C to F:  " +  previous + "  ->  " + String.format("%.1f", current);
            }

            if (FahrenheitToCelsius.isChecked()) {
                current = (previous - 32.0) * (5.0 / 9.0);
                finalTemp = "\n" + " F to C:  " + previous + "  ->  " + String.format("%.1f", current);
            }

            output_Val.setText(String.format("%.1f", current));

            String currentHistory = history_Val.getText().toString();
            currentHistory =   finalTemp + "\n" + currentHistory;
            history_Val.setText(currentHistory);
            Log.d(TAG, "temperature_Convert: Done");

            if (start = true) {
                history_Val.setMovementMethod(new ScrollingMovementMethod());
            }

        }
    }
}
