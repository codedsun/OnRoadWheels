package com.example.suneet.onroadwheels;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class Registration extends Activity implements AdapterView.OnItemSelectedListener {
    Intent recieved;
    String mobNo,userName,gender,emergencyNumber,DLNumber,address,age,bloodgp;
    ImageView profilePic;
    TextView mobileNo;
    EditText username;
    RadioButton genderM;
    RadioButton genderF;
    EditText emergencyNo;
    EditText drivingLicenceNo;
    EditText userAddress;
    Button nextButton;
    EditText userAge;
    DatabaseReference database;
    Spinner loginspinner;
    List<String> list = new ArrayList<>();
//    User user;
//    UserProfile userProfile;
//    ArrayList<EmergencyContact> emergencyContacts=new ArrayList<>();
//    ArrayList<Vehicle> vehicles=new ArrayList<>();


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
        userAddress = (EditText)findViewById(R.id.userAddress);
        userAge = (EditText)findViewById(R.id.userAge);
        loginspinner = (Spinner)findViewById(R.id.regspinner);
        loginspinner.setOnItemSelectedListener(this);
        bloodgp = "AB+";
        list.add("AB+");
        list.add("AB-");
        list.add("A+");
        list.add("A-");
        list.add("O+");
        list.add("O-");
        list.add("B+");
        list.add("B-");
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        loginspinner.setAdapter(adapter);

        recieved=getIntent();
        try {
            mobNo = recieved.getStringExtra("MobileNo");
            Log.e("TAG", "onCreate: "+mobNo );
            mobileNo.setText("+91-"+mobNo);

        }
        catch (Exception e)
        {
            Log.e("TAG", "onCreate:registration Exception"+e.getMessage());
        }
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
              public void onClick(View v) {
//                userProfile=new UserProfile(username.getText().toString(),"",drivingLicenceNo.getText().toString(),"B+","address","age","male","status","famer","disease");
//                emergencyContacts.add(new EmergencyContact(emergencyNo.getText().toString(),"Father"));
//                vehicles.add(new Vehicle("UP32","Lambo","4W"));
//                user=new User(userProfile,emergencyContacts,vehicles);
//                database.child("Users").child(mobNo).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
//                    @Override
//                    public void onComplete(@NonNull Task<Void> task) {
//                        Toast.makeText(Registration.this,"Added Bhai",Toast.LENGTH_SHORT).show();
//                    }

//                });

                    userName = username.getText().toString();
                    if(genderM.isChecked()){
                        gender="Male";
                    }
                    else{
                        gender="Female";
                    }
                    emergencyNumber = emergencyNo.getText().toString();
                    DLNumber = drivingLicenceNo.getText().toString();
                    address = userAddress.getText().toString();
                    age = userAge.getText().toString();
                if(userName.isEmpty()||gender.isEmpty()||emergencyNumber.isEmpty()||DLNumber.isEmpty()||address.isEmpty()||age.isEmpty()){
                    Toast.makeText(Registration.this, "All Fields Required!", Toast.LENGTH_SHORT).show();
                }
                else {
                    Intent i=new Intent(Registration.this,VehicleRegistration.class);
                    i.putExtra("MobileNo",mobNo);
                    i.putExtra("userName",userName);
                    i.putExtra("gender",gender);
                    i.putExtra("EmergencyNumber",emergencyNumber);
                    i.putExtra("DrivingLicence",DLNumber);
                    i.putExtra("Address",address);
                    i.putExtra("BloodGP",bloodgp);
                    i.putExtra("Age",age);
                    startActivity(i);
                }
            }
        });
        profilePic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i,0);
            }
        });


    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        bloodgp =list.get(position);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        bloodgp = "AB+";

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


    }
}
