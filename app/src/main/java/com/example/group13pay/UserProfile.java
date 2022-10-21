package com.example.group13pay;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class UserProfile extends AppCompatActivity {

    TextView name1, name2, username,mobile, account, email, show_joined;
    Button editProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        //Hooks
        name1 = findViewById(R.id.full_name);
        name2 = findViewById(R.id.full_name_1);
        username = findViewById(R.id.user_name);
        mobile = findViewById(R.id.mobile_number);
        account = findViewById(R.id.account_number);
        email = findViewById(R.id.e_mail);
        show_joined = findViewById(R.id.joined_date);
        editProfile = findViewById(R.id.edit_profile_details);

        //Receiving Information
        Intent intent = getIntent();
        String FULLNAME = intent.getStringExtra("full_name");
        String USERNAME = "@" + intent.getStringExtra("username");
        String PHONE = intent.getStringExtra("phone_no");
        String ACCOUNT = intent.getStringExtra("account_number");
        String EMAIL = intent.getStringExtra("email");
        String JOINED = "Since " + intent.getStringExtra("joined");

        name1.setText(FULLNAME);
        name2.setText(FULLNAME);
        username.setText(USERNAME);
        mobile.setText(PHONE);
        account.setText(ACCOUNT);
        email.setText(EMAIL);
        show_joined.setText(JOINED);

        //Edit Profile
        editProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(getApplicationContext(), EditProfile.class);
                intent1.putExtra("username",intent.getStringExtra("username"));
                startActivity(intent1);
            }
        });
    }
}