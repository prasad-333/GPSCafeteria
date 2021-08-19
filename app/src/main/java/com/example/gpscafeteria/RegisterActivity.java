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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity {

    //Decalred the widgets

    EditText etPhone, etPwd, etName;
    TextView tvLoginLink;
    Button btnRegister;
    ProgressDialog pd;

    DatabaseReference rootRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //initialise the widgets
        etName = findViewById(R.id.etRegisterNmae);
        etPhone = findViewById(R.id.etRegisterPhoneNumber);
        etPwd = findViewById(R.id.etRegisterPassword);

        tvLoginLink = findViewById(R.id.tvAlreadyRegistered);

        rootRef = FirebaseDatabase.getInstance().getReference().child("Users");
        pd = new ProgressDialog(this);

        btnRegister = findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = etName.getText().toString().trim();
                String phone = etPhone.getText().toString().trim();
                String pwd = etPwd.getText().toString().trim();

                if (TextUtils.isEmpty(name)){
                    Toast.makeText(RegisterActivity.this, "Please Enter Name", Toast.LENGTH_SHORT).show();

                }else if (TextUtils.isEmpty(phone)){
                    Toast.makeText(RegisterActivity.this, "Please Phone Number", Toast.LENGTH_SHORT).show();

                }else if (TextUtils.isEmpty(pwd)){
                    Toast.makeText(RegisterActivity.this, "Please Enter Password", Toast.LENGTH_SHORT).show();

                }else{

                    pd.setTitle("Creating Account");
                    pd.setMessage("Please wait, while we create your account...");
                    pd.setCanceledOnTouchOutside(false);
                    pd.show();

                    createAccount(name,phone,pwd);
                }

                etPhone.setText(null);
                etName.setText(null);
                etPwd.setText(null);
            }
        });

    }

    private void createAccount(final String name, final String phone, final String pwd) {

        rootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (!dataSnapshot.child(phone).exists()){

                    HashMap<String, Object> userDataMap = new HashMap<>();
                    userDataMap.put("name",name);
                    userDataMap.put("phone",phone);
                    userDataMap.put("password",pwd);

                    rootRef.child(phone).updateChildren(userDataMap)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {

                                    if (task.isSuccessful()){

                                        pd.dismiss();

                                        Toast.makeText(RegisterActivity.this, "User created", Toast.LENGTH_SHORT).show();

                                        startActivity(new Intent(RegisterActivity.this, MainActivity.class));
                                    }else {
                                        Toast.makeText(RegisterActivity.this, "Error, Couldnot create user", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }else {

                    Toast.makeText(RegisterActivity.this, "Phone "+phone+ " number already exists", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(RegisterActivity.this, MainActivity.class));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                Toast.makeText(RegisterActivity.this, "Eroor "+databaseError.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }
}
