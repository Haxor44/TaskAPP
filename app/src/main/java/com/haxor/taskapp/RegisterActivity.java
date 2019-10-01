package com.haxor.taskapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    db dbs;
    EditText e1, p1, p2;
    Button b1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        dbs = new db(this);
        e1 = (EditText) findViewById(R.id.emails);
        p1= (EditText)findViewById(R.id.pass);
        p2 =(EditText) findViewById(R.id.cpass);
        b1 = (Button)findViewById(R.id.reg);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mail = e1.getText().toString();
                String passwords = p1.getText().toString();
                String cpasswords = p2.getText().toString();

                Boolean register = dbs.addUser(mail, passwords);
                if (register==true){
                    //Toast.makeText(getApplicationContext(), "Registered successfully!!!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), Login.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(getApplicationContext(), "Registration failed", Toast.LENGTH_SHORT).show();
                }
                }



        });


    }
}
