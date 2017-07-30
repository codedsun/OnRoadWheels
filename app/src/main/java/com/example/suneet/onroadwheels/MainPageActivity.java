package com.example.suneet.onroadwheels;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainPageActivity extends Activity implements ValueEventListener {
    String mobileNo;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
        mobileNo=App.MOBILENO;
        Log.e("TAG", "onCreate: "+mobileNo );
        databaseReference=FirebaseDatabase.getInstance().getReference().child(App.USERSBRANCH).child(mobileNo);
        databaseReference.addValueEventListener(this);


       // Toast.makeText(this, "Welcome "+databaseReference.getKey("age"), Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onDataChange(DataSnapshot dataSnapshot) {
        User user=dataSnapshot.getValue(User.class);
        Toast.makeText(this,"HII "+user.getUserProfile().getAge(),Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onCancelled(DatabaseError databaseError) {

    }
}
