package com.example.vishali.androidproject;


import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;


public class activity_Admin_Home extends AppCompatActivity implements View.OnClickListener {
    private TextView Collector;
    private TextView Schedule;
    private TextView Payment;
    private Button Logout;

    private android.app.ProgressDialog ProgressDialog;
    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__admin__home);

        ProgressDialog = new ProgressDialog(this);

        firebaseAuth = FirebaseAuth.getInstance();

        if(firebaseAuth.getCurrentUser() != null){
            //profile activity here
            finish();
            startActivity(new Intent(getApplicationContext(),activity_Register.class));

        }

        Logout = (Button)findViewById(R.id.Logout);

        Collector = (TextView)findViewById(R.id.Collector);
        Schedule = (TextView)findViewById(R.id.Schedule);
        Payment = (TextView)findViewById(R.id.Payment);


        Logout.setOnClickListener(this);
        Collector.setOnClickListener(this);
        Schedule.setOnClickListener(this);
        Payment.setOnClickListener(this);

    }


    @Override
    public void onClick(View view) {
        if(view == Logout){
            firebaseAuth.signOut();
            finish();
            startActivity(new Intent(this,activity_welcome.class));
        }

        if(view == Collector){
            startActivity(new Intent(this,activity_Register.class));
        }

        if(view == Schedule){
            startActivity(new Intent(this,jMainActivity2.class));
        }

        if(view == Payment){
            startActivity(new Intent(this,Activity3admin.class));
        }
    }
}
