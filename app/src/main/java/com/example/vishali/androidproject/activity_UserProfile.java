package com.example.vishali.androidproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class activity_UserProfile extends AppCompatActivity implements View.OnClickListener {

        private EditText UserName;
        private EditText PhoneNo;
        private EditText HouseNo;
        private EditText StreetAddress;
        private EditText Town;
        private EditText Country;
        private TextView userEmail;

        private Button connect;
        private Button Login;


        private FirebaseAuth firebaseAuth;
        DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__user_profile);

        databaseReference = FirebaseDatabase.getInstance().getReference("user");

        firebaseAuth = FirebaseAuth.getInstance();

        if(firebaseAuth.getCurrentUser() == null){
            finish();
            startActivity(new Intent(this, activity_home.class));
        }

        FirebaseUser user = firebaseAuth.getCurrentUser();

        userEmail = (TextView) findViewById(R.id.userEmail);
        userEmail.setText("Welcome " +user.getEmail());

        UserName = (EditText) findViewById(R.id.UserName);
        PhoneNo = (EditText) findViewById(R.id.PhoneNo);
        HouseNo = (EditText) findViewById(R.id.HouseNo);
        StreetAddress = (EditText) findViewById(R.id.StreetAddress);
        Town = (EditText) findViewById(R.id.Town);
        Country = (EditText) findViewById(R.id.Country);

        connect = (Button) findViewById(R.id.connect);
        Login = (Button) findViewById(R.id.Login);


        connect.setOnClickListener(this);
        Login.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
            if(view == Login) {
                firebaseAuth.signOut();
                finish();
                startActivity(new Intent(getApplicationContext(), activity_Login.class));
            }

            if(view == connect){
                addUser();
            }


    }

    public  void addUser(){
        String userName = UserName.getText().toString();
        String phoneNo = PhoneNo.getText().toString();
        String houseNo = HouseNo.getText().toString();
        String streetAddress = StreetAddress.getText().toString();
        String town = Town.getText().toString();
        String country = Country.getText().toString();

        if(!TextUtils.isEmpty(userName) && !TextUtils.isEmpty(phoneNo) && !TextUtils.isEmpty(houseNo) && !TextUtils.isEmpty(streetAddress) && !TextUtils.isEmpty(town) && !TextUtils.isEmpty(country) ){
            String userId = databaseReference.push().getKey();

            user user = new user(userId,userName,phoneNo,houseNo,streetAddress,town,country);

            databaseReference.child(userId).setValue(user);

            UserName.setText("");
            PhoneNo.setText("");
            HouseNo.setText("");
            StreetAddress.setText("");
            Town.setText("");
            Country.setText("");

            Toast.makeText(this, "Your details connected with cleanIT. Click the home button", Toast.LENGTH_SHORT).show();

        }else {
            Toast.makeText(activity_UserProfile.this,"Please Enter the empty fields",Toast.LENGTH_LONG).show();
        }
    }

}
