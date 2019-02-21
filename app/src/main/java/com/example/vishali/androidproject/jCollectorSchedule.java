package com.example.vishali.androidproject;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class jCollectorSchedule extends AppCompatActivity {

    jDatabaseHelperA myDb;
    Button btnviewAll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jcollector_schedule);

        myDb = new jDatabaseHelperA(this);


        btnviewAll = (Button) findViewById(R.id.button_viewAll);
        viewAll();
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

//        public boolean onCreateOptionsMenu(Menu menu) {
//
//           getMenuInflater().inflate(R.menu.menu_main, menu);
//         return true;
//    }

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
