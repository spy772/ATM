package com.atm;

import Exceptions.OverdraftWithdrawlException;
import com.atm.model.Account;

public interface Transactions {

    final public int YEARS = 30;
    final public  int PERCENT = 100;

    public void checkBalance(Account account);

    public void deposit(double amountToDeposit, Account account);

    public void withdraw(double amountToWithdraw, Account account) throws OverdraftWithdrawlException;

}

