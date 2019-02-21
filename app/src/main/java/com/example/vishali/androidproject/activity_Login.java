package com.example.vishali.androidproject;

import android.app.ProgressDialog;
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
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class activity_Login extends AppCompatActivity implements View.OnClickListener {
    private Button Login;
    private TextView forgot;
    private TextView Register;


    private EditText Email;
    private EditText Password;

    private android.app.ProgressDialog ProgressDialog;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ProgressDialog = new ProgressDialog(this);

        firebaseAuth = FirebaseAuth.getInstance();

        if(firebaseAuth.getCurrentUser() != null){
            //profile activity here
            finish();
            startActivity(new Intent(getApplicationContext(),activity_Register.class));

        }


        Login = (Button) findViewById(R.id.Login);
        Register = (TextView) findViewById(R.id.Register);
        forgot = (TextView) findViewById(R.id.forgot);



        Email = (EditText) findViewById(R.id.Email);
        Password = (EditText) findViewById(R.id.Password);

        Login.setOnClickListener(this);
        forgot.setOnClickListener(this);
        Register.setOnClickListener(this);

    }


    private void loginUser() {
        String email = Email.getText().toString().trim();
        String password = Password.getText().toString().trim();


        if (TextUtils.isEmpty(email)) {
            //email is Empty
            Toast.makeText(this, "Please Enter Correct Email Address", Toast.LENGTH_SHORT).show();
            //stopping the function execution further
            return;
        }

        if (TextUtils.isEmpty(password)) {
            //password is Empty
            Toast.makeText(this, "Please Enter Correct Email Password", Toast.LENGTH_SHORT).show();
            //stopping the function execution further
            return;
        }

        //if validations are ok
        //we will first show a progressbar
        ProgressDialog.setMessage("Login User please wait....");
        ProgressDialog.show();

        firebaseAuth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        ProgressDialog.dismiss();

                        if(task.isSuccessful()){
                            //start the profile
                            finish();
                            startActivity(new Intent(getApplicationContext(),activity_home.class));
                        }
                    }
                });
    }


    @Override
    public void onClick(View view) {
        if (view == Login) {
            loginUser();
            finish();
            startActivity(new Intent(getApplicationContext(),activity_home.class));

        }

        if (view == Register) {
            // Register
            finish();
            startActivity(new Intent(this,activity_Register.class));
        }

        if (view == forgot) {
            // forgot();
        }

    }


}
