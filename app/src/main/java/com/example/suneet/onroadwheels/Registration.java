package com.example.suneet.onroadwheels;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

public class Registration extends Activity {
    Intent recieved;
    String mobNo;
    ImageView profilePic;
    TextView mobileNo;
    EditText username;
    RadioButton genderM;
    RadioButton genderF;
    EditText emergencyNo;
    EditText drivingLicenceNo;
    Button nextButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        profilePic= (ImageView) findViewById(R.id.userImage);
        mobileNo= (TextView) findViewById(R.id.userMobNo);
        username= (EditText) findViewById(R.id.userName);
        genderF= (RadioButton) findViewById(R.id.uFemale);
        genderM= (RadioButton) findViewById(R.id.uMale);
        emergencyNo= (EditText) findViewById(R.id.uEmergencyNo);
        drivingLicenceNo= (EditText) findViewById(R.id.uDLNO);
        nextButton= (Button) findViewById(R.id.buttonNext);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Registration.this,VehicalRegisteration.class);
                startActivity(i);
            }
        });
        recieved=getIntent();
        try {
            mobNo = recieved.getStringExtra("MobileNo");
            Log.e("TAG", "onCreate: "+mobNo );
            mobileNo.setText(mobNo+"");

        }
        catch (Exception e)
        {
            Log.e("TAG", "onCreate:registartion Exception"+e.getMessage());
        }

    }
}
