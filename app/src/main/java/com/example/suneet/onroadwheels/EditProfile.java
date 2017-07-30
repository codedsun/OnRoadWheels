package com.example.suneet.onroadwheels;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;

public class EditProfile extends Activity {
    //String mobNo,userName,gender,emergencyNumber,DLNumber,address,age,bloodgp;
    User user;
    UserProfile userProfile;
    TextView dlNumber;
    EditText name;
    EditText userAddress;
    EditText userAge;
    EditText occupation;
    EditText carModel;
    EditText bloodgrp;
    EditText userdisease;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        name = (EditText)findViewById(R.id.editProfileName);
        userAddress = (EditText)findViewById(R.id.editProfileAddress);
        userAge = (EditText)findViewById(R.id.editProfileAge);
        occupation = (EditText)findViewById(R.id.editProfileOccupation);
        carModel = (EditText)findViewById(R.id.editProfileCarModel);
        bloodgrp = (EditText)findViewById(R.id.editProfileBloodGroup);
        userdisease = (EditText)findViewById(R.id.editProfileDiseases);
        dlNumber = (TextView)findViewById(R.id.editProfileDLNO);
        userProfile = user.userProfile;
        name.setText(userProfile.getUserName());
        userAddress.setText(userProfile.getAddress());
        userAge.setText(userProfile.getAge());
        occupation.setText(userProfile.getOccupation());
        bloodgrp.setText(userProfile.getBloodGroup());
        dlNumber.setText(userProfile.getDrivingLicenceNo());


    }
}
