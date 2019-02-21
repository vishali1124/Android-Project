package com.example.vishali.androidproject;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class activity_home extends AppCompatActivity implements View.OnClickListener {
    private TextView Profile;
    private TextView Schedule;
    private TextView Alarm;
    private TextView Payment;
    private TextView AboutUs;
    private TextView Adds;
    private Button Logout;

    private android.app.ProgressDialog ProgressDialog;
    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ProgressDialog = new ProgressDialog(this);

        firebaseAuth = FirebaseAuth.getInstance();

        if(firebaseAuth.getCurrentUser() != null){
            //profile activity here
            finish();
            startActivity(new Intent(getApplicationContext(),activity_Register.class));

        }

        Profile = (TextView)findViewById(R.id.Profile);
        Schedule = (TextView)findViewById(R.id.Schedule);
        Alarm = (TextView)findViewById(R.id.Alarm);
        Payment = (TextView)findViewById(R.id.Payment);
        AboutUs = (TextView)findViewById(R.id.AboutUs);
        Adds = (TextView)findViewById(R.id.Adds);
        Logout = (Button)findViewById(R.id.Logout);

        Profile.setOnClickListener(this);
        Schedule.setOnClickListener(this);
        Alarm.setOnClickListener(this);
        Payment.setOnClickListener(this);
        AboutUs.setOnClickListener(this);
        Adds.setOnClickListener(this);
        Logout.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view == Profile){
            startActivity(new Intent(this,activity_Profile.class));
        }

        if(view == Schedule){
            startActivity(new Intent(this,jCollectorSchedule.class));
        }

        if(view == Alarm){
            startActivity(new Intent(this,activity_Booking_customer.class));
        }

        if(view == Payment){
            startActivity(new Intent(this,activity_Profile.class));
        }

        if(view == AboutUs){
            startActivity(new Intent(this,activity_about_us.class));
        }

        if(view == Adds){
            startActivity(new Intent(this,activity_adds.class));
        }

        if(view == Logout){
            firebaseAuth.signOut();
            finish();
            startActivity(new Intent(this,activity_welcome.class));
        }

    }
}
