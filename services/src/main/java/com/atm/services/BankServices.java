package com.atm.services;
import Exceptions.OverdraftWithdrawlException;
import com.atm.model.Account;
import org.springframework.stereotype.Service;
import java.text.NumberFormat;
import java.util.Locale;

@Service
public class BankServices implements Transactions {

    public String checkBalance(Account account) {
        System.out.println("Your current bank balance is: " + NumberFormat.getCurrencyInstance(Locale.CANADA).format(account.getBankBalance()));
        return "Your current bank balance is: " + NumberFormat.getCurrencyInstance(Locale.CANADA).format(account.getBankBalance());
    }

    public String deposit(double amountToDeposit, Account account) {
        double afterDeposit = account.getBankBalance() + amountToDeposit;
        account.setBankBalance(afterDeposit);
        System.out.println("You successfully deposited: " + amountToDeposit);
        return "You successfully deposited: " + amountToDeposit;
    }

    public String withdraw(double amountToWithdraw, Account account) throws OverdraftWithdrawlException {
        String withdrawlResult;

        if (account.getBankBalance() > 0) {
            double afterWithdrawl = account.getBankBalance() - amountToWithdraw;

            if (afterWithdrawl < 0) {
                throw new OverdraftWithdrawlException("Withdrawing more than available balance, you current balance is: " + NumberFormat.getCurrencyInstance(Locale.CANADA).format(account.getBankBalance()));
            } else {
                account.setBankBalance(afterWithdrawl);
                System.out.println("You have successfully withdrawn: " + amountToWithdraw);
                withdrawlResult = "You have successfully withdrawn: " + amountToWithdraw;
            }
        } else {
            System.out.println("You do not have enough balance to withdraw, your current balance is: " + NumberFormat.getCurrencyInstance(Locale.CANADA).format(account.getBankBalance()));
            withdrawlResult = "You do not have enough balance to withdraw, your current balance is: " + NumberFormat.getCurrencyInstance(Locale.CANADA).format(account.getBankBalance());
        }

        return withdrawlResult;
    }
}

