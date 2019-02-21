package com.example.vishali.androidproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class activity_welcome extends AppCompatActivity implements View.OnClickListener {

    private Button Login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        Login = (Button) findViewById(R.id.Login);

        Login.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        if(view == Login){
            finish();
            startActivity(new Intent(this, jMainActivity.class));
        }

    }
}
