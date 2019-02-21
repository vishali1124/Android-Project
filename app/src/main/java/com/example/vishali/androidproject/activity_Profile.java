package com.example.vishali.androidproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class activity_Profile extends AppCompatActivity {

    private static final String TAG = "ViewDatabase";

    //add Firebase Database stuff
    private FirebaseDatabase mFirebaseDatabase;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private DatabaseReference myRef;
    private  String userID;

    private ListView mListView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__profile);
        //declare the database reference object.
        mAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        myRef = mFirebaseDatabase.getReference();
        FirebaseUser user = mAuth.getCurrentUser();
        userID = user.getUid();

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                    toastMessage("Successfully signed in with: " + user.getEmail());
                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                    toastMessage("Successfully signed out.");
                }
                // ...
            }
        };

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                showData(dataSnapshot);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    private void showData(DataSnapshot dataSnapshot) {
        for(DataSnapshot ds : dataSnapshot.getChildren()){
            user uInfo = new user();
            uInfo.setUserName(ds.child(userID).getValue(user.class).getUserName());
            uInfo.setPhoneNo(ds.child(userID).getValue(user.class).getPhoneNo());
            uInfo.setHouseNo(ds.child(userID).getValue(user.class).getHouseNo());
            uInfo.setStreetAddress(ds.child(userID).getValue(user.class).getStreetAddress());
            uInfo.setTown(ds.child(userID).getValue(user.class).getTown());
            uInfo.setCountry(ds.child(userID).getValue(user.class).getCountry()); //set the phone_num

            //display all the information

            Log.d(TAG, "showData: userName: " + uInfo.getUserName());
            Log.d(TAG, "showData: phoneNo: " + uInfo.getPhoneNo());
            Log.d(TAG, "showData: houseNo: " + uInfo.getHouseNo());
            Log.d(TAG, "showData: streetAddress: " + uInfo.getStreetAddress());
            Log.d(TAG, "showData: town: " + uInfo.getTown());
            Log.d(TAG, "showData: country: " + uInfo.getCountry());

            ArrayList<String> array  = new ArrayList<>();
            array.add(uInfo.getUserName());
            array.add(uInfo.getPhoneNo());
            array.add(uInfo.getHouseNo());
            array.add(uInfo.getStreetAddress());
            array.add(uInfo.getTown());
            array.add(uInfo.getCountry());
            ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,array);
            mListView.setAdapter(adapter);

        }
    }

    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }


    /**
     * customizable toast
     * @param message
     */
    private void toastMessage(String message){
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }
}
