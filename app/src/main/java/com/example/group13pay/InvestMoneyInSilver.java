package com.example.group13pay;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class InvestMoneyInSilver extends AppCompatActivity {

    TextView have_silver, calculate, instruction, priceShow;
    TextInputLayout userInput;
    Button go_to_home;
    protected double value;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invest_money_in_silver);

        //Hooks
        have_silver = findViewById(R.id.set_have_silver);
        go_to_home = findViewById(R.id.back_to_home);
        calculate = findViewById(R.id.calc_price);
        instruction = findViewById(R.id.show_instruction);
        userInput = findViewById(R.id.input);
        priceShow = findViewById(R.id.show_price);


        //Receiving Information  //--------------
        Intent intent = getIntent();
        String USERNAME = intent.getStringExtra("username");
        String HAVESILVER = intent.getStringExtra("silver") + "g ";

        //User Input To Buy Silver
        String input = userInput.getEditText().getText().toString();


        //Set Text, How Much User Have
        have_silver.setText(HAVESILVER);

        //Go Back To Home
        go_to_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users");
                Query checkUser = reference.orderByChild("username").equalTo(USERNAME);
                checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()){
                            //Fetching Information From Database
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

                            Intent intent1 = new Intent(getApplicationContext(), Dashboard.class);
                            intent1.putExtra("username",usernameDB);
                            intent1.putExtra("full_name",full_nameDB);
                            intent1.putExtra("phone_no",phone_noDB);
                            intent1.putExtra("account_number",account_numberDB);
                            intent1.putExtra("email",emailDB);
                            intent1.putExtra("joined",joinedDB);
                            intent1.putExtra("balance", balance);
                            intent1.putExtra("haveGold", haveGold);
                            intent1.putExtra("haveSilver", haveSilver);
                            intent1.putExtra("havePlatinum", havePlatinum);
                            startActivity(intent1);

                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });

        ///Investment Calculator Object
        InvestmentCalculator calculator = new InvestmentCalculator();

        //Show The Price To User
        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input = userInput.getEditText().getText().toString().trim();
                int input1 = Integer.parseInt(input);
                if (input == null){
                    priceShow.setText("Error");
                }
                else{
                    double price = calculator.price(input1);
                    priceShow.setText(String.valueOf(price));
                }
            }
        });

    }
}