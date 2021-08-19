package com.example.gpscafeteria;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MenuActivity extends AppCompatActivity {

    ListView lv;
    //String [] items = {"Coffee","Burger","Pizza","Soft Drinks"};

    // Array of strings for ListView Title

    int[] listviewImage = new int[]{
            R.drawable.coffee, R.drawable.burger, R.drawable.pizza, R.drawable.drinks
    };//, R.drawable.noodles, R.drawable.icecream, R.drawable.chocolate,


    String[] listviewTitle = new String[]{
            "COFFEE", "BURGER", "PIZZA", "SOFT DRINKS"
    };//,"NOODLES", "ICECREAM", "CHOCOLATE",


    String[] listviewShortDescription = new String[]{
            "Hot and cold coffee with different flavour", "Chicken and Beef burger", "Vegetable/Chicken pizza", "Soft drinks and juices",
            "More flavours noodles\nCOMMING SOON*", "Various flovour of Icecream\nCOMMING SOON*", "Chocolate with best quality\nCOMMING SOON*",
    };



    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        List<HashMap<String, String>> aList = new ArrayList<HashMap<String, String>>();

        for (int i = 0; i < 4; i++) {
            HashMap<String, String> hm = new HashMap<String, String>();
            hm.put("listview_title", listviewTitle[i]);
            hm.put("listview_discription", listviewShortDescription[i]);
            hm.put("listview_image", Integer.toString(listviewImage[i]));
            aList.add(hm);
        }

        String[] from = {"listview_image", "listview_title", "listview_discription"};
        int[] to = {R.id.listview_image, R.id.listview_item_title, R.id.listview_item_short_description};

        SimpleAdapter simpleAdapter = new SimpleAdapter(this, aList, R.layout.listview_activity, from, to);
        //ListView androidListView = (ListView) findViewById(R.id.list_view);
        lv = findViewById(R.id.list_view);
        lv.setAdapter(simpleAdapter);


        //lv = (ListView) findViewById(R.id.list_view);
        //ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,items);
        //lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           // FragmentManager fragmentManager = getFragmentManager();

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                switch (i) {
                    case 0:
                       // fragmentManager.beginTransaction().replace(R.id.content_frame,new CoffeeFragment()).commit();
                        startActivity(new Intent(getApplicationContext(), CoffeeActivity.class));
                        break;
                    case 1:
                        //fragmentManager.beginTransaction().replace(R.id.content_frame, new BurgerFragment()).commit();
                        startActivity(new Intent(getApplicationContext(), BurgerActivity.class));
                        break;
                    case 2:
                       // fragmentManager.beginTransaction().replace(R.id.content_frame, new PizzaFragment()).commit();
                        startActivity(new Intent(getApplicationContext(), PizzaActivity.class));
                        break;
                    case 3:
                       // fragmentManager.beginTransaction().replace(R.id.content_frame, new SoftdrinksFragment()).commit();
                        startActivity(new Intent(getApplicationContext(), SoftDrinksActivity.class));
                        break;
                }
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
