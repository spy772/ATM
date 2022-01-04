package com.atm.model;

import java.util.List;

public class Client {

    private int clientId;
    private int accountId;
    private double bankBalance; // TODO: Remove bankBalance from here once the new data model is fully and safely implemented (a few days' time)
    private double savingsBalance; // TODO: Remove savingsBalance from here once the new data model is fully and safely implemented (a few days' time)
    private double checkingBalance; // TODO: Remove checkingBalance from here once the new data model is fully and safely implemented (a few days' time)
    private int numOfTransactions; // TODO: Remove numOfTransactions from here once the new data model is fully and safely implemented (a few days' time)
    private List<Accounts> account;

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

    public List<Accounts> getAccount() {
        return account;
    }

    public void setAccount(List<Accounts> account) {
        this.account = account;
    }
}