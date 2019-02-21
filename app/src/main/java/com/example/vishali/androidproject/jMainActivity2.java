package com.example.vishali.androidproject;

import android.database.Cursor;
import android.os.Bundle;
import android.service.autofill.Validator;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class jMainActivity2 extends AppCompatActivity {

    jDatabaseHelperA myDb;
    EditText editPID,editCID,editCity ,editDate,editTime;
     Button btnAddData;
     Button btnviewAll;
     Button btnDelete;
     Button btnviewUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jmain2);

        myDb = new jDatabaseHelperA(this);

        editPID = (EditText) findViewById(R.id.PID);
        editCID = (EditText) findViewById(R.id.CollectorID);
        editCity = (EditText) findViewById(R.id.City);
        editDate = (EditText) findViewById(R.id.Date);
        editTime = (EditText) findViewById(R.id.Time);

        editCID.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {

               if(editCID.getText().length()<9){
               editCID.setError("Please Enter a valid CID email");

                   String email = editCID.getText().toString().trim();

                   String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

                   if (email.matches(emailPattern))
                   {
                       Toast.makeText(getApplicationContext(),"valid email address",Toast.LENGTH_SHORT).show();
                   }
                   else
                   {
                       Toast.makeText(getApplicationContext(),"Invalid email address", Toast.LENGTH_SHORT).show();
                   }


               }
            }
        });




        editDate.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {


                    String date = editCID.getText().toString().trim();

                    String datePattern = "(0?[1-9]|1[012]) [/.-] (0?[1-9]|[12][0-9]|3[01]) [/.-] ((19|20)\\d\\d)";



                    if (date.matches(datePattern))
                    {
                        Toast.makeText(getApplicationContext(),"valid date pattern",Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(),"Invalid date pattern", Toast.LENGTH_SHORT).show();
                    }



            }
        });







        btnAddData = (Button) findViewById(R.id.buttonAddCollector);
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
                        Integer deletedRows = myDb.deleteData(editPID.getText().toString());
                        if(deletedRows > 0)
                            Toast.makeText(jMainActivity2.this,"Data Deleted",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(jMainActivity2.this,"Data not Deleted",Toast.LENGTH_LONG).show();
                    }
                }
        );
    }

    public void UpdateData() {
        btnviewUpdate.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isUpdate = myDb.updateData(editPID.getText().toString(),
                                editCID.getText().toString(),
                                editCity.getText().toString(),editDate.getText().toString(),editTime.getText().toString());
                        if(isUpdate == true)
                            Toast.makeText(jMainActivity2.this,"Data Updated",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(jMainActivity2.this,"Data not Updated",Toast.LENGTH_LONG).show();
                    }
                }
        );
    }
    public  void AddData() {
        btnAddData.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isInserted = myDb.insertData(editCID.getText().toString(),
                                editCity.getText().toString(),
                                editDate.getText().toString(),editTime.getText().toString());
                        if(TextUtils.isEmpty(editCID.getText())) {
                            editCID.setError("Enter a CID email");
                            return;
                        }
                        else if(TextUtils.isEmpty(editCity.getText())) {
                            editCity.setError("Enter a City");
                            return;
                        }
                        else if(TextUtils.isEmpty(editDate.getText())) {
                            editDate.setError("Enter a Date");
                            return;
                        }
                        else if(TextUtils.isEmpty(editTime.getText())) {
                            editTime.setError("Enter a Time");
                            return;
                        }
                       else if(isInserted == true)
                            Toast.makeText(jMainActivity2.this,"Data Inserted",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(jMainActivity2.this,"Data not Inserted",Toast.LENGTH_LONG).show();

                        }





                }



        );




    }

    public void viewAll() {
        btnviewAll.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Cursor res = myDb.getAllData();
                        if(res.getCount() == 0) {
                            // show message
                            showMessage("Error","Nothing found");
                            return;
                        }

                        StringBuffer buffer = new StringBuffer();
                        while (res.moveToNext()) {
                            buffer.append("Pick Up ID :"+ res.getString(0)+"\n");
                            buffer.append("Collector :"+ res.getString(1)+"\n");

                            buffer.append("City :"+ res.getString(2)+"\n");
                            buffer.append("Date :"+ res.getString(3)+"\n");
                            buffer.append("Time :"+ res.getString(4)+"\n\n");

                        }

                        // Show all data
                        showMessage("PickUp Schedule",buffer.toString());
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


    //    @Override
    //    public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    //       getMenuInflater().inflate(R.menu.menu_main, menu);
    //     return true;
    //}

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);

    }
}
