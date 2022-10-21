package com.example.group13pay;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class SignUp extends AppCompatActivity {

    private TextInputLayout regName, regPhone, regMail, regAccNo, regPassword,regUsername;
    private Button regButton,logInButton;
    private FirebaseDatabase rootNode;
    private DatabaseReference reference;
    protected String name,phone,mail,accNo,password,username;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        //Hooks with XML's ID
        regName = findViewById(R.id.fullName);
        regPhone = findViewById(R.id.phoneNumber);
        regMail = findViewById(R.id.email);
        regAccNo = findViewById(R.id.accountNumber);
        regPassword = findViewById(R.id.password);
        regButton = findViewById(R.id.reg_button);
        regUsername = findViewById(R.id.userName);
        logInButton = findViewById(R.id.login_button);


        regButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rootNode = FirebaseDatabase.getInstance();
                reference = rootNode.getReference("Users");

                //Get Values From User
                name = regName.getEditText().getText().toString();
                phone = regPhone.getEditText().getText().toString();
                mail = regMail.getEditText().getText().toString();
                accNo = regAccNo.getEditText().getText().toString();
                password = regPassword.getEditText().getText().toString();
                username = regUsername.getEditText().getText().toString();

                //Check Enered Input Valid Or Not
                Validator validator = new ValidateInput();
                boolean a = validator.validateName(regName);
                boolean b = validator.validatePhone(regPhone);
                boolean c = validator.validateMail(regMail);
                boolean d = validator.validateAccNo(regAccNo);
                boolean e = validator.validatePassword(regPassword);
                boolean f = validator.validateUsername(regUsername);


                Calendar calendar = Calendar.getInstance();
                String[] monthName={"Jan","Feb","March", "April", "May", "June", "July",
                        "Aug", "Sept", "Oct", "Nov",
                        "Dec"};
                String month=monthName[calendar.get(Calendar.MONTH)];
                int yearINT= calendar.get(Calendar.YEAR);
                int dateINT= calendar.get(Calendar.DATE);

                String year = String.valueOf(yearINT);
                String date = String.valueOf(dateINT);
                String join_date = date + " " + month + " " +  year;

                if (a && b && c && d && e && f){
                    UserHelper userHelper = new UserHelper(name, phone, mail, accNo, password, "50000", username,"0","0","0",join_date);
                    reference.child(username).setValue(userHelper);
                    Intent intent = new Intent(SignUp.this, Dashboard.class);

                    intent.putExtra("username",username);
                    intent.putExtra("full_name",name);
                    intent.putExtra("phone_no",phone);
                    intent.putExtra("account_number",accNo);
                    intent.putExtra("email",mail);
                    intent.putExtra("joined",join_date);
                    intent.putExtra("balance", "50000");
                    intent.putExtra("haveGold", "0");
                    intent.putExtra("haveSilver", "0");
                    intent.putExtra("havePlatinum", "0");
                    startActivity(intent);
                }
            }
        });
        logInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LogIn.class);
                startActivity(intent);
            }
        });
    }
}