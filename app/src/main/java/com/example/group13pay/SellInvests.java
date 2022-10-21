package com.example.group13pay;

//All Prices
interface SellPrices{
    //Price, Per 10g
    public double gold = 49635;
    public double silver = 644;
    public double platinum = 23760;
}

public class SellInvests implements SellPrices{
    public double price(String w){
        double weight = Double.parseDouble(w);
        //Adding 3% Profit
        //e.g. : Original = gold*weight/10  + new gold*weight/300(3% Profit For Investing)
        return ( gold*weight/10 + gold*weight*0.03 ) ;
    }
    public double price(float weight){
        return ( silver*weight/10 + silver*weight*0.03 );
    }
    public double price(double weight){
        return ( platinum*weight/10 + platinum*weight*0.03 );
    }
}
