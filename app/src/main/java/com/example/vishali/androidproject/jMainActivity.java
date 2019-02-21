package com.example.vishali.androidproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class jMainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button Customer;
    private Button Collector;
    private Button Admin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jmain);

        Admin = (Button) findViewById(R.id.Admin);
        Customer = (Button) findViewById(R.id.Customer);
        Collector = (Button) findViewById(R.id.Collector);

        Customer.setOnClickListener(this);
        Collector.setOnClickListener(this);
        Admin.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        if(view == Admin) {

            startActivity(new Intent(this, activity_Admin_Login.class));
        }

        if(view == Collector) {

            startActivity(new Intent(this, activity_collector_Login.class));
        }

        if(view == Customer) {

            startActivity(new Intent(this, activity_Login.class));
        }
    }
}