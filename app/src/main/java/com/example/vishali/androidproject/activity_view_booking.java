package com.example.vishali.androidproject;

import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class activity_view_booking extends AppCompatActivity {

    jDatabaseHelperA myDb;
    EditText editName, editSurname, editMarks, editTextId, editTextDate;
    Button btnViewAll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_booking);

        myDb = new jDatabaseHelperA(this);

        editTextId = (EditText)findViewById(R.id.editText);
        editName = (EditText)findViewById(R.id.editText4);
        editSurname = (EditText)findViewById(R.id.editText2);
        editMarks = (EditText)findViewById(R.id.editText3);
        editTextDate = (EditText)findViewById(R.id.editText5);

        btnViewAll = (Button)findViewById(R.id.button2);


        viewAll();
    }

    public void viewAll(){
        btnViewAll.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Cursor res = myDb.getAllData_k();
                        if(res.getCount() == 0){
                            //show message
                            showMessage("Error","Nothing found");
                            return;
                        }

                        StringBuffer buffer = new StringBuffer();
                        while (res.moveToNext()){
                            buffer.append("ID : "+ res.getString(0)+"\n");
                            buffer.append("name : "+ res.getString(1)+"\n");
                            buffer.append("surname : "+ res.getString(2)+"\n");
                            buffer.append("marks : "+ res.getString(3)+"\n");
                            buffer.append("date : "+ res.getString(4)+"\n");
                        }

                        //show all data
                        showMessage("Data",buffer.toString());

                    }
                }
        );
    }

    public void showMessage(String title, String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }
}
