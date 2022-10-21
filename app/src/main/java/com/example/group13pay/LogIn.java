package com.example.group13pay;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class LogIn extends AppCompatActivity{

    //Variables
    private Button signUp, logIn,forgotPass;
    private TextInputLayout enteredUsername, enteredPassword;



    private boolean validateUsername(TextInputLayout enteredUsername){
        String value = enteredUsername.getEditText().getText().toString();
        if (value.isEmpty()){
            enteredUsername.setError("Field Cannot Be Empty");
            return false;
        }
        else {
            enteredUsername.setError(null);
            enteredUsername.setErrorEnabled(false);
            return true;
        }
    }
    private boolean validatePassword(TextInputLayout enteredPassword){
        String value = enteredPassword.getEditText().getText().toString();
        if (value.isEmpty()){
            enteredPassword.setError("Field Cannot Be Empty");
            return false;
        }
        else {
            enteredPassword.setError(null);
            enteredPassword.setErrorEnabled(false);
            return true;
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_log_in);

        //Hooks
        logIn = findViewById(R.id.login_button);
        signUp = findViewById(R.id.create_acc);
        forgotPass = findViewById(R.id.forgot_password);
        enteredUsername = findViewById(R.id.userNameInput);
        enteredPassword = findViewById(R.id.userPasswordInput);


        //Create Log In
        logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                LogIn logIn = new LogIn();
                boolean a = logIn.validateUsername(enteredUsername);
                boolean b = logIn.validatePassword(enteredPassword);



                if (a && b){
                    final String USERNAME = enteredUsername.getEditText().getText().toString().trim();
                    final String USERPASSWORD = enteredPassword.getEditText().getText().toString().trim();

                    DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users");
                    Query checkUser = reference.orderByChild("username").equalTo(USERNAME);

                    checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if (snapshot.exists()){
                                String passwordFromDB = snapshot.child(USERNAME).child("password").getValue(String.class);
                                if (passwordFromDB.equals(USERPASSWORD)){

                                    enteredPassword.setError(null);
                                    enteredUsername.setError(null);
                                    enteredUsername.setErrorEnabled(false);
                                    enteredPassword.setErrorEnabled(false);

                                    String full_nameDB = snapshot.child(USERNAME).child("name").getValue(String.class);
                                    String usernameDB = snapshot.child(USERNAME).child("username").getValue(String.class);
                                    String phone_noDB = snapshot.child(USERNAME).child("phone").getValue(String.class);
                                    String account_numberDB = snapshot.child(USERNAME).child("accNo").getValue(String.class);
                                    String emailDB = snapshot.child(USERNAME).child("email").getValue(String.class);
                                    String joinedDB = snapshot.child(USERNAME).child("joinDate").getValue(String.class);

                                    String balance = snapshot.child(USERNAME).child("balance").getValue(String.class);
                                    String haveGold = snapshot.child(USERNAME).child("haveGold").getValue(String.class);
                                    String haveSilver = snapshot.child(USERNAME).child("haveSilver").getValue(String.class);
                                    String havePlatinum = snapshot.child(USERNAME).child("havePlatinum").getValue(String.class);

                                    Toast.makeText(getApplicationContext(), "Log In Successful", Toast.LENGTH_SHORT).show();

                                    Intent intent = new Intent(getApplicationContext(), Dashboard.class);
                                    intent.putExtra("username",usernameDB);
                                    intent.putExtra("full_name",full_nameDB);
                                    intent.putExtra("phone_no",phone_noDB);
                                    intent.putExtra("account_number",account_numberDB);
                                    intent.putExtra("email",emailDB);
                                    intent.putExtra("joined",joinedDB);
                                    intent.putExtra("balance", balance);
                                    intent.putExtra("haveGold", haveGold);
                                    intent.putExtra("haveSilver", haveSilver);
                                    intent.putExtra("havePlatinum", havePlatinum);
                                    startActivity(intent);
                                }
                                else {
                                    enteredPassword.setError("Wrong Password");
                                    enteredPassword.requestFocus();
                                }
                            }
                            else {
                                enteredUsername.setError("No Such User Exists");
                                enteredUsername.requestFocus();
                            }
                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                        }
                    });
                }
            }
        });

        forgotPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ForgotPassword.class);
                startActivity(intent);
            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LogIn.this, SignUp.class);
                startActivity(intent);
            }
        });
    }
}