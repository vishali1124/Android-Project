package com.example.vishali.androidproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Activity4calc extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity4calc);

        Button AddBtn= (Button) findViewById(R.id.AddBtn);
        Button SubBtn= (Button) findViewById(R.id.SubBtn);
        Button MulBtn = (Button) findViewById(R.id.MulBtn);
        Button DivBtn=(Button) findViewById(R.id.DivBtn);

        AddBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText FirstNumEditText = (EditText) findViewById(R.id.FirstNumEditText);
                EditText SecondNumEditText = (EditText) findViewById(R.id.SecondNumEditText);
                TextView Result = (TextView) findViewById(R.id.Result);

                int num1 = Integer.parseInt(FirstNumEditText.getText().toString());
                int num2 = Integer.parseInt(SecondNumEditText.getText().toString());


                int result = num1 + num2;


                Result.setText(result + "");
            }
        });


        SubBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText FirstNumEditText = (EditText) findViewById(R.id.FirstNumEditText);
                EditText SecondNumEditText = (EditText) findViewById(R.id.SecondNumEditText);
                TextView Result = (TextView) findViewById(R.id.Result);

                int num1 = Integer.parseInt(FirstNumEditText.getText().toString());
                int num2 = Integer.parseInt(SecondNumEditText.getText().toString());


                int result = num1 - num2;


                Result.setText(result + "");
            }
        });



        MulBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText FirstNumEditText = (EditText) findViewById(R.id.FirstNumEditText);
                EditText SecondNumEditText = (EditText) findViewById(R.id.SecondNumEditText);
                TextView Result = (TextView) findViewById(R.id.Result);

                int num1 = Integer.parseInt(FirstNumEditText.getText().toString());
                int num2 = Integer.parseInt(SecondNumEditText.getText().toString());


                int result = num1 * num2;


                Result.setText(result + "");
            }
        });



        DivBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText FirstNumEditText = (EditText) findViewById(R.id.FirstNumEditText);
                EditText SecondNumEditText = (EditText) findViewById(R.id.SecondNumEditText);
                TextView Result = (TextView) findViewById(R.id.Result);

                int num1 = Integer.parseInt(FirstNumEditText.getText().toString());
                int num2 = Integer.parseInt(SecondNumEditText.getText().toString());


                int result = num1 / num2;


                Result.setText(result + "");
            }
        });


    }
}
