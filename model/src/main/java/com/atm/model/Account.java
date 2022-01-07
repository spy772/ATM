package com.atm.model;

public class Account {

    public Account(int clientId, double balance, AccountTypes accountType, int numOfTransactions) {
        this.clientId = clientId;
        this.balance = balance;
        this.accountType = accountType;
        this.numOfTransactions = numOfTransactions;
    }

    public Account() {

    }

    private int clientId;
    private int accountId;
    private double balance;
    private AccountTypes accountType;
    private int numOfTransactions;

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public AccountTypes getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountTypes accountType) {
        this.accountType = accountType;
    }

    public int getNumOfTransactions() { // TODO: Implement numOfTransactions from here once the new data model is fully and safely implemented (a few days' time)
        return numOfTransactions;
    }

    public void setNumOfTransactions(int numOfTransactions) { // TODO: Implement numOfTransactions from here once the new data model is fully and safely implemented (a few days' time)
        this.numOfTransactions = numOfTransactions;
    }
}
