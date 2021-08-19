package com.example.gpscafeteria;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class CartActivity extends AppCompatActivity {

    TextView cart,textpay;
    AlertDialog.Builder alert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        cart= findViewById(R.id.textViewcart);
        textpay=  findViewById(R.id.textPay);
        alert = new AlertDialog.Builder(this);

        if(MainActivity.total!=0){
            String cartview=MainActivity.orderString;

            cartview += "\n\nTotal Amount:"+MainActivity.total+"Tk";

            cart.setText(cartview);

        }else{
            cart.setText("Currently NO Items On the Cart\nPlease Select Some Item First");
        }

        textpay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.content_frame, new PaymentFragment()).commit();*/

                startActivity(new Intent(CartActivity.this,  PaymentActivity.class));
            }
        });
    }
}
