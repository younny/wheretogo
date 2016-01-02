package com.doo.study.dytransit.POJO;

/**
 * Created by dooyoungki on 12/30/15.
 */
public class Price {
    private String currency;
    private int amount;

    public Price(int amount, String currency){
        this.amount = amount;
        this.currency = currency;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "price: "+amount+" "+currency;
    }
}
