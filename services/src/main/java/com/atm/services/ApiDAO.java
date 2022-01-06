package com.atm.services;
import Utilities.TransactionUtilities;
import com.atm.model.Account;
import com.atm.model.AccountTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ApiDAO { // TODO: Change all the "Client" objects to "Account" objects

    @Autowired
    ApiMapper apiMapper;

    public double checkAccountBalance(int accountId) {
        return apiMapper.getAccountById(accountId).getBalance();
    }

    public void deposit(int accountId, double amountToDeposit) {
        double balance = checkAccountBalance(accountId);
        double newBalance = balance + amountToDeposit;
        Account account = apiMapper.getAccountById(accountId);
        account.setBalance(newBalance);

        if (account.getAccountType() == AccountTypes.SAVINGS) {
            TransactionUtilities.numberOfTransactions(account);
        }

        apiMapper.updateClient(account);
    }

    public void withdraw(int accountId, double amountToWithdraw) {
        double balance = checkAccountBalance(accountId);
        double newBalance = balance - amountToWithdraw;
        Account account = apiMapper.getAccountById(accountId);
        account.setBalance(newBalance);
        apiMapper.updateClient(account);
    }
}
