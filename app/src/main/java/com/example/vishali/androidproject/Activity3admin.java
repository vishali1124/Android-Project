package com.example.vishali.androidproject;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Activity3admin extends AppCompatActivity {
    jDatabaseHelperA myDb;
    EditText editGarbageName,editPrice ,editTextId;
    Button btnAddData;
    Button btnviewAll;
    Button btnDelete;
    Button btnviewUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity3admin);
        myDb = new jDatabaseHelperA(this);

        editGarbageName = (EditText) findViewById(R.id.garbage_name);
        editPrice = (EditText) findViewById(R.id.garbage_price);
        editTextId = (EditText) findViewById(R.id.id);
        btnAddData = (Button) findViewById(R.id.button_add);
        btnviewAll = (Button) findViewById(R.id.button_viewAll);
        btnviewUpdate = (Button) findViewById(R.id.button_update);
        btnDelete = (Button) findViewById(R.id.button_delete);
        AddData();
        viewAll();
        UpdateData();
        DeleteData();
    }

    public void DeleteData(){
        btnDelete.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Integer deletedRows = myDb.deleteData_e(editTextId.getText().toString());
                        if(deletedRows > 0)
                            Toast.makeText(Activity3admin.this,"Data Deleted",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(Activity3admin.this,"Data not Deleted",Toast.LENGTH_LONG).show();
                    }
                }
        );
    }
    public void UpdateData() {
        btnviewUpdate.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isUpdate = myDb.updateData_e(editTextId.getText().toString(),
                                editGarbageName.getText().toString(),
                                editPrice.getText().toString());
                        if(isUpdate == true)
                            Toast.makeText(Activity3admin.this,"Data Updated",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(Activity3admin.this,"Data not Updated",Toast.LENGTH_LONG).show();
                    }
                }
        );
    }
    public  void AddData() {
        btnAddData.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isInserted = myDb.insertData_e(editGarbageName.getText().toString(),
                                editPrice.getText().toString());
                        if(isInserted == true)
                            Toast.makeText(Activity3admin.this,"Data Inserted",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(Activity3admin.this,"Data not Inserted",Toast.LENGTH_LONG).show();
                    }
                }
        );
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
