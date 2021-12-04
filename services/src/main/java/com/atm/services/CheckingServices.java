package com.atm.services;

import Exceptions.OverdraftWithdrawlException;
import com.atm.model.Account;

import java.text.NumberFormat;
import java.util.Locale;

public class CheckingServices extends ExtendedTransactions {

    public void checkBalance(Account account) {
        System.out.println("Your current balance is: " + NumberFormat.getCurrencyInstance(Locale.CANADA).format(account.getCheckingBalance()));
    }

    public void deposit(double amountToDeposit, Account account) {
        double afterDeposit = account.getCheckingBalance() + amountToDeposit;
        account.setCheckingBalance(afterDeposit);
        System.out.println("You successfully deposited: " + amountToDeposit);
    }

    public void withdraw(double amountToWithdraw, Account account) throws OverdraftWithdrawlException {
        if (account.getCheckingBalance() > 0) {
            double afterWithdrawl = account.getCheckingBalance() - amountToWithdraw;

            if (afterWithdrawl < 0) {
                throw new OverdraftWithdrawlException("Withdrawing more than available balance, you current balance is: " + NumberFormat.getCurrencyInstance(Locale.CANADA).format(account.getCheckingBalance()));
            } else {
                account.setCheckingBalance(afterWithdrawl);
                System.out.println("You have successfully withdrawn: " + amountToWithdraw);
            }
        } else {
            System.out.println("You do not have enough balance to withdraw, your current balance is: " + NumberFormat.getCurrencyInstance(Locale.CANADA).format(account.getCheckingBalance()));
        }
    }
}
