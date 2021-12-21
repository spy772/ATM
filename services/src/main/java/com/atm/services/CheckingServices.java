package com.atm.services;
import Exceptions.OverdraftWithdrawlException;
import com.atm.model.Account;
import org.springframework.stereotype.Service;
import java.text.NumberFormat;
import java.util.Locale;

@Service
public class CheckingServices extends ExtendedTransactions {

    public String checkBalance(Account account) {
        System.out.println("Your current checking balance is: " + NumberFormat.getCurrencyInstance(Locale.CANADA).format(account.getCheckingBalance()));
        return "Your current checking balance is: " + NumberFormat.getCurrencyInstance(Locale.CANADA).format(account.getCheckingBalance());
    }

    public String deposit(double amountToDeposit, Account account) {
        double afterDeposit = account.getCheckingBalance() + amountToDeposit;
        account.setCheckingBalance(afterDeposit);
        System.out.println("You successfully deposited: " + amountToDeposit);
        return "You successfully deposited: " + amountToDeposit;
    }

    public String withdraw(double amountToWithdraw, Account account) throws OverdraftWithdrawlException {
        String withdrawlResult;

        if (account.getCheckingBalance() > 0) {
            double afterWithdrawl = account.getCheckingBalance() - amountToWithdraw;

            if (afterWithdrawl < 0) {
                throw new OverdraftWithdrawlException("Withdrawing more than available balance, you current balance is: " + NumberFormat.getCurrencyInstance(Locale.CANADA).format(account.getCheckingBalance()));
            } else {
                account.setCheckingBalance(afterWithdrawl);
                System.out.println("You have successfully withdrawn: " + amountToWithdraw);
                withdrawlResult = "You have successfully withdrawn: " + amountToWithdraw;
            }
        } else {
            System.out.println("You do not have enough balance to withdraw, your current balance is: " + NumberFormat.getCurrencyInstance(Locale.CANADA).format(account.getCheckingBalance()));
            withdrawlResult = "You do not have enough balance to withdraw, your current balance is: " + NumberFormat.getCurrencyInstance(Locale.CANADA).format(account.getCheckingBalance());
        }

        return withdrawlResult;
    }
}
