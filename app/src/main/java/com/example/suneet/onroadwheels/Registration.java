package com.example.suneet.onroadwheels;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

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
    Spinner spinner;
    DatabaseReference database;
    User user;
    UserProfile userProfile;
    ArrayList<EmergencyContact>  emergencyContacts=new ArrayList<>();
    ArrayList<Vehicle> vehicles=new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        database = FirebaseDatabase.getInstance().getReference();
        profilePic= (ImageView) findViewById(R.id.userImage);
        mobileNo= (TextView) findViewById(R.id.userMobNo);
        username= (EditText) findViewById(R.id.userName);
        genderF= (RadioButton) findViewById(R.id.uFemale);
        genderM= (RadioButton) findViewById(R.id.uMale);
        emergencyNo= (EditText) findViewById(R.id.uEmergencyNo);
        drivingLicenceNo= (EditText) findViewById(R.id.uDLNO);
        nextButton= (Button) findViewById(R.id.buttonNext);
        spinner= (Spinner) findViewById(R.id.regspinner);
        recieved=getIntent();
        try {
            mobNo = recieved.getStringExtra("MobileNo");
            Log.e("TAG", "onCreate: "+mobNo );
            mobileNo.setText(mobNo);

        }
        catch (Exception e)
        {
            Log.e("TAG", "onCreate:registartion Exception"+e.getMessage());
        }
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userProfile=new UserProfile(username.getText().toString(),"",drivingLicenceNo.getText().toString(),"B+","addreess","age","male","status","famer","disease");
                emergencyContacts.add(new EmergencyContact(emergencyNo.getText().toString(),"Father"));
                vehicles.add(new Vehicle("UP32","Lambo","4W"));
                user=new User(userProfile,emergencyContacts,vehicles);
                database.child("Users").child(mobNo).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(Registration.this,"Added Bhai",Toast.LENGTH_SHORT).show();
                    }
                });
                Intent i=new Intent(Registration.this,VehicalRegisteration.class);
                startActivity(i);
            }
        });


    }
}
