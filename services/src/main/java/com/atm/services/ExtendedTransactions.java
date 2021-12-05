package com.atm.services;

import com.atm.model.Account;

import java.text.NumberFormat;

public abstract class ExtendedTransactions implements Transactions {

    final public int YEARS = 30;
    final public  int PERCENT = 100;
    private double savingsInterest = 0.6 / PERCENT;
    private double checkingInterest = 0.03 / PERCENT;

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

    public void monthlyFunctionsSavings(Account account) {
        account.setNumOfTransactions(0);
        double afterInterest = account.getSavingsBalance() * (1 + savingsInterest * YEARS);
        account.setSavingsBalance(afterInterest);
        double afterMonthlyFee = account.getSavingsBalance() - 500;
        account.setSavingsBalance(afterMonthlyFee);

        System.out.println("You have added " + NumberFormat.getCurrencyInstance().format(afterInterest) + " to your account via interest");
        System.out.println("$500 of monthly maintenance fees have been deducted from your savings account");
        System.out.println("Your current savings balance is: " + NumberFormat.getCurrencyInstance().format(account.getSavingsBalance()));
    }

    public void monthlyFunctionsChecking(Account account) {
        double afterInterest = account.getCheckingBalance() * (1 + checkingInterest * YEARS);
        account.setCheckingBalance(afterInterest);
        double afterMonthlyFee = account.getCheckingBalance() - 500;
        account.setCheckingBalance(afterMonthlyFee);

        System.out.println("You have added " + NumberFormat.getCurrencyInstance().format(afterInterest) + " to your account via interest");
        System.out.println("$500 of monthly maintenance fees have been deducted from your checking account");
        System.out.println("Your current checking balance is: " + NumberFormat.getCurrencyInstance().format(account.getCheckingBalance()));
    }
}

