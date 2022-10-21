package com.example.group13pay;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

public class Dashboard extends AppCompatActivity {

    private ImageView logoImage;
    private ImageView transferWithPeople,transferWithAccount,transferWithPhone,investMoneyInGold, investMoneyInSilver,investMoneyInPlatinum, profile;
    private TextView checkBalance,seeAccountHistory, findNearestATMfromUser;
    String FULLNAME,USERNAME,PHONE,EMAIL,ACCOUNT,JOINED,BALANCE;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_dashboard);

        //Creating Hooks For Each Touchable Element
        logoImage = findViewById(R.id.logo_image);
        transferWithPeople = findViewById(R.id.transferPeople);
        transferWithAccount = findViewById(R.id.transferAccount);
        transferWithPhone = findViewById(R.id.transferPhone);
        investMoneyInGold = findViewById(R.id.investInGold);
        investMoneyInSilver = findViewById(R.id.investInSilver);
        investMoneyInPlatinum = findViewById(R.id.investInPlatinum);
        profile = findViewById(R.id.profile_picture);
        checkBalance = findViewById(R.id.checkAccountBalance);
        seeAccountHistory = findViewById(R.id.viewAccountHistory);
        findNearestATMfromUser = findViewById(R.id.findNearestATM);


        //Receiving Data For Showing Profile;
        Intent intent = getIntent();
        FULLNAME = intent.getStringExtra("full_name");
        USERNAME = intent.getStringExtra("username");
        PHONE = intent.getStringExtra("phone_no");
        ACCOUNT = intent.getStringExtra("account_number");
        EMAIL = intent.getStringExtra("email");
        BALANCE = intent.getStringExtra("balance");
        JOINED = intent.getStringExtra("joined");

        String GOLD = intent.getStringExtra("haveGold");
        String SILVER = intent.getStringExtra("haveSilver");
        String PLATINUM = intent.getStringExtra("havePlatinum");


        //Profile Open
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), UserProfile.class);
                intent.putExtra("username",USERNAME);
                intent.putExtra("full_name",FULLNAME);
                intent.putExtra("phone_no",PHONE);
                intent.putExtra("account_number",ACCOUNT);
                intent.putExtra("email",EMAIL);
                intent.putExtra("joined", JOINED);
                startActivity(intent);
            }
        });

        //Onclick Logo (About Page)
        logoImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(getApplicationContext(), AboutUs.class);
                startActivity(intent1);
            }
        });


        //Transfer Money
        transferWithPeople.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), TransferMoneyWithPeople.class);
                intent.putExtra("username", USERNAME);  //Self
                intent.putExtra("balance",BALANCE); //User Balance
                startActivity(intent);
            }
        });
        transferWithPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), TransferMoneyWithPeople.class);
                intent.putExtra("username",USERNAME);
                intent.putExtra("balance",BALANCE); //User Balance
                startActivity(intent);
            }
        });
        transferWithAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), TransferMoneyWithPeople.class);
                intent.putExtra("username",USERNAME);
                intent.putExtra("balance",BALANCE); //User Balance
                startActivity(intent);
            }
        });


        //Invest Money
        investMoneyInGold.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), InvestMoneyInGold.class);
                intent.putExtra("username",USERNAME);
                intent.putExtra("gold", GOLD);
                startActivity(intent);
            }
        });

        investMoneyInSilver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), InvestMoneyInSilver.class);
                intent.putExtra("username",USERNAME);
                intent.putExtra("silver", SILVER);
                startActivity(intent);
            }
        });

        investMoneyInPlatinum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), InvestMoneyInPlatinum.class);
                intent.putExtra("username",USERNAME);
                intent.putExtra("platinum", PLATINUM);
                startActivity(intent);
            }
        });

        //Check Balance Function
        checkBalance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CheckBalance.class);
                intent.putExtra("account_number",ACCOUNT);
                intent.putExtra("balance", BALANCE);
                startActivity(intent);
            }
        });
    }
}