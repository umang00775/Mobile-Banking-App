package com.example.group13pay;

public class UserHelper {
    private String name, phone, email, accNo, username, joinDate,haveGold, haveSilver, havePlatinum, password,balance,history;

    public UserHelper(String name, String phone,
                      String email, String accNo,
                      String password, String balance,
                      String username, String haveGold,
                      String haveSilver, String havePlatinum,
                      String joinDate){
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.accNo = accNo;
        this.password = password;
        this.balance = balance;
        this.username = username;
        this.haveGold = haveGold;
        this.haveSilver = haveSilver;
        this.havePlatinum = havePlatinum;
        this.joinDate = joinDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAccNo() {
        return accNo;
    }

    public void setAccNo(String accNo) {
        this.accNo = accNo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getHaveGold() {
        return haveGold;
    }

    public void setHaveGold(String haveGold) {
        this.haveGold = haveGold;
    }

    public String getHaveSilver() {
        return haveSilver;
    }

    public void setHaveSilver(String haveSilver) {
        this.haveSilver = haveSilver;
    }

    public String getHavePlatinum() {
        return havePlatinum;
    }

    public void setHavePlatinum(String havePlatinum) {
        this.havePlatinum = havePlatinum;
    }

    public String getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(String joinDate) {
        this.joinDate = joinDate;
    }
}