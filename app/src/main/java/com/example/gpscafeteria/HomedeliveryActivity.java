package com.example.gpscafeteria;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class HomedeliveryActivity extends AppCompatActivity {

    EditText address;
    EditText phone;
    Button confirmOrder;
    AlertDialog.Builder alert;
    String manager = "8296871629";
    String order;


    private static final int PERMISSION_SEND_SMS = 123;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homedelivery);

        address = findViewById(R.id.editTextaddress);
        phone = findViewById(R.id.editTextphone);
        confirmOrder = findViewById(R.id.buttonconfirm);

        confirmOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                order=MainActivity.orderString;
                int total = MainActivity.total;

                alert = new AlertDialog.Builder(HomedeliveryActivity.this);

                if(address.getText().toString().trim().isEmpty() || phone.getText().toString().trim().isEmpty()){
                    String msg = "Please put Address & Phone Number First!!!";
                    Toast.makeText(HomedeliveryActivity.this,msg, Toast.LENGTH_LONG).show();
                }else{

                    total+=100;
                    order += "\nDelivery Charge: 100tk";
                    order += "\n Total Amount: "+total;
                    order += "\n Delivery Add: "+address.getText().toString();
                    order += "\n Phone No: "+phone.getText().toString();
                    order += "\n Do you want to confirm Order?";
                    alert.setMessage(order);

                    //alert.setIcon(R.drawable.Pizza_icon);
                    alert.setTitle("Order Confirmation");

                    alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {


                            if (ContextCompat.checkSelfPermission(HomedeliveryActivity.this, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
                                // request permission (see result in onRequestPermissionsResult() method)
                                ActivityCompat.requestPermissions(HomedeliveryActivity.this,
                                        new String[]{Manifest.permission.SEND_SMS},
                                        PERMISSION_SEND_SMS);
                            } else {
                                // permission already granted run sms send
                                sendSms(phone, order);
                            }
                        }
                    });

                    alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.cancel();
                        }
                    });

                    alert.show();

                }
            }
        });
    }

    private void sendSms(EditText phone, String order) {

        SmsManager sms = SmsManager.getDefault();
        ArrayList<String> parts = sms.divideMessage(order);
        //sms.sendTextMessage(manager,null,parts,null,null);

        sms.sendMultipartTextMessage(manager,null,parts,null,null);

        sms.sendTextMessage(phone.getText().toString(),null,"Your order have been placed,you will receive the delivery within 1 hour.Thank You!",null,null);

        Toast.makeText(HomedeliveryActivity.this, "Your order have been placed,you will receive the delivery within 1 hour.Thank You!!!", Toast.LENGTH_LONG).show();
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // Do something after 5s = 5000ms
                MainActivity.orderString = "";
                MainActivity.total = 0;
                                    /*FragmentManager fragmentManager = getFragmentManager();
                                    fragmentManager.beginTransaction().replace(R.id.content_frame, new H).commit();*/

                startActivity(new Intent(HomedeliveryActivity.this, MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
            }
        }, 2000);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_SEND_SMS: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    sendSms(phone,order);
                }
                else {
                    Toast.makeText(getApplicationContext(),
                            "SMS failed, please try again.", Toast.LENGTH_LONG).show();
                    return;
                }
            }
        }

    }


}
