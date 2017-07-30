package com.example.suneet.onroadwheels;

import android.app.Application;
import android.util.Log;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by suneet on 29/7/17.
 */

public class App extends Application {

    public static String TAG="APPLICATION";
    public static DatabaseReference databaseReference;
    public static final String USERSBRANCH="USERS";
    public static final String VEHICLEBRANCH="VEHICLENO";
    public static String MOBILENO;




    @Override
    public void onCreate() {
        super.onCreate();

        databaseReference=FirebaseDatabase.getInstance().getReference();
        Log.e(TAG, "onCreate: "+databaseReference );
    }
}
