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

public class TransferMoneyWithAccount extends AppCompatActivity {

    private Button pay;   //Onclick -> Paying To User
    private TextInputLayout sendTo;  //Get Input Account Number

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_transfer_money_with_account);

        //Hooks
        pay = findViewById(R.id.payMoney);
        sendTo = findViewById(R.id.username_input_for_sending);

        //Receiver Username
        String receiver = sendTo.getEditText().getText().toString();

        //Get Self Username
        Intent intent = getIntent();
        String username = intent.getStringExtra("username");

        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //----------------------------

                final String RECEIVER_ACCOUNT = sendTo.getEditText().getText().toString().trim();

                DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users");
                Query checkUser = reference.orderByChild("accNo").equalTo(RECEIVER_ACCOUNT);

                checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()){


                            final String RECEIVER_USERNAME = snapshot.getKey();

                            String receiver_balace = snapshot.child(RECEIVER_USERNAME).child("balance").getValue(String.class);
                            String user_balace = snapshot.child(RECEIVER_USERNAME).child("balance").getValue(String.class);

                            Intent intent1 = new Intent(getApplicationContext(),EnterAmount.class);
                            intent1.putExtra("username",username); //Self
                            intent1.putExtra("userBalance",user_balace);
                            intent1.putExtra("receiver", RECEIVER_USERNAME); //receiver username
                            intent1.putExtra("receiver_balance", receiver_balace);
                            startActivity(intent1);
                        }
                        else {
                            sendTo.setError("No Such User Exists");
                            sendTo.requestFocus();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }
        });
    }
}