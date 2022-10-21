package com.example.group13pay;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class EnterAmount extends AppCompatActivity {
    private Button sendMoney;
    private TextInputLayout amount;
    private DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_enter_amount);

        //Receiving Initial Data
        Intent intent = getIntent();
        String username = intent.getStringExtra("username").trim();
        String user_balance = intent.getStringExtra("userBalance").trim();
        String receiver = intent.getStringExtra("receiver").trim();
        String receiver_balance =  intent.getStringExtra("receiverBalance").trim();

        //Hooks
        sendMoney = findViewById(R.id.pay);
        amount = findViewById(R.id.enter_amount);

        sendMoney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //User Input
                String amount_to_send = amount.getEditText().getText().toString().trim();
                int sendMoney = Integer.parseInt(amount_to_send.replaceAll("[\\D]",""));

                //User Balance
                int userBalance = Integer.parseInt(user_balance);

                //Receiver Balance
                int receiverBalance = Integer.parseInt(receiver_balance);

                //Checking :)
                if (sendMoney <= userBalance){

                    //Some Calculations :)
                    //Reference For Send Money
                    int newBalanceReceiver_in_int = sendMoney + receiverBalance;
                    String newBalanceReceiver = String.valueOf(newBalanceReceiver_in_int);

                    int myBalance_in_int  = userBalance - sendMoney;
                    String myBalance = String.valueOf(myBalance_in_int);

                    //Set In DB
                    reference = FirebaseDatabase.getInstance().getReference("Users");
                    reference.child(receiver).child("balance").setValue(newBalanceReceiver);
                    reference.child(username).child("balance").setValue(myBalance);

                    Intent intent1 = new Intent(getApplicationContext(), TransitionSuccessful.class);
                    intent1.putExtra("username",username);
                    startActivity(intent1);
                }
                else {
                    amount.setError("You Don't Have Enough Money");
                }
            }
        });
    }
}