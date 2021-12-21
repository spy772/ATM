package com.atm.services;

import Exceptions.OverdraftWithdrawlException;
import com.atm.model.Client;

public interface Transactions {

    public String checkBalance(Client client);

    public String deposit(double amountToDeposit, Client client);

    public String withdraw(double amountToWithdraw, Client client) throws OverdraftWithdrawlException;

}

