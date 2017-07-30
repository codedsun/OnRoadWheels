package com.example.suneet.onroadwheels;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainPageActivity extends Activity implements ValueEventListener, View.OnClickListener {
    String mobileNo;
    DatabaseReference databaseReference;
    private User user;
    private ImageView imageView;
    private ImageView navigationIcon;
    private TextView name;
    private TextView licenceNo;
    private TextView city;
    private TextView location;
    private TextView bloodGroup;
    private EditText searchVehicleNo;
    private ImageButton searchButton;
    String vehicleNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
        mobileNo=App.MOBILENO;
        Log.e("TAG", "onCreate: "+mobileNo );
        databaseReference=FirebaseDatabase.getInstance().getReference().child(App.USERSBRANCH).child(mobileNo);
        databaseReference.addValueEventListener(this);


        imageView= (ImageView) findViewById(R.id.mainPageDummyImage);
        navigationIcon= (ImageView) findViewById(R.id.mainPageNavigationIcon);
        name= (TextView) findViewById(R.id.mainPageName);
        licenceNo= (TextView) findViewById(R.id.mainPageLicenceNo);
        city= (TextView) findViewById(R.id.mainPageCity);
        location= (TextView) findViewById(R.id.mainPageLocation);
        bloodGroup= (TextView) findViewById(R.id.mainPageBloodGroup);
        searchVehicleNo= (EditText) findViewById(R.id.mainPageVehicleSearch);
        searchButton= (ImageButton) findViewById(R.id.mainPageSearchButton);
        searchButton.setOnClickListener(this);
        navigationIcon.setOnClickListener(this);

       // Toast.makeText(this, "Welcome "+databaseReference.getKey("age"), Toast.LENGTH_SHORT).show();

    }


    @Override
    public void onDataChange(DataSnapshot dataSnapshot) {
        User user=dataSnapshot.getValue(User.class);
        this.user=user;
        Toast.makeText(this,"HII "+user.getUserProfile().getAge(),Toast.LENGTH_SHORT).show();
        try {
            name.setText(user.getUserProfile().getUserName());
            licenceNo.setText(user.getUserProfile().getDrivingLicenceNo());
            city.setText("Chandigarh");
            location.setText("Infosys-Chandigarh");
            bloodGroup.setText(user.getUserProfile().getBloodGroup());
        }
        catch (Exception e)
        {
            Log.e("TAG", "onDataChange:TRY CATCH BLOCKS " );
        }

    }

    @Override
    public void onCancelled(DatabaseError databaseError) {

    }

    @Override
    public void onClick(View v) {
        if(v.getId()==searchButton.getId())
        {
            vehicleNumber=searchVehicleNo.getText().toString();
            Log.e("TAG", "onCreate: "+vehicleNumber );

            if((vehicleNumber.length()>0))
            {
                DatabaseReference databaseReference1=FirebaseDatabase.getInstance().getReference().child(App.VEHICLEBRANCH).child(vehicleNumber);
                if(databaseReference1!=null) {
                    databaseReference1.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            String s = dataSnapshot.getValue(String.class);
                            Toast.makeText(MainPageActivity.this, "Hello "+s, Toast.LENGTH_SHORT).show();
                            if(s!=null)
                            {
                                Intent i=new Intent(MainPageActivity.this,SearchScreenActivity.class);
                                i.putExtra("VEHICLEMOBILENO",s);
                                i.putExtra("VEHICLENO",vehicleNumber);
                                startActivity(i);
                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                }
                else
                {
                    Toast.makeText(this, "No Vehicle Find", Toast.LENGTH_SHORT).show();
                }
            }
            else
            {
                Toast.makeText(this, "Enter the Number", Toast.LENGTH_SHORT).show();
            }
        }
        if(v.getId()==navigationIcon.getId())
        {
            startActivity(new Intent(MainPageActivity.this,EditProfile.class));

        }

    }
}
