package com.example.gpscafeteria;

import android.app.FragmentManager;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.Toast;

public class CoffeeActivity extends AppCompatActivity {

    int prevtotal;


    String[] amount = {"1", "2", "3", "4", "5", "6", "7", "8", "9"};
    Spinner spinnerCf1, spinnerCf2, spinnerCf3, spinnerCf4, spinnerCf5, spinnerCf6;
    CheckBox cboxcf1, cboxcf2, cboxcf3, cboxcf4, cboxcf5, cboxcf6;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coffee);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, amount);

        spinnerCf1 = findViewById(R.id.spinner1);
        spinnerCf1.setAdapter(adapter);
        spinnerCf2 = findViewById(R.id.spinner2);
        spinnerCf2.setAdapter(adapter);
        spinnerCf3 = findViewById(R.id.spinner3);
        spinnerCf3.setAdapter(adapter);
        spinnerCf4 = findViewById(R.id.spinner4);
        spinnerCf4.setAdapter(adapter);
        spinnerCf5 = findViewById(R.id.spinner5);
        spinnerCf5.setAdapter(adapter);
        spinnerCf6 = findViewById(R.id.spinner6);
        spinnerCf6.setAdapter(adapter);


        cboxcf1 = findViewById(R.id.checkCoffee1);
        cboxcf2 = findViewById(R.id.checkCoffee2);
        cboxcf3 = findViewById(R.id.checkCoffee3);
        cboxcf4 = findViewById(R.id.checkCoffee4);
        cboxcf5 = findViewById(R.id.checkCoffee5);
        cboxcf6 = findViewById(R.id.checkCoffee6);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
        public boolean onOptionsItemSelected (MenuItem item){

           // FragmentManager fragmentManager = getFragmentManager();

            int id = item.getItemId();

            if (id == R.id.action_confirm) {
                prevtotal = MainActivity.total;
                if (cboxcf1.isChecked()) {
                    int n = (1 + spinnerCf1.getSelectedItemPosition());
                    int total = (n * 100);
                    MainActivity.orderString += "\n       " + n + " Caffe Noisette " + total + " Tk";
                    MainActivity.total += total;

                }
                if (cboxcf2.isChecked()) {
                    int n = (1 + spinnerCf2.getSelectedItemPosition());
                    int total = (n * 120);
                    MainActivity.orderString += "\n       " + n + " Caffe Americano " + total + " Tk";
                    MainActivity.total += total;

                }
                if (cboxcf3.isChecked()) {
                    int n = (1 + spinnerCf3.getSelectedItemPosition());
                    int total = (n * 130);
                    MainActivity.orderString += "\n       " + n + " Caffe Macchiato " + total + " Tk";
                    MainActivity.total += total;

                }
                if (cboxcf4.isChecked()) {
                    int n = (1 + spinnerCf4.getSelectedItemPosition());
                    int total = (n * 100);
                    MainActivity.orderString += "\n       " + n + " Iced Mocha " + total + " Tk";
                    MainActivity.total += total;

                }

                if (cboxcf5.isChecked()) {
                    int n = (1 + spinnerCf5.getSelectedItemPosition());
                    int total = (n * 80);
                    MainActivity.orderString += "\n       " + n + " Cappuccino " + total + " Tk";
                    MainActivity.total += total;

                }

                if (cboxcf6.isChecked()) {
                    int n = (1 + spinnerCf6.getSelectedItemPosition());
                    int total = (n * 110);
                    MainActivity.orderString += "\n       " + n + " Caffe Espresso " + total + " Tk";
                    MainActivity.total += total;

                }
                if (MainActivity.total != prevtotal) {
                    Toast.makeText(CoffeeActivity.this, "Items Added to Cart", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(CoffeeActivity.this, "Sorry You haven't Selected any Item", Toast.LENGTH_LONG).show();
                }
                finish();
            }
            return super.onOptionsItemSelected(item);
        }



}
