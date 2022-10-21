package com.example.group13pay;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class PayMoneyForInvesting extends AppCompatActivity {

    TextView showMoney;
    Button payMoney;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_pay_money_for_investing);

        //Hooks
        showMoney = findViewById(R.id.have_to_pay);
        payMoney = findViewById(R.id.pay_money);

        //Getting Values From Previous Page
        Intent intent = getIntent();
        String username = intent.getStringExtra("username");
        String required_money_string = intent.getStringExtra("price");
        String keySTRING = intent.getStringExtra("key");
        String weightSTRING = intent.getStringExtra("weight");


        //Required Info In Integer
        int required_money = Integer.parseInt(required_money_string);


        //Set Value To TV
        showMoney.setText(required_money);

        payMoney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
    }
}