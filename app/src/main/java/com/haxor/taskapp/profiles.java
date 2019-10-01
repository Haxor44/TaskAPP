package com.haxor.taskapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class profiles extends AppCompatActivity {

    db dbs;
    EditText password1, password2;
    Button btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profiles);

        dbs = new db(this);
        password1 = (EditText) findViewById(R.id.email1);
        password2 = (EditText) findViewById(R.id.cupass1);
        btn = (Button) findViewById(R.id.button2);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String pass1 = password1.getText().toString();
                String pass2 = password2.getText().toString();

                boolean updated = dbs.updateUser(pass1, pass2);

                if (updated == true) {

                    Toast.makeText(getApplicationContext(), "Updated", Toast.LENGTH_SHORT).show();
                } else {

                    Toast.makeText(getApplicationContext(), "Not updated", Toast.LENGTH_SHORT).show();
                }
            }

        });

    }
}
