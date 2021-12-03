package com.atm;

import com.atm.model.Account;

import java.text.NumberFormat;

public abstract class ExtendedTransactions implements Transactions {

    public int numOfTransactions;
    public double savingsBalance;
    public double savingsInterest = 0.6 / PERCENT;

    public double checkingBalance;
    public double checkingInterest = 0.03 / PERCENT;

    public void numberOfTransactions(Account account) {
        double afterTransaction = account.getSavingsBalance() - 500;
        if (account.getNumOfTransactions() < 6) {
            account.setNumOfTransactions(1);
            System.out.println("You have made " + account.getNumOfTransactions() + " transactions out of your maximum 6 this month.");
        } else {
            System.out.println("You have exceeded your monthly number of transactions in this account, a $500 fee will be applied");
            account.setSavingsBalance(afterTransaction);
        }
    }

    public void monthlyFunctionsSavings() {
        numOfTransactions = 0;
        savingsBalance = savingsBalance * (1 + savingsInterest * YEARS);
        double calculatedInterest = savingsInterest * (1 + (savingsInterest * YEARS));
        savingsBalance = savingsBalance - 500;

        System.out.println("You have added " + NumberFormat.getCurrencyInstance().format(calculatedInterest) + " to your account via interest");
        System.out.println("$500 of monthly maintenance fees have been deducted from your account");
        System.out.println("Your current balance is: " + NumberFormat.getCurrencyInstance().format(savingsBalance));
    }

    public void monthlyFunctionsChecking() {
        checkingBalance = checkingBalance * (1 + checkingInterest * YEARS);
        double calculatedInterest = checkingBalance * (1 + (checkingInterest * YEARS));
        checkingBalance = checkingBalance - 500;

        System.out.println("You have added " + NumberFormat.getCurrencyInstance().format(calculatedInterest) + " to your account via interest");
        System.out.println("$500 of monthly maintenance fees have been deducted from your account");
        System.out.println("Your current balance is: " + NumberFormat.getCurrencyInstance().format(checkingBalance));
    }
}

