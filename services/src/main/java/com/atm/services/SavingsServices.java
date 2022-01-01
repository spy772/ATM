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

    public double deposit(double amountToDeposit, Client client) {
        double afterDeposit = client.getSavingsBalance() + amountToDeposit;
        client.setSavingsBalance(afterDeposit);
        System.out.println("You successfully deposited: " + amountToDeposit);
        numberOfTransactions(client);
        return amountToDeposit;
    }

    public double withdraw(double amountToWithdraw, double balance) throws OverdraftWithdrawlException {

        if (balance >= 0) {
            double afterWithdrawl = balance - amountToWithdraw;

            if (afterWithdrawl < 0) {
                throw new OverdraftWithdrawlException("Withdrawing more than available balance, you current balance is: " + NumberFormat.getCurrencyInstance(Locale.CANADA).format(balance));
            } else {
                System.out.println("You have successfully withdrawn: " + amountToWithdraw);

            }
        }

        return amountToWithdraw;
    }
}
