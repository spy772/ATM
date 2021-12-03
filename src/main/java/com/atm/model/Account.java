package com.atm.model;

public class Account {

    private double bankBalance;
    private double checkingBalance;
    private int numOfTransactions;
    private double savingsBalance;

    public double getBankBalance() {
        return bankBalance;
    }

    public void setBankBalance(double bankBalance) {
        this.bankBalance = bankBalance;
    }

    public double getCheckingBalance() {
        return checkingBalance;
    }

    public void setCheckingBalance(double checkingBalance) {
        this.checkingBalance = checkingBalance;
    }

    public int getNumOfTransactions() {
        return numOfTransactions;
    }

    public void setNumOfTransactions(int numOfTransactions) {
        this.numOfTransactions = numOfTransactions;
    }

    public double getSavingsBalance() {
        return savingsBalance;
    }

    public void setSavingsBalance(double savingsBalance) {
        this.savingsBalance = savingsBalance;
    }
}
