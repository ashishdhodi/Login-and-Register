package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    private EditText Uname;
    private EditText Password;
    private Button Login;
    private Button Register;
    private TextView Attempts;
    private int count = 3;


    DatabaseHelper dbhelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbhelper=new DatabaseHelper(this);//Takes only one arg Context
        //Call the constructor of the class to create the db

        Uname = findViewById(R.id.etName);
        Password = findViewById(R.id.etPassword);
        Login =  findViewById(R.id.signin);
        Attempts =  findViewById(R.id.tvAttempts);
        Register = findViewById(R.id.register);

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateDb(Uname.getText().toString(),Password.getText().toString());
            }
        });

        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register();
            }
        });


    }

   /* private void validate(String userName, String password)
    {
        if((userName.equals("Admin")) && (password.equals("12345")))
        {
            Intent intent = new Intent(MainActivity.this, SecondActivity.class);
            startActivity(intent);
        }
        else
        {

        }
    }*/

    private void validateDb(String username,String password)
    {
       boolean isExists = dbhelper.authenticate(Password.getText().toString(),Uname.getText().toString());
       if(isExists)
       {
           Intent intent = new Intent(MainActivity.this,SecondActivity.class);
           intent.putExtra("username",Uname.getText().toString());
           startActivity(intent);
       }
       else
       {
           Password.setText(null);
           count--;

           Attempts.setText("Attempts : "+ count);

           if(count == 0)
           {
               Login.setEnabled(false);
           }
           Toast.makeText(MainActivity.this,"Login Failed. Invalid UserName or Password",Toast.LENGTH_SHORT).show();
       }
    }

    private void register()
    {
        Intent intent = new Intent(MainActivity.this,Register.class);
        startActivity(intent);
    }



}
