package com.atm.services;

import Exceptions.OverdraftWithdrawlException;
import com.atm.model.Client;

public interface Transactions {

    public String checkBalance(Client client);

    public double deposit(double amountToDeposit, Client client);

    public double withdraw(double amountToWithdraw, double balance) throws OverdraftWithdrawlException;

}

