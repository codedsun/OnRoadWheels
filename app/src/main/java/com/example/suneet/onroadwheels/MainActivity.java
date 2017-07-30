package com.example.suneet.onroadwheels;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity implements AdapterView.OnItemSelectedListener {

    private EditText mobileNo;
    private Button goButton;
    private String mobNo;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference();
        mobileNo= (EditText) findViewById(R.id.mobileNo);
        goButton= (Button) findViewById(R.id.buttonGo);
        Spinner loginspinner = (Spinner)findViewById(R.id.loginspinner);
        loginspinner.setOnItemSelectedListener(this);
        List<String> list = new ArrayList<>();
        list.add("+91");
        list.add("+86");
        list.add("+49");
        list.add("+81");
        list.add("+44");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        loginspinner.setAdapter(adapter);

        goButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int len = mobileNo.getText().toString().length();
                if(len>=10&&len<=12) {
                    mobNo = mobileNo.getText().toString();
                    Intent i=new Intent(MainActivity.this,Registration.class);
                    i.putExtra("MobileNo",mobNo);
                    startActivity(i);
                }
                else
                {
                    mobileNo.setText("");
                    mobileNo.setHint("Invalid No");
                    mobileNo.setHintTextColor(Color.RED);
                }
            }
        });

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
