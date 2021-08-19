package com.example.gpscafeteria;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class AboutActivity extends AppCompatActivity {

    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        tv = (TextView) findViewById(R.id.textBackHome);

        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();
                Toast.makeText(getApplicationContext(), "Thanks...", Toast.LENGTH_LONG).show();
            }
        });
    }
}
