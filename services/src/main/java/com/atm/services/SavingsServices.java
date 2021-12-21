package com.atm.services;

import Exceptions.OverdraftWithdrawlException;
import com.atm.model.Account;
import org.springframework.stereotype.Service;

import java.text.NumberFormat;
import java.util.Locale;

@Service
public class SavingsServices extends ExtendedTransactions {

    public String checkBalance(Account account) {
        System.out.println("Your current savings balance is: " + NumberFormat.getCurrencyInstance(Locale.CANADA).format(account.getSavingsBalance()));
        return "Your current savings balance is: " + NumberFormat.getCurrencyInstance(Locale.CANADA).format(account.getSavingsBalance());
    }

    public String deposit(double amountToDeposit, Account account) {
        double afterDeposit = account.getSavingsBalance() + amountToDeposit;
        account.setSavingsBalance(afterDeposit);
        System.out.println("You successfully deposited: " + amountToDeposit);
        numberOfTransactions(account);
        return "You successfully deposited: " + amountToDeposit;
    }

    public String withdraw(double amountToWithdraw, Account account) throws OverdraftWithdrawlException {
        String withdrawlResult;

        if (account.getSavingsBalance() > 0) {
            double afterWithdrawl = account.getSavingsBalance() - amountToWithdraw;

            if (afterWithdrawl < 0) {
                throw new OverdraftWithdrawlException("Withdrawing more than available balance, you current balance is: " + NumberFormat.getCurrencyInstance(Locale.CANADA).format(account.getSavingsBalance()));
            } else {
                account.setSavingsBalance(afterWithdrawl);
                System.out.println("You have successfully withdrawn: " + amountToWithdraw);
                withdrawlResult = "You have successfully withdrawn: " + amountToWithdraw;
            }
        } else {
            System.out.println("You do not have enough balance to withdraw, your current balance is: " + NumberFormat.getCurrencyInstance(Locale.CANADA).format(account.getSavingsBalance()));
            withdrawlResult = "You do not have enough balance to withdraw, your current balance is: " + NumberFormat.getCurrencyInstance(Locale.CANADA).format(account.getSavingsBalance());
        }

        return withdrawlResult;
    }
}
