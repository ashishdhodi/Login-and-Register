package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Register extends AppCompatActivity {

    DatabaseHelper dbhelper;
    private Button Submit;
    private EditText Name;
    private EditText Password;
    private EditText ConfirmPassword;
    private EditText Email;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Submit = findViewById(R.id.rgRegister);
        Name = findViewById(R.id.rgName);
        Password =findViewById(R.id.rgPass);
        ConfirmPassword=findViewById(R.id.rgConfirmPass);
        Email = findViewById(R.id.rgEmail);

        dbhelper=new DatabaseHelper(this);


        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnRegister();
            }
        });


    }

    private void btnRegister()
    {
        boolean ins=dbhelper.insertData(Name.getText().toString(),Password.getText().toString(),Email.getText().toString());
        if(ins) {
            Toast.makeText(getApplicationContext(), "Registration Successful", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(getApplicationContext(),"Not Successful",Toast.LENGTH_SHORT).show();
        }
    }


}
