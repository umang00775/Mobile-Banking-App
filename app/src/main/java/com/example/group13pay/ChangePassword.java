package com.example.group13pay;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ChangePassword extends AppCompatActivity{

    private TextInputLayout regPassword;
    private Button saveNewPassword;
    private DatabaseReference reference;
    private String username;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_change_password);

        //Receiving Username
        Intent intent = getIntent();
        username = intent.getStringExtra("username");

        //Hooks
        regPassword = findViewById(R.id.input_new_password);
        saveNewPassword = findViewById(R.id.new_password_set);

        //Set New Password
        saveNewPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Validate New Password
                Validator validator = new ValidateInput();
                boolean a = validator.validatePassword(regPassword);

                //Now Some Work With DB :)
                if (a){
                    reference = FirebaseDatabase.getInstance().getReference("Users");
                    reference.child(username).child("password").setValue(regPassword.getEditText().getText().toString());
                    Toast.makeText(getApplicationContext(), "Password Changed Successfully", Toast.LENGTH_SHORT).show();
                    Intent intent1 = new Intent(getApplicationContext(), LogIn.class);
                    startActivity(intent1);
                }
            }
        });
    }
}