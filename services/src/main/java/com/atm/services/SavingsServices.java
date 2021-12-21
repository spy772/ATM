package com.atm.services;

import Exceptions.OverdraftWithdrawlException;
import com.atm.model.Client;
import org.springframework.stereotype.Service;

import java.text.NumberFormat;
import java.util.Locale;

@Service
public class SavingsServices extends ExtendedTransactions {

    public String checkBalance(Client client) {
        System.out.println("Your current savings balance is: " + NumberFormat.getCurrencyInstance(Locale.CANADA).format(client.getSavingsBalance()));
        return "Your current savings balance is: " + NumberFormat.getCurrencyInstance(Locale.CANADA).format(client.getSavingsBalance());
    }

    public String deposit(double amountToDeposit, Client client) {
        double afterDeposit = client.getSavingsBalance() + amountToDeposit;
        client.setSavingsBalance(afterDeposit);
        System.out.println("You successfully deposited: " + amountToDeposit);
        numberOfTransactions(client);
        return "You successfully deposited: " + amountToDeposit;
    }

    public String withdraw(double amountToWithdraw, Client client) throws OverdraftWithdrawlException {
        String withdrawlResult;

        if (client.getSavingsBalance() > 0) {
            double afterWithdrawl = client.getSavingsBalance() - amountToWithdraw;

            if (afterWithdrawl < 0) {
                throw new OverdraftWithdrawlException("Withdrawing more than available balance, you current balance is: " + NumberFormat.getCurrencyInstance(Locale.CANADA).format(client.getSavingsBalance()));
            } else {
                client.setSavingsBalance(afterWithdrawl);
                System.out.println("You have successfully withdrawn: " + amountToWithdraw);
                withdrawlResult = "You have successfully withdrawn: " + amountToWithdraw;
            }
        } else {
            System.out.println("You do not have enough balance to withdraw, your current balance is: " + NumberFormat.getCurrencyInstance(Locale.CANADA).format(client.getSavingsBalance()));
            withdrawlResult = "You do not have enough balance to withdraw, your current balance is: " + NumberFormat.getCurrencyInstance(Locale.CANADA).format(client.getSavingsBalance());
        }

        return withdrawlResult;
    }
}
