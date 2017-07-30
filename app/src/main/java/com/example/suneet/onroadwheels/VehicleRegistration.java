package com.example.suneet.onroadwheels;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class VehicleRegistration extends Activity {
    public static String TAG="VehicleRegistration";

    private ImageView profilePic;
    private ImageView banner;
    private Button add4Wheeler;
    private android.app.AlertDialog.Builder builder;
    private android.app.AlertDialog alertDialog;
    private Button add2Wheeler;
    private DatabaseReference databaseReference;
    UserProfile userProfile=new UserProfile();
    ArrayList<EmergencyContact> contactList = new ArrayList<>();
    User newuser;
    ArrayList<Vehicle> vehicleList = new ArrayList<>();
    private RecyclerView fourWheelerRecyclerView;
    private RecyclerView twoWheelerRecycelerView;
    private Button buttonNext;

    Intent passData;
    String mobNo,userName,gender,emergencyNumber,DLNumber,address,age,bloodgp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehical_registeration);
        databaseReference=App.databaseReference;
        buttonNext = (Button)findViewById(R.id.buttonVechileNext);

        profilePic= (ImageView) findViewById(R.id.userImage);
        banner= (ImageView) findViewById(R.id.vechilePromo);
        add4Wheeler= (Button) findViewById(R.id.button4wheeler);
        add2Wheeler= (Button) findViewById(R.id.button2wheeler);
        builder=new android.app.AlertDialog.Builder(this);
        fourWheelerRecyclerView= (RecyclerView) findViewById(R.id.fourWheelerRecyclerView);
        twoWheelerRecycelerView= (RecyclerView) findViewById(R.id.twoWheelerRecyclerView);
        final RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(this);
        final RecyclerView.LayoutManager layoutManager1=new LinearLayoutManager(this);

        //        fourWheelerRecyclerView.setLayoutManager(layoutManager);
       // fourWheelerRecyclerView.setAdapter(typeWheelerRecyclerAdapter);
       // typeWheelerRecyclerAdapter.notifyDataSetChanged();
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
                Log.e(TAG, "onClick: "+databaseReference);
                Toast.makeText(VehicleRegistration.this, "Passed Data : "+mobNo+userName+gender+emergencyNumber+DLNumber+address+age, Toast.LENGTH_SHORT).show();

                //Final User Object
                newuser = new User(userProfile,contactList,vehicleList);
                Log.e(TAG, "onNew uSer "+mobNo);
                final DatabaseReference databaseReference1=FirebaseDatabase.getInstance().getReference();
                databaseReference.child(App.USERSBRANCH).child(mobNo).setValue(newuser).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Log.e(TAG, "onComplete: aagye asa" );

                    }
                });

                startActivity(new Intent(VehicleRegistration.this,MainPageActivity.class));



            }
        });
        add2Wheeler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 final EditText vehicleNo;
                final EditText vehicleModel;

                View layout=LayoutInflater.from(VehicleRegistration.this).inflate(R.layout.vehicle_alertdialog_layout,null);
                vehicleNo= (EditText) layout.findViewById(R.id.alertDialogEditTextVehicleNumber);
                vehicleModel=(EditText)layout.findViewById(R.id.alertDialogEditTextVehicleModel);

                builder.setView(layout)
                        .setTitle("Add 2 Wheeler")
                        .setCancelable(false)
                        .setPositiveButton("Add Wheel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Vehicle newVehicle=new Vehicle(vehicleNo.getText().toString(),vehicleModel.getText().toString(),"2");
                                vehicleList.add(newVehicle);
                                banner.setVisibility(View.GONE);
                                twoWheelerRecycelerView.setVisibility(View.VISIBLE);
                                TypeWheelerRecyclerAdapter typeWheelerRecyclerAdapter=new TypeWheelerRecyclerAdapter(vehicleList,getBaseContext());
                                twoWheelerRecycelerView.setLayoutManager(layoutManager);

                                twoWheelerRecycelerView.setAdapter(typeWheelerRecyclerAdapter);
                                typeWheelerRecyclerAdapter.notifyDataSetChanged();
                                Toast.makeText(VehicleRegistration.this, "Added Sucessfully", Toast.LENGTH_SHORT).show();
                                databaseReference.child(App.VEHICLEBRANCH).child(vehicleNo.getText().toString()).setValue(mobNo).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        Log.e(TAG, "onComplete:VEHICLE BRANCH" );
                                    }
                                });


                            }
                        })
                        .setNegativeButton("Dismiss",null);
                alertDialog=builder.create();
                alertDialog.show();

            }
        });
        add4Wheeler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final EditText vehicleNo;
                final EditText vehicleModel;

                View layout=LayoutInflater.from(VehicleRegistration.this).inflate(R.layout.vehicle_alertdialog_layout,null);
                vehicleNo= (EditText) layout.findViewById(R.id.alertDialogEditTextVehicleNumber);
                vehicleModel=(EditText)layout.findViewById(R.id.alertDialogEditTextVehicleModel);
                builder.setView(layout)
                        .setTitle("Add 4 Wheeler")
                        .setCancelable(false)
                        .setPositiveButton("Add Wheels", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Vehicle newVehicle=new Vehicle(vehicleNo.getText().toString(),vehicleModel.getText().toString(),"4");
                                vehicleList.add(newVehicle);
                                banner.setVisibility(View.GONE);
                                fourWheelerRecyclerView.setVisibility(View.VISIBLE);
                                TypeWheelerRecyclerAdapter typeWheelerRecyclerAdapter=new TypeWheelerRecyclerAdapter(vehicleList,getBaseContext());
                                fourWheelerRecyclerView.setLayoutManager(layoutManager1);
                                fourWheelerRecyclerView.setAdapter(typeWheelerRecyclerAdapter);


                                typeWheelerRecyclerAdapter.notifyDataSetChanged();
                                Toast.makeText(VehicleRegistration.this, "Added Sucessfully", Toast.LENGTH_SHORT).show();

                            }
                        })
                        .setNegativeButton("Dismiss",null);
                alertDialog=builder.create();
                alertDialog.show();
            }
        });
    }




}
