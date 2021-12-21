package com.atm.services;

import com.atm.model.Client;

import java.text.NumberFormat;

public abstract class ExtendedTransactions implements Transactions {

    final public int YEARS = 30;
    final public  int PERCENT = 100;
    private double savingsInterest = 0.6 / PERCENT;
    private double checkingInterest = 0.03 / PERCENT;

    public void numberOfTransactions(Client client) {
        double afterTransaction = client.getSavingsBalance() - 500;
        if (client.getNumOfTransactions() < 6) {
            client.setNumOfTransactions(1);
            System.out.println("You have made " + client.getNumOfTransactions() + " transactions out of your maximum 6 this month.");
        } else {
            System.out.println("You have exceeded your monthly number of transactions in this client, a $500 fee will be applied");
            client.setSavingsBalance(afterTransaction);
        }
    }

    public void monthlyFunctionsSavings(Client client) {
        client.setNumOfTransactions(0);
        double afterInterest = client.getSavingsBalance() * (1 + savingsInterest * YEARS);
        client.setSavingsBalance(afterInterest);
        double afterMonthlyFee = client.getSavingsBalance() - 500;
        client.setSavingsBalance(afterMonthlyFee);

        System.out.println("You have added " + NumberFormat.getCurrencyInstance().format(afterInterest) + " to your client via interest");
        System.out.println("$500 of monthly maintenance fees have been deducted from your savings client");
        System.out.println("Your current savings balance is: " + NumberFormat.getCurrencyInstance().format(client.getSavingsBalance()));
    }

    public void monthlyFunctionsChecking(Client client) {
        double afterInterest = client.getCheckingBalance() * (1 + checkingInterest * YEARS);
        client.setCheckingBalance(afterInterest);
        double afterMonthlyFee = client.getCheckingBalance() - 500;
        client.setCheckingBalance(afterMonthlyFee);

        System.out.println("You have added " + NumberFormat.getCurrencyInstance().format(afterInterest) + " to your client via interest");
        System.out.println("$500 of monthly maintenance fees have been deducted from your checking client");
        System.out.println("Your current checking balance is: " + NumberFormat.getCurrencyInstance().format(client.getCheckingBalance()));
    }
}

