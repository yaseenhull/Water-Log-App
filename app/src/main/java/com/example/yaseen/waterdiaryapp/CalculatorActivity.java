package com.example.yaseen.waterdiaryapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class CalculatorActivity extends AppCompatActivity {
    Button btnHome, btnSave;
    DatabaseHelper myDB;
    EditText  date1, shower1, toilet1, hygiene1, laundry1, dishes1, drinking1, cooking1, cleaning1,other1;
    TextView total1;
    public int num1,num2,num3,num4,num5,num6,num7,num8,num9,total, newTotal, count= 0;
    public String mainTotal;
    public static  int finalTotal = 0;
    SharedPreferences sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);


        shower1 = (EditText) findViewById(R.id.shower1);
        toilet1 = (EditText) findViewById(R.id.toilet1);
        hygiene1 = (EditText) findViewById(R.id.hygiene1);
        laundry1 = (EditText) findViewById(R.id.laundry1);
        dishes1 = (EditText) findViewById(R.id.dishes1);
        drinking1 = (EditText) findViewById(R.id.drinking1);
        cooking1 = (EditText) findViewById(R.id.cooking1);
        cleaning1 = (EditText) findViewById(R.id.cleaning1);
        other1 = (EditText) findViewById(R.id.other1);
        total1 = (TextView) findViewById(R.id.total1);
        date1 = (EditText) findViewById(R.id.date1);


        btnHome = (Button)findViewById(R.id.btnHome);
        btnSave = (Button) findViewById(R.id.btnSave);
        myDB = new DatabaseHelper(this);
        sharedPref = getSharedPreferences("myPrefs", Context.MODE_PRIVATE);


        shower1.addTextChangedListener(tw);
        toilet1.addTextChangedListener(tw2);
        hygiene1.addTextChangedListener(tw3);
        laundry1.addTextChangedListener(tw4);
        dishes1.addTextChangedListener(tw5);
        drinking1.addTextChangedListener(tw6);
        cooking1.addTextChangedListener(tw7);
        cleaning1.addTextChangedListener(tw8);
        other1.addTextChangedListener(tw9);

        /** On btnSave find string value and check length (check if text input is empty)
         * remove TextWatcher from each listener and reset category fields to blank
         * save and update totals and counter to update main screen values
         */

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String firstEntry = shower1.getText().toString();
                String secondEntry = toilet1.getText().toString();
                String thirdEntry = hygiene1.getText().toString();
                String forthEntry = laundry1.getText().toString();
                String fifthEntry = dishes1.getText().toString();
                String sixthEntry = drinking1.getText().toString();
                String seventhEntry = cooking1.getText().toString();
                String eighthEntry = cleaning1.getText().toString();
                String ninthEntry = other1.getText().toString();
                String tenthEntry = total1.getText().toString();
                String dateEntry = date1.getText().toString();



                if((shower1.length()!= 0) || (toilet1.length() != 0) || (hygiene1.length() != 0) || (laundry1.length() !=0) || (dishes1.length() != 0) || (drinking1.length() != 0) || (cooking1.length() != 0) || (cleaning1.length() != 0) || (other1.length() !=0) || (total1.length() !=0) || (date1.length() !=0)   ){
                    AddData(firstEntry,secondEntry,thirdEntry,forthEntry,fifthEntry,sixthEntry,seventhEntry,eighthEntry,ninthEntry,tenthEntry, dateEntry);
                    shower1.removeTextChangedListener(tw);
                    toilet1.removeTextChangedListener(tw2);
                    hygiene1.removeTextChangedListener(tw3);
                    laundry1.removeTextChangedListener(tw4);
                    dishes1.removeTextChangedListener(tw5);
                    drinking1.removeTextChangedListener(tw6);
                    cooking1.removeTextChangedListener(tw7);
                    cleaning1.removeTextChangedListener(tw8);
                    other1.removeTextChangedListener(tw9);

                    total = num1+num2+num3+num4+num5+num6+num7+num8+num9;
                    SharedPreferences.Editor editor = sharedPref.edit();

                    count = sharedPref.getInt("ci",0);

                    if(count == 0)
                    {
                        count = count +1;
                        editor.putInt("fi", total );
                        editor.putInt("ci",count);
                        editor.commit();
                    }
                    else
                    {
                        count = count + 1;
                        newTotal = sharedPref.getInt("fi",0)+total;
                        editor.putInt("fi",newTotal);
                        editor.putInt("ci",count);
                        editor.commit();

                    }




                    shower1.setText("");
                    toilet1.setText("");
                    hygiene1.setText("");
                    laundry1.setText("");
                    dishes1.setText("");
                    drinking1.setText("");
                    cooking1.setText("");
                    cleaning1.setText("");
                    other1.setText("");
                    date1.setText("");
                    total1.setText("Total");



                }else{
                    Toast.makeText(CalculatorActivity.this, "One or more text fields are empty", Toast.LENGTH_LONG).show();
                }

            }
        });


        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CalculatorActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }

    /** TextWatcher listens for change in textView
     * fields on activity_calculator and
     * updates total field each time values
     * are added.
     */

    private final TextWatcher tw = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {

            num1 = Integer.parseInt(shower1.getText().toString());
            total1.setText(num1+"");
            //shower1.removeTextChangedListener(this);


        }
    };

    private final TextWatcher tw2 = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            num2 = Integer.parseInt(toilet1.getText().toString());
            total1.setText(num1+num2+"");

        }
    };

    private final TextWatcher tw3 = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            num3 = Integer.parseInt(hygiene1.getText().toString());
            total1.setText(num1+num2+num3+"");


        }
    };

    private final TextWatcher tw4 = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            num4 = Integer.parseInt(laundry1.getText().toString());
            total1.setText(num1+num2+num3+num4+"");


        }
    };

    private final TextWatcher tw5 = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            num5 = Integer.parseInt(dishes1.getText().toString());
            total1.setText(num1+num2+num3+num4+num5+"");


        }
    };

    private final TextWatcher tw6 = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            num6 = Integer.parseInt(drinking1.getText().toString());
            total1.setText(num1+num2+num3+num4+num5+num6+"");


        }
    };

    private final TextWatcher tw7 = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            num7 = Integer.parseInt(cooking1.getText().toString());
            total1.setText(num1+num2+num3+num4+num5+num6+num7+"");


        }
    };

    private final TextWatcher tw8 = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            num8 = Integer.parseInt(cleaning1.getText().toString());
            total1.setText(num1+num2+num3+num4+num5+num6+num7+num8+"");


        }
    };

    private final TextWatcher tw9 = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            num9 = Integer.parseInt(other1.getText().toString());
            total1.setText(num1+num2+num3+num4+num5+num6+num7+num8+num9+"");




        }
    };

    /**Add data into the database created in DatabaseHelper class
     * each string represents category of water usage (i.e shower, etc)
     */

    public void AddData(String i1, String i2, String i3, String i4, String i5, String i6, String i7, String i8, String i9, String i10, String i11)
    {
        boolean insertData = myDB.addData(i1,i2,i3,i4,i5,i6,i7,i8,i9,i10, i11);

        if(insertData==true){
            Toast.makeText(this, "Data Successfully Inserted!", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(this, "Something went wrong :(.", Toast.LENGTH_LONG).show();
        }

    }
}
