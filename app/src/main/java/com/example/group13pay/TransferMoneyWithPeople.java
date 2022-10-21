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

public class TransferMoneyWithPeople extends AppCompatActivity {

    private Button pay, back_to_home;
    private TextInputLayout sendTo;
    private String RECEIVER_USERNAME,SELF_USERNAME;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_transfer_money_with_people);

        //Hooks
        pay = findViewById(R.id.payMoney);
        sendTo = findViewById(R.id.username_input_for_sending);

        //Get Self Username
        Intent intent = getIntent();
        String username = intent.getStringExtra("username");
        String user_balance = intent.getStringExtra("balance"); //self balance

        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //----------------------------
                RECEIVER_USERNAME = sendTo.getEditText().getText().toString().trim();
                SELF_USERNAME = username.trim();

                DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users");
                Query checkUser = reference.orderByChild("username").equalTo(RECEIVER_USERNAME);

                checkUser.addListenerForSingleValueEvent(new ValueEventListener(){
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()){
                            String receiver_balance = snapshot.child(RECEIVER_USERNAME).child("balance").getValue(String.class);
                            Intent intent1 = new Intent(getApplicationContext(),EnterAmount.class);
                            intent1.putExtra("username",SELF_USERNAME); //Self
                            intent1.putExtra("userBalance",user_balance);
                            intent1.putExtra("receiver", RECEIVER_USERNAME); //receiver username
                            intent1.putExtra("receiverBalance", receiver_balance);
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