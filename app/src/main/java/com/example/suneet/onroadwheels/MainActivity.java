package com.example.suneet.onroadwheels;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.os.Bundle;

import android.util.Log;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
//Screen1

public class MainActivity extends Activity implements AdapterView.OnItemSelectedListener {

    private EditText mobileNo;
    private Button goButton;
    private String mobNo;
    private String mVerificationId;
    private PhoneAuthProvider.ForceResendingToken mResendToken;
    private Button mainNext;
    LinearLayout linearLayoutMain2;
    EditText mainVerificationCode;
    FirebaseDatabase firebaseDatabase;
    FirebaseAuth mAuth;
    DatabaseReference databaseReference;
    PhoneAuthProvider phoneAuthProvider;
    PhoneAuthCredential credential;
    PhoneAuthProvider.OnVerificationStateChangedCallbacks mcallback;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        firebaseDatabase=FirebaseDatabase.getInstance();
        mAuth=FirebaseAuth.getInstance();
        databaseReference=firebaseDatabase.getReference();
        phoneAuthProvider=PhoneAuthProvider.getInstance();
        mobileNo= (EditText) findViewById(R.id.mobileNo);
        goButton= (Button) findViewById(R.id.buttonGo);
        linearLayoutMain2= (LinearLayout) findViewById(R.id.linearLayoutMain2);
        mainVerificationCode= (EditText) findViewById(R.id.mainVerificationCode);
        mainNext= (Button) findViewById(R.id.mainButtonNext);

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
                    App.MOBILENO=mobNo;
                    Log.e("TAG", "onClick: "+mobNo );
                    phoneAuthProvider.verifyPhoneNumber(mobNo, 60, TimeUnit.SECONDS, MainActivity.this, mcallback);


                }
                else
                {
                    mobileNo.setText("");
                    mobileNo.setHint("Invalid No");
                    mobileNo.setHintTextColor(Color.RED);
                }
            }
        });
        mcallback =new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
                Toast.makeText(MainActivity.this, "Done"+phoneAuthCredential.getSmsCode(), Toast.LENGTH_SHORT).show();

                userSignIn(phoneAuthCredential);
            }

            @Override
            public void onVerificationFailed(FirebaseException e) {
                Log.e("TAG", "onVerificationFailed: "+e.getMessage() );
            }

            @Override
            public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                Log.e("TAG", "onCodeSent: Doosre ke phone number pe"+s);
                mVerificationId=s;
                mResendToken=forceResendingToken;
                linearLayoutMain2.setVisibility(View.GONE);
                mainVerificationCode.setVisibility(View.VISIBLE);
                goButton.setVisibility(View.GONE);
                mainNext.setVisibility(View.VISIBLE);

                //super.onCodeSent(s, forceResendingToken);
            }

            @Override
            public void onCodeAutoRetrievalTimeOut(String s) {
                super.onCodeAutoRetrievalTimeOut(s);
            }


        };
        mainNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mainVerificationCode.getText().toString()!=null)
                {
                    credential=PhoneAuthProvider.getCredential(mVerificationId,mainVerificationCode.getText().toString());
                    userSignIn(credential);
                }
                else
                {
                    Toast.makeText(MainActivity.this, "Enter the Verification Code", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }


    private void userSignIn(PhoneAuthCredential phoneAuthCredential){
        mAuth.signInWithCredential(phoneAuthCredential).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    Toast.makeText(MainActivity.this, "Registration Successfull ", Toast.LENGTH_SHORT).show();
                   // Intent i=new Intent(MainActivity.this,Registration.class);
                    Intent i=new Intent(MainActivity.this,MainPageActivity.class);
                    i.putExtra("MobileNo",mobNo);
                    startActivity(i);

                }
                else
                {
                    Toast.makeText(MainActivity.this, "Registraion Unsuccessfull ", Toast.LENGTH_SHORT).show();
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