package com.example.group13pay;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class InvestMoneyInGold extends AppCompatActivity {

    private TextView have_gold, calculate, instruction, priceShow;
    private TextInputLayout userInput;
    private Button go_to_home, buy;
    private String USERNAME,HAVEGOLD,BALANCE;
    protected double value;

//    public InvestMoneyInGold(double Value){
//        this.value = Value;
//    }
//
//    public InvestMoneyInGold(String USERNAME, String HAVEGOLD,String BALANCE){
//        this.USERNAME = USERNAME;
//        this.HAVEGOLD = HAVEGOLD;
//        this.BALANCE = BALANCE;
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_invest_money_in_gold);

        //Hooks
        have_gold = findViewById(R.id.set_have_gold);
        go_to_home = findViewById(R.id.back_to_home);
        calculate = findViewById(R.id.calc_price);
        instruction = findViewById(R.id.show_instruction);
        userInput = findViewById(R.id.input);
        priceShow = findViewById(R.id.show_price);
        buy = findViewById(R.id.proceed_further);


        //User Input To Buy Gold
        String input = userInput.getEditText().getText().toString().trim();

        //Receiving Information from Previous Screen
        Intent intent = getIntent();
        USERNAME = intent.getStringExtra("username");
        HAVEGOLD = intent.getStringExtra("gold") + " g ";

        //set gold weight, how much user have
        have_gold.setText(HAVEGOLD);

        //Back To Home On Button Click
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
                        //When Connection Bad
                    }
                });
            }
        });

        //Investment Calculator Object
        InvestmentCalculator calculator = new InvestmentCalculator();

        //Show The Price To User
        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input1 = userInput.getEditText().getText().toString().trim();
                if (input1 == null){
                    priceShow.setText("Please Enter Valid Input");
                }
                else{
                    double price = calculator.price(input1);
//                    InvestMoneyInGold investMoneyInGold = new InvestMoneyInGold(price);
                    priceShow.setText(String.valueOf(price));
                }
            }
        });

        //Pbject Of Dashboard
        Dashboard dashboard = new Dashboard();

        //Finally Buy
//        buy.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String balanceString = dashboard.BALANCE;
//
//                //Required In Double
//                double balance = Double.parseDouble(balanceString);
//                if(userInput.getEditText().getText().toString().trim().isEmpty()){
//                    priceShow.setText("Enter Valid Input");
//                }
//                else if (balance < value){
//                    priceShow.setText("You Don't Have Enough Money");
//                }
//                else {
//                    //Add New Gold
//                    //Change In Double,
//                    double balanceInDouble = Double.parseDouble(BALANCE);
//                    double havegoldD = Double.parseDouble(BALANCE);
//
//                }
//            }
//        });


    }
}