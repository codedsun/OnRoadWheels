package com.example.suneet.onroadwheels;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

    EditText mobileNo;
    Button goButton;
    String mobNo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mobileNo= (EditText) findViewById(R.id.mobileNo);
        goButton= (Button) findViewById(R.id.buttonGo);
        goButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!((mobileNo.getText().toString().length())==0)) {

                    mobNo = mobileNo.getText().toString();
                    Intent i=new Intent(MainActivity.this,Registration.class);
                    i.putExtra("MobileNo",mobNo);
                    startActivity(i);
                }
                else
                {
                    Toast.makeText(MainActivity.this,"Enter the Mobile No",Toast.LENGTH_SHORT).show();
                }


            }
        });

    }
}
