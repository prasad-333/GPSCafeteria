package com.example.gpscafeteria;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gpscafeteria.model.User;
import com.example.gpscafeteria.prevalent.Prevalent;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rey.material.widget.CheckBox;

import io.paperdb.Paper;

public class LoginActivity extends AppCompatActivity {

    EditText etPhone, etPwd;
    CheckBox remember;
    Button btnLogin;
    private static String DBNAME = "Users";

    DatabaseReference databaseReference;
    ProgressDialog pd;

    TextView tvRegister;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        FirebaseApp.initializeApp(this);

        databaseReference = FirebaseDatabase.getInstance().getReference().child(DBNAME);
        pd = new ProgressDialog(this);

        etPhone = findViewById(R.id.etLoginPhoneNumber);
        etPwd = findViewById(R.id.etLoginPassword);
        remember = findViewById(R.id.chk_remember_me);

        Paper.init(this);

        btnLogin  = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                loginUser();
            }
        });


        tvRegister = findViewById(R.id.tvNotRegistered);

        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });
    }

    private void loginUser() {

        String phone = etPhone.getText().toString().trim();
        String pwd = etPwd.getText().toString();

        if (TextUtils.isEmpty(phone)){

            Toast.makeText(this, "Please enter phone number", Toast.LENGTH_SHORT).show();

        }else if (TextUtils.isEmpty(pwd)){

            Toast.makeText(this, "Please enter Password", Toast.LENGTH_SHORT).show();
        }else {

            pd.setTitle("Authenticating Account");
            pd.setMessage("Please wait, while we are checking the credentials");
            pd.setCanceledOnTouchOutside(false);
            pd.show();

            allowAccessToAccount(phone,pwd);
        }
    }

    private void allowAccessToAccount(final String phone, final String pwd) {

        if (remember.isChecked()){

            Paper.book().write(Prevalent.userPhoneKey,phone);
            Paper.book().write(Prevalent.userPasswordKey, pwd);

        }
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                pd.dismiss();
                if (dataSnapshot.child(phone).exists()){

                    User userData  = dataSnapshot.child(phone).getValue(User.class);

                    if (userData.getPhone().equals(phone)){

                        if (userData.getPassword().equals(pwd)){


                            Toast.makeText(LoginActivity.this, "Logged in Successfully", Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            Prevalent.currentOnlineUser = userData;
                            startActivity(intent);

                        }
                    }else {
                        Toast.makeText(LoginActivity.this, "Incorrect Credentials", Toast.LENGTH_SHORT).show();
                    }
                }else {

                    Toast.makeText(LoginActivity.this, "Account with this "+phone + "Already exist, Please create a new account", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                pd.dismiss();
                Toast.makeText(LoginActivity.this, "Error : "+databaseError.getMessage().toString(), Toast.LENGTH_SHORT).show();

            }
        });
    }
}
