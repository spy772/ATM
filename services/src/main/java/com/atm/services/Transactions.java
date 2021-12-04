package com.atm.services;

import Exceptions.OverdraftWithdrawlException;
import com.atm.model.Account;

public interface Transactions {

    public void checkBalance(Account account);

    public void deposit(double amountToDeposit, Account account);

    public void withdraw(double amountToWithdraw, Account account) throws OverdraftWithdrawlException;

}

