package com.example.suneet.onroadwheels;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class EditProfile extends Activity implements ValueEventListener {
    //String mobNo,userName,gender,emergencyNumber,DLNumber,address,age,bloodgp;
  //  User user;
    UserProfile userProfile;
    TextView dlNumber;
    EditText name;
    EditText userAddress;
    EditText userAge;
    EditText occupation;
    EditText carModel;
    EditText bloodgrp;
    EditText userdisease;
    User u;
   // User user;

    DatabaseReference dbRef= FirebaseDatabase.getInstance().getReference().child(App.USERSBRANCH).child(App.MOBILENO);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        dbRef.addValueEventListener(this);
        name = (EditText)findViewById(R.id.editProfileName);
        userAddress = (EditText)findViewById(R.id.editProfileAddress);
        userAge = (EditText)findViewById(R.id.editProfileAge);
        occupation = (EditText)findViewById(R.id.editProfileOccupation);
        carModel = (EditText)findViewById(R.id.editProfileCarModel);
        bloodgrp = (EditText)findViewById(R.id.editProfileBloodGroup);
        userdisease = (EditText)findViewById(R.id.editProfileDiseases);
        dlNumber = (TextView)findViewById(R.id.editProfileDLNO);
        Toast.makeText(this, "To Save the changes press back button", Toast.LENGTH_SHORT).show();


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        try {
            u.getUserProfile().setAddress(userAddress.getText().toString());
            u.getUserProfile().setAge(userAge.getText().toString());
            u.getUserProfile().setOccupation(occupation.getText().toString());
            u.getUserProfile().setDisease(userdisease.getText().toString());
            Toast.makeText(this, "Saved Details", Toast.LENGTH_SHORT).show();
            dbRef.setValue(u);
        }
        catch (Exception e)
        {
            Log.e("TAG", "onBackPressed: " );
        }
    }

    @Override
    public void onDataChange(DataSnapshot dataSnapshot) {

        u = dataSnapshot.getValue(User.class);
        userProfile = u.userProfile;
        name.setText(userProfile.getUserName());
        userAddress.setText(userProfile.getAddress());
        userAge.setText(userProfile.getAge());
        occupation.setText(userProfile.getOccupation());
        bloodgrp.setText(userProfile.getBloodGroup());
        dlNumber.setText(userProfile.getDrivingLicenceNo());
    }

    @Override
    public void onCancelled(DatabaseError databaseError) {

    }
}