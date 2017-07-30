package com.example.suneet.onroadwheels;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class VehicleRegistration extends Activity {
    Button buttonNext;
    Intent passData;
    String mobNo,userName,gender,emergencyNumber,DLNumber,address,age,bloodgp;
    UserProfile userProfile;
    ArrayList<EmergencyContact> contactList = new ArrayList<>();
    User newuser;
    ArrayList<Vehicle> vehicleList = new ArrayList<>();
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class VehicleRegistration extends Activity {

    private ImageView profilePic;
    private ImageView banner;
    private Button add4Wheeler;

    private Button add2Wheeler;
    private RecyclerView fourWheelerRecyclerView;
    private RecyclerView twoWheelerRecycelerView;
    private Button buttonNext;

    Intent passData;
    String mobNo,userName,gender,emergencyNumber,DLNumber,address,age,bloodgp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehical_registeration);

        buttonNext = (Button)findViewById(R.id.buttonVechileNext);

        profilePic= (ImageView) findViewById(R.id.userImage);
        banner= (ImageView) findViewById(R.id.vechilePromo);
        add4Wheeler= (Button) findViewById(R.id.button4wheeler);
        add2Wheeler= (Button) findViewById(R.id.button2wheeler);
        fourWheelerRecyclerView= (RecyclerView) findViewById(R.id.fourWheelerRecyclerView);
        twoWheelerRecycelerView= (RecyclerView) findViewById(R.id.twoWheelerRecyclerView);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(this);
        TypeWheelerRecyclerAdapter typeWheelerRecyclerAdapter;
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

        userProfile.setUserName(userName);
        userProfile.setDrivingLicenceNo(DLNumber);
        userProfile.setAddress(address);
        userProfile.setAge(age);
        userProfile.setBloodGroup(bloodgp);
        userProfile.setGender(gender);
        userProfile.setStatus("No");
        userProfile.setOccupation("occupation");
        userProfile.setDisease("None");
        userProfile.setImgUrl("None");
        contactList.add(new EmergencyContact(mobNo,"Self"));
        contactList.add(new EmergencyContact(emergencyNumber,"Father"));

        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(VehicleRegistration.this,EditProfile.class));
                Toast.makeText(VehicleRegistration.this, "Passed Data : "+mobNo+userName+gender+emergencyNumber+DLNumber+address+age, Toast.LENGTH_SHORT).show();








                //Final User Object
                newuser = new User(userProfile,contactList,vehicleList);


            }
        });
    }
}
