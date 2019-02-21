package com.example.vishali.androidproject;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class activity_Booking_customer extends AppCompatActivity {

    jDatabaseHelperA myDb;
    EditText editName, editSurname, editMarks, editTextId, editTextDate;
    Button btnAddData;
    Button btnViewAll;
    Button btnDelete;
    Button btnviewUpdate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__booking_customer);

        myDb = new jDatabaseHelperA(this);

        editTextId = (EditText)findViewById(R.id.editText);
        editName = (EditText)findViewById(R.id.editText4);
        editSurname = (EditText)findViewById(R.id.editText2);
        editMarks = (EditText)findViewById(R.id.editText3);
        editTextDate = (EditText)findViewById(R.id.editText5);
        btnAddData = (Button)findViewById(R.id.button);
        btnViewAll = (Button)findViewById(R.id.button2);
        btnviewUpdate= (Button)findViewById(R.id.button3);
        btnDelete= (Button)findViewById(R.id.button4);
        AddData();
        viewAll();
        updateData();
        DeleteData();
    }

    public void DeleteData(){
        btnDelete.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Integer deletedRows = myDb.deleteData_k(editTextId.getText().toString());
                        if(deletedRows > 0)
                            Toast.makeText(activity_Booking_customer.this, "Data Deleted", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(activity_Booking_customer.this, "Data not Deleted ",Toast.LENGTH_LONG).show();
                    }
                }
        );
    }
    public void AddData(){
        btnAddData.setOnClickListener(
                new View.OnClickListener() {
                    @RequiresApi(api = Build.VERSION_CODES.N)
                    @Override
                    public void onClick(View v) {
                        boolean isInserted = myDb.insertData_k( editName.getText().toString() , editSurname.getText().toString() , editMarks.getText().toString(), editTextDate.getText().toString());
                        if (isInserted == true)
                            Toast.makeText(activity_Booking_customer.this, "Data Inserted ", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(activity_Booking_customer.this, "Data Not Inserted", Toast.LENGTH_LONG).show();
                    }
                }
        );
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

    public void updateData(){
        btnviewUpdate.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isUpdate = myDb.updateData_k(editTextId.getText().toString(),editName.getText().toString(),editSurname.getText().toString(),editMarks.getText().toString(), editTextDate.getText().toString());
                        if(isUpdate == true){
                            Toast.makeText(activity_Booking_customer.this, "Data Updated",Toast.LENGTH_LONG).show();
                        }
                        else
                            Toast.makeText(activity_Booking_customer.this, "Data is not updated",Toast.LENGTH_LONG).show();
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
