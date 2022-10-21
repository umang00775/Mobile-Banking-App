package com.example.group13pay;

class Prices{
    //Price, Per 10g
    protected double gold = 49635;
    protected double silver = 644;
    protected double platinum = 23760;
}

public class InvestmentCalculator extends Prices{
    // For Gold
    public double price(String w){
        double weight = Double.parseDouble(w);
        return gold*weight/10 ;
    }

    //For Silver
    public double price(int weight){
        return silver*weight/10 ;
    }

    //For Platinum
    public double price(double weight){
        return platinum*weight/10 ;
    }
}
