package com.haxor.taskapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    db dbs;
    EditText email;
    EditText pass;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        dbs = new db(this);
        email = findViewById(R.id.email);
        pass = findViewById(R.id.password);
        btn = findViewById(R.id.button);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email1 = email.getText().toString();
                String pass1 = pass.getText().toString();
                Boolean Chkcreds = dbs.login(email1,pass1);
                if (Chkcreds){
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                }

                else{

                    Toast.makeText(getApplicationContext(), "Login failed!!!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
