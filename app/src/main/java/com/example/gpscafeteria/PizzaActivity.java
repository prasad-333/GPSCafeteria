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

public class PizzaActivity extends AppCompatActivity {

    int prevtotal;

    String[] amount = {"1","2","3","4","5","6","7","8","9"};
    Spinner spinner1,spinner2,spinner3,spinner4,spinner5;
    CheckBox cbox1,cbox2,cbox3,cbox4,cbox5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pizza);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,amount);

        spinner1 = findViewById(R.id.spinnerp1);
        spinner1.setAdapter(adapter);
        spinner2 = findViewById(R.id.spinnerp2);
        spinner2.setAdapter(adapter);
        spinner3 = findViewById(R.id.spinnerp3);
        spinner3.setAdapter(adapter);
        spinner4 = findViewById(R.id.spinnerp4);
        spinner4.setAdapter(adapter);
        spinner5 = findViewById(R.id.spinnerp5);
        spinner5.setAdapter(adapter);


        cbox1 = findViewById(R.id.checkp1);
        cbox2 = findViewById(R.id.checkp2);
        cbox3 = findViewById(R.id.checkp3);
        cbox4 = findViewById(R.id.checkp4);
        cbox5 = findViewById(R.id.checkp5);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // FragmentManager fragmentManager = getFragmentManager();

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_confirm) {

            prevtotal = MainActivity.total;




            if (cbox1.isChecked()) {

                int n = (1 + spinner1.getSelectedItemPosition());
                int total = (n * 150);
                MainActivity.orderString += "\n       " + n + " Americano Pizza " + total + " Tk";
                MainActivity.total += total;



            }
            if (cbox2.isChecked()) {

                int n = (1 + spinner2.getSelectedItemPosition());
                int total = (n * 130);
                MainActivity.orderString += "\n       " + n + " Bacon and Mushroom " + total + " Tk";
                MainActivity.total += total;



            }
            if (cbox3.isChecked()) {

                int n = (1 + spinner3.getSelectedItemPosition());
                int total = (n * 170);
                MainActivity.orderString += "\n       " + n + " Garlic Prawn " + total + " Tk";
                MainActivity.total += total;


            }
            if (cbox4.isChecked()) {
                int n = (1 + spinner4.getSelectedItemPosition());
                int total = (n * 180);
                MainActivity.orderString += "\n       " + n + " Hot Chilli Beef " + total + " Tk";
                MainActivity.total += total;



            }
            if (cbox5.isChecked()) {

                int n = (1 + spinner5.getSelectedItemPosition());
                int total = (n * 200);
                MainActivity.orderString += "\n       " + n + " Peri-Peri Chicken " + total + " Tk";
                MainActivity.total += total;



            }


            if(MainActivity.total!=prevtotal){
                Toast.makeText(PizzaActivity.this,"Items Added to Cart",Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(PizzaActivity.this,"Sorry You haven't Selected any Item",Toast.LENGTH_LONG).show();
            }
            finish();


        }
        return super.onOptionsItemSelected(item);
    }
}