package com.example.suneet.onroadwheels;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class VehicalRegisteration extends Activity {
    Button buttonNext;
    Intent passData;
    String mobNo,userName,gender,emergencyNumber,DLNumber,address,age,bloodgp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehical_registeration);
        buttonNext = (Button)findViewById(R.id.buttonVechileNext);
        passData=getIntent();
        mobNo = passData.getStringExtra("MobileNo");
        userName = passData.getStringExtra("userName");
        gender = passData.getStringExtra("gender");
        emergencyNumber = passData.getStringExtra("EmergencyNumber");
        DLNumber = passData.getStringExtra("DrivingLicence");
        address = passData.getStringExtra("Address");
        age=passData.getStringExtra("Age");
        bloodgp = passData.getStringExtra("BloodGP");
        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(VehicalRegisteration.this,EditProfile.class));
                Toast.makeText(VehicalRegisteration.this, "Passed Data : "+mobNo+userName+gender+emergencyNumber+DLNumber+address+age, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
