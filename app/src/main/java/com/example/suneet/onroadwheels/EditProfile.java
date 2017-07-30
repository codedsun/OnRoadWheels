package com.example.suneet.onroadwheels;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class EditProfile extends Activity {
    String mobNo,userName,gender,emergencyNumber,DLNumber,address,age,bloodgp;
    User user;
    UserProfile userProfile;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
    }
}
