package com.example.vishali.androidproject;

import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class activity_view_price extends AppCompatActivity {
    jDatabaseHelperA myDb;
    EditText editGarbageName,editPrice ,editTextId;
    Button btnviewAll;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_price);

        myDb = new jDatabaseHelperA(this);

        editGarbageName = (EditText) findViewById(R.id.garbage_name);
        editPrice = (EditText) findViewById(R.id.garbage_price);
        editTextId = (EditText) findViewById(R.id.id);
        btnviewAll = (Button) findViewById(R.id.button_viewAll);

        viewAll();

    }

    public void viewAll() {
        btnviewAll.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Cursor res = myDb.getAllData_e();
                        if(res.getCount() == 0) {
                            // show message
                            showMessage("Error","Nothing found");
                            return;
                        }

                        StringBuffer buffer = new StringBuffer();
                        while (res.moveToNext()) {
                            buffer.append("Id :"+ res.getString(0)+"\n");
                            buffer.append("Garbage_Name :"+ res.getString(1)+"\n");
                            buffer.append("Price :"+ res.getString(2)+"\n\n");

                        }

                        // Show all data
                        showMessage("Data",buffer.toString());
                    }
                }
        );
    }

    public void showMessage(String title,String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }
}
