package com.example.gpscafeteria;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.Toast;

public class BurgerActivity extends AppCompatActivity {

    int prevtotal;

    String[] amount = {"1","2","3","4","5","6","7","8","9"};
    Spinner spinnerQS,spinnerCB,spinnerGC,spinnerWHO,spinnerEND;
    CheckBox cboxQS,cboxCB,cboxGC,cboxWHO,cboxEND;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_burger);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,amount);

        spinnerQS = findViewById(R.id.spinnerb1);
        spinnerQS.setAdapter(adapter);
        spinnerCB = findViewById(R.id.spinnerb2);
        spinnerCB.setAdapter(adapter);
        spinnerGC = findViewById(R.id.spinnerb3);
        spinnerGC.setAdapter(adapter);
        spinnerWHO = findViewById(R.id.spinnerb4);
        spinnerWHO.setAdapter(adapter);
        spinnerEND = findViewById(R.id.spinnerb5);
        spinnerEND.setAdapter(adapter);

        cboxQS = findViewById(R.id.checkb1);
        cboxCB = findViewById(R.id.checkb2);
        cboxGC = findViewById(R.id.checkb3);
        cboxWHO = findViewById(R.id.checkb4);
        cboxEND = findViewById(R.id.checkb5);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        //  FragmentManager fragmentManager = getFragmentManager();

        int id = item.getItemId();

        if(id == R.id.action_confirm){

            prevtotal = MainActivity.total;
            if (cboxQS.isChecked()) {

                int n = (1 + spinnerQS.getSelectedItemPosition());
                int total = (n * 150);
                MainActivity.orderString += "\n       " + n + " Kiwiburger (McDonald) " + total + " Tk";
                MainActivity.total += total;
            }
            if (cboxCB.isChecked()) {

                int n = (1 + spinnerCB.getSelectedItemPosition());
                int total = (n * 130);
                MainActivity.orderString += "\n       " + n + " McItaly Adagio (McDonald) " + total + " Tk";
                MainActivity.total += total;


            }
            if (cboxGC.isChecked()) {

                int n = (1 + spinnerGC.getSelectedItemPosition());
                int total = (n * 170);
                MainActivity.orderString += "\n       " + n + " Mcribster (McDonald) " + total + " Tk";
                MainActivity.total += total;


            }
            if (cboxWHO.isChecked()) {

                int n = (1 + spinnerWHO.getSelectedItemPosition());
                int total = (n * 180);
                MainActivity.orderString += "\n       " + n + " Meat Monster King " + total + " Tk";
                MainActivity.total += total;


            }
            if (cboxEND.isChecked()) {

                int n = (1 + spinnerEND.getSelectedItemPosition());
                int total = (n * 200);
                MainActivity.orderString += "\n       " + n + " Sprout Whopper King " + total + " Tk";
                MainActivity.total += total;


            }
            if(MainActivity.total!=prevtotal){
                Toast.makeText(this,"Items Added to Cart", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this,"Sorry You haven't Selected any Item",Toast.LENGTH_LONG).show();
            }
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
