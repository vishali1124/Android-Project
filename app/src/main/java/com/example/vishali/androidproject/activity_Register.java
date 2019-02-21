package com.example.vishali.androidproject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class activity_Register extends AppCompatActivity implements View.OnClickListener {
    private Button register;
    private TextView Login;

    private EditText Email;
    private EditText Password;


    private ProgressDialog ProgressDialog;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__register);

        firebaseAuth = FirebaseAuth.getInstance();

        if(firebaseAuth.getCurrentUser() != null){
            //profile activity here
            finish();
            startActivity(new Intent(getApplicationContext(),activity_UserProfile.class));

        }


        ProgressDialog = new ProgressDialog(this);

        register = (Button) findViewById(R.id.register);
        Login = (TextView) findViewById(R.id.Login);


        Email = (EditText) findViewById(R.id.Email);
        Password = (EditText) findViewById(R.id.Password);


        register.setOnClickListener(this);
        Login.setOnClickListener(this);

    }

    private void registerUser() {
        String email = Email.getText().toString().trim();
        String password = Password.getText().toString().trim();


        if (TextUtils.isEmpty(email)) {
            //email is Empty
            Toast.makeText(this, "Please Enter Email Address", Toast.LENGTH_SHORT).show();
            //stopping the function execution further
            return;
        }

        if (TextUtils.isEmpty(password)) {
            //password is Empty
            Toast.makeText(this, "Please Enter Email Password", Toast.LENGTH_SHORT).show();
            //stopping the function execution further
            return;
        }

        //if validations are ok
        //we will first show a progressbar
        ProgressDialog.setMessage("Registering User....");
        ProgressDialog.show();

        firebaseAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            //user is successfully registered and logged in
                            finish();
                            startActivity(new Intent(getApplicationContext(),activity_UserProfile.class));
                            Toast.makeText(activity_Register.this, "Registered Successfully", Toast.LENGTH_SHORT);
                        }else {
                            Toast.makeText(activity_Register.this,"Could not register.Please try again later.",Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }

    @Override
    public void onClick(View view) {
        if (view == register) {
            registerUser();
        }

        if (view == Login) {
            // loginUser();
            startActivity(new Intent(this,activity_Login.class));
        }
    }
}
