package com.example.group13pay;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.TextView;

public class CheckBalance extends AppCompatActivity {

    TextView accountNo, money;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_check_balance);
        //Hooks
        accountNo = findViewById(R.id.set_account_last_four_digit);
        money = findViewById(R.id.set_account_balance);
        //Receiving Data
        Intent intent = getIntent();
        String ACCOUNT_NO = intent.getStringExtra("account_number");
        String BALANCE = intent.getStringExtra("balance");
        accountNo.setText(ACCOUNT_NO);
        money.setText(BALANCE);
    }
}