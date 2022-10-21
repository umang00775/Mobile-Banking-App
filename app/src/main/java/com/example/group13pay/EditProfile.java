package com.example.group13pay;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class EditProfile extends AppCompatActivity{

    TextInputLayout regName, regPhone, regPassword;
    DatabaseReference reference;
    Button saveInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_edit_profile);

        //Get Some Essential Details
        Intent intent = getIntent();
        String USERNAME = intent.getStringExtra("username").trim();

        //Hooks
        regName =findViewById(R.id.fullName);
        regPhone = findViewById(R.id.mobileNumber);
        regPassword = findViewById(R.id.password);
        saveInfo = findViewById(R.id.update);


        //Set Onclick
        saveInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Validating Inputs
                Validator validator = new ValidateInput();
                boolean a = validator.validateName(regName);
                boolean b = validator.validatePassword(regPassword);
                boolean c = validator.validateUsername(regPhone);

                if (a && b && c){

                    // Override OLD data In Database
                    reference = FirebaseDatabase.getInstance().getReference("Users");
                    reference.child(USERNAME).child("password").setValue(regPassword.getEditText().getText().toString());
                    reference.child(USERNAME).child("name").setValue(regName.getEditText().getText().toString());
                    reference.child(USERNAME).child("phone").setValue(regPhone.getEditText().getText().toString());


                    Query checkUser = reference.orderByChild("username").equalTo(USERNAME);
                    checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if (snapshot.exists()){

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
                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                        }
                    });
                }
            }
        });
    }
}