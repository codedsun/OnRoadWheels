package com.example.suneet.onroadwheels;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SearchScreenActivity extends Activity implements ValueEventListener {

    private DatabaseReference databaseReference;
    private User searchUser;
    private TextView vehicleNo;
    private RecyclerView recyclerView;
    private TextView name,bloodGroup,age,occupation,disease,gender,address;
    String VNO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_screen);
        Intent i=getIntent();
        String mobileNo=i.getStringExtra("VEHICLEMOBILENO");
        VNO =i.getStringExtra("VEHICLENO");
        databaseReference= FirebaseDatabase.getInstance().getReference().child(App.USERSBRANCH).child(mobileNo);
        databaseReference.addValueEventListener(this);
        vehicleNo = (TextView) findViewById(R.id.searchCarNo);
        recyclerView= (RecyclerView) findViewById(R.id.searchScreenrecyclerView);

        name= (TextView) findViewById(R.id.searchScreenName);
        bloodGroup= (TextView) findViewById(R.id.searchScreenBloodGroup);
        age= (TextView) findViewById(R.id.searchScreenAge);
        occupation= (TextView) findViewById(R.id.searchScreenOccupation);
        disease= (TextView) findViewById(R.id.searchScreenDisease);
        gender= (TextView) findViewById(R.id.searchScreenGender);
        address= (TextView) findViewById(R.id.searchScreenAddresss);





    }

    @Override
    public void onDataChange(DataSnapshot dataSnapshot) {
        searchUser=dataSnapshot.getValue(User.class);
        vehicleNo.setText(VNO);
        name.setText("Name : "+searchUser.getUserProfile().getUserName());
        bloodGroup.setText("Blood Group : "+searchUser.getUserProfile().getBloodGroup());
        age.setText("Age : "+searchUser.getUserProfile().getAge());
        occupation.setText("Occupation : "+searchUser.getUserProfile().getOccupation());
        disease.setText("Disease : "+searchUser.getUserProfile().getDisease());
        gender.setText("Gender : "+searchUser.getUserProfile().getGender());
        address.setText("Address : "+searchUser.getUserProfile().getAddress());

    }

    @Override
    public void onCancelled(DatabaseError databaseError) {

    }
}
