package com.atm.services;
import Exceptions.OverdraftWithdrawlException;
import com.atm.model.Client;
import org.springframework.stereotype.Service;
import java.text.NumberFormat;
import java.util.Locale;

@Service
public class BankServices implements Transactions {

    public String checkBalance(Client client) {
        System.out.println("Your current bank balance is: " + NumberFormat.getCurrencyInstance(Locale.CANADA).format(client.getBankBalance()));
        return "Your current bank balance is: " + NumberFormat.getCurrencyInstance(Locale.CANADA).format(client.getBankBalance());
    }

    public String deposit(double amountToDeposit, Client client) {
        double afterDeposit = client.getBankBalance() + amountToDeposit;
        client.setBankBalance(afterDeposit);
        System.out.println("You successfully deposited: " + amountToDeposit);
        return "You successfully deposited: " + amountToDeposit;
    }

    public String withdraw(double amountToWithdraw, Client client) throws OverdraftWithdrawlException {
        String withdrawlResult;

        if (client.getBankBalance() > 0) {
            double afterWithdrawl = client.getBankBalance() - amountToWithdraw;

            if (afterWithdrawl < 0) {
                throw new OverdraftWithdrawlException("Withdrawing more than available balance, you current balance is: " + NumberFormat.getCurrencyInstance(Locale.CANADA).format(client.getBankBalance()));
            } else {
                client.setBankBalance(afterWithdrawl);
                System.out.println("You have successfully withdrawn: " + amountToWithdraw);
                withdrawlResult = "You have successfully withdrawn: " + amountToWithdraw;
            }
        } else {
            System.out.println("You do not have enough balance to withdraw, your current balance is: " + NumberFormat.getCurrencyInstance(Locale.CANADA).format(client.getBankBalance()));
            withdrawlResult = "You do not have enough balance to withdraw, your current balance is: " + NumberFormat.getCurrencyInstance(Locale.CANADA).format(client.getBankBalance());
        }

        return withdrawlResult;
    }
}

