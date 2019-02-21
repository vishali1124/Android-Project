package com.example.vishali.androidproject;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class activity_collector extends AppCompatActivity implements View.OnClickListener{

    private TextView Payment;
    private TextView Schedule;
    private TextView Calculator;
    private TextView Booking;
    private TextView Price;
    private Button Logout;


    private android.app.ProgressDialog ProgressDialog;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collector);

        ProgressDialog = new ProgressDialog(this);

        firebaseAuth = FirebaseAuth.getInstance();

        if(firebaseAuth.getCurrentUser() != null){
            //profile activity here
            finish();
            startActivity(new Intent(getApplicationContext(),activity_Register.class));

        }

        Logout = (Button)findViewById(R.id.Logout);

        Calculator = (TextView)findViewById(R.id.Calculator);
        Schedule = (TextView)findViewById(R.id.Schedule);
        Payment = (TextView)findViewById(R.id.Payment);
        Price = (TextView)findViewById(R.id.Price);
        Booking = (TextView)findViewById(R.id.Booking);


        Logout.setOnClickListener(this);
        Calculator.setOnClickListener(this);
        Schedule.setOnClickListener(this);
        Payment.setOnClickListener(this);
        Price.setOnClickListener(this);
        Booking.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view == Logout){
            firebaseAuth.signOut();
            finish();
            startActivity(new Intent(this,activity_welcome.class));
        }

        if(view == Calculator){
            startActivity(new Intent(this,Activity4calc.class));
        }

        if(view == Schedule){
            startActivity(new Intent(this,jCollectorSchedule.class));
        }

        if(view == Payment){
            startActivity(new Intent(this,PaymentAdd.class));
        }
        if(view == Booking){
            startActivity(new Intent(this,activity_view_booking.class));
        }

        if(view == Price){
            startActivity(new Intent(this,activity_view_price.class));
        }
    }
}
