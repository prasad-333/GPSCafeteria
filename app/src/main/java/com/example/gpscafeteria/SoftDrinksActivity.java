package com.example.gpscafeteria;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.Toast;

public class SoftDrinksActivity extends AppCompatActivity {

    int prevtotal;

    String[] amount = {"1","2","3","4","5","6","7","8","9"};
    Spinner spinnerCola,spinnerPepsi,spinnerDew,spinnerFanta, spinnerLast;
    CheckBox cboxCola,cboxPepsi,cboxDew,cboxFanta,cboxLast;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soft_drinks);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,amount);


        spinnerCola = findViewById(R.id.spinnerd1);
        spinnerCola.setAdapter(adapter);
        spinnerPepsi = findViewById(R.id.spinnerd2);
        spinnerPepsi.setAdapter(adapter);
        spinnerDew = findViewById(R.id.spinnerd3);
        spinnerDew.setAdapter(adapter);
        spinnerFanta = findViewById(R.id.spinnerd4);
        spinnerFanta.setAdapter(adapter);
        spinnerLast = findViewById(R.id.spinnerd5);
        spinnerLast.setAdapter(adapter);

        cboxCola = findViewById(R.id.checkd1);
        cboxPepsi = findViewById(R.id.checkd2);
        cboxDew = findViewById(R.id.checkd3);
        cboxFanta = findViewById(R.id.checkd4);
        cboxLast = findViewById(R.id.checkd5);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


       // FragmentManager fragmentManager = getFragmentManager();

        int id = item.getItemId();

        if(id == R.id.action_confirm) {
            prevtotal = MainActivity.total;

            if (cboxCola.isChecked()) {
                int n = (1 + spinnerCola.getSelectedItemPosition());
                int total = (n * 50);
                MainActivity.orderString += "\n       " + n + " 750ml H2GO " + total + " Tk";
                MainActivity.total += total;

            }
            if (cboxPepsi.isChecked()) {
                int n = (1 + spinnerPepsi.getSelectedItemPosition());
                int total = (n * 60);
                MainActivity.orderString += "\n       " + n + " 7UP 1000ml " + total + " Tk";
                MainActivity.total += total;

            }
            if (cboxDew.isChecked()) {
                int n = (1 + spinnerDew.getSelectedItemPosition());
                int total = (n * 70);
                MainActivity.orderString += "\n       " + n + " Crisp Apple 750ml " + total + " Tk";
                MainActivity.total += total;

            }
            if (cboxFanta.isChecked()) {
                int n = (1 + spinnerFanta.getSelectedItemPosition());
                int total= (n * 80);
                MainActivity.orderString += "\n       " + n + " Juicy Orange 750ml " + total + " Tk";
                MainActivity.total += total;

            }
            if (cboxLast.isChecked()) {
                int n = (1 + spinnerLast.getSelectedItemPosition());
                int total= (n * 40);
                MainActivity.orderString += "\n       " + n + " Pepsi MAX 500ml " + total + " Tk";
                MainActivity.total += total;

            }

            if(MainActivity.total!=0){
                Toast.makeText(SoftDrinksActivity.this,"Items Added to Cart", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(SoftDrinksActivity.this,"Sorry You haven't Selected any Item",Toast.LENGTH_LONG).show();
            }
            finish();

        }
        return super.onOptionsItemSelected(item);
    }
}
