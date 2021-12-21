package com.atm.services;

import Exceptions.OverdraftWithdrawlException;
import com.atm.model.Account;

public interface Transactions {

    public String checkBalance(Account account);

    public String deposit(double amountToDeposit, Account account);

    public String withdraw(double amountToWithdraw, Account account) throws OverdraftWithdrawlException;

}

