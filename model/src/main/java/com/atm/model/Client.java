package com.atm.model;

public class Client {

    private int clientId;
    private int accountId;
    private double bankBalance;
    private double savingsBalance;
    private double checkingBalance;
    private int numOfTransactions;

    public int getClientId() { return clientId; }

    public void setClientId(int clientId) { this.clientId = clientId; }

    public int getAccountId() { return accountId; }

    public void setAccountId(int accountId) { this.accountId = accountId; }

    public double getBankBalance() {
        return bankBalance;
    }

    public void setBankBalance(double bankBalance) {
        this.bankBalance = bankBalance;
    }

    public double getSavingsBalance() {
        return savingsBalance;
    }

    public void setSavingsBalance(double savingsBalance) {
        this.savingsBalance = savingsBalance;
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

}