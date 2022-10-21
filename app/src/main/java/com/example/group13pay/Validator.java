package com.example.group13pay;

import com.google.android.material.textfield.TextInputLayout;

//Abstract Method For Validating Input
abstract class Validator{
    //For Sign Up OR Creating Account
    abstract public boolean validateName(TextInputLayout regName);
    abstract public boolean validateUsername(TextInputLayout regUsername);
    abstract public boolean validatePhone(TextInputLayout regPhone);
    abstract public boolean validateMail(TextInputLayout regMail);
    abstract public boolean validateAccNo(TextInputLayout regAccNo);
    abstract public boolean validatePassword(TextInputLayout regPassword);
}


// Subclass Of Abstract Class Validator
class ValidateInput extends Validator{
    public boolean validateName(TextInputLayout regName){
        String value = regName.getEditText().getText().toString();
        if (value.isEmpty()){
            regName.setError("Field Cannot Be Empty");
            return false;
        }
        else {
            regName.setError(null);
            regName.setErrorEnabled(false);
            return true;
        }
    }
    public boolean validateUsername(TextInputLayout regUsername){
        String value = regUsername.getEditText().getText().toString();
        if (value.isEmpty()){
            regUsername.setError("Field Cannot Be Empty");
            return false;
        }
        else if (value.length() >= 15 || value.contains(" ")){
            regUsername.setError("Enter Valid Username");
            return false;
        }
        else {
            regUsername.setError(null);
            regUsername.setErrorEnabled(false);
            return true;
        }
    }
    public boolean validatePhone(TextInputLayout regPhone){
        String value = regPhone.getEditText().getText().toString();
        if (value.isEmpty()){
            regPhone.setError("Field Cannot Be Empty");
            return false;
        }
        else if (value.length() != 10){
            regPhone.setError("Enter Valid Number");
            return false;
        }
        else {
            regPhone.setError(null);
            return true;
        }
    }
    public boolean validateMail(TextInputLayout regMail){
        String value = regMail.getEditText().getText().toString();
        String emailPattern = "[a-zA-Z0-9._]+@[a-z]+\\.+[a-z]+";
        if (value.isEmpty()){
            regMail.setError("Field Cannot Be Empty");
            return false;
        }
        else if (!value.matches(emailPattern)){
            regMail.setError("Invalid Email Address");
            return  false;
        }
        else {
            regMail.setError(null);
            return true;
        }
    }
    public boolean validateAccNo(TextInputLayout regAccNo){
        String value = regAccNo.getEditText().getText().toString();
        if (value.isEmpty()){
            regAccNo.setError("Field Cannot Be Empty");
            return false;
        }
        else if (value.length() != 10){
            regAccNo.setError("Enter Valid Number");
            return false;
        }
        else {
            regAccNo.setError(null);
            return true;
        }
    }
    public boolean validatePassword(TextInputLayout regPassword){
        String value = regPassword.getEditText().getText().toString();
        String passwordPattern = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{8,20}$" ;
        if (value.isEmpty()){
            regPassword.setError("Field Cannot Be Empty");
            return false;
        }
        else if (!value.matches(passwordPattern)){
            regPassword.setError("Password is too weak");
            return  false;
        }
        else {
            regPassword.setError(null);
            return true;
        }
    }
}