package com.atm.services;
import Utilities.TransactionUtilities;
import com.atm.model.Account;
import com.atm.model.AccountTypes;
import com.atm.model.Client;
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

    public String createNewAccount(int clientId, AccountTypes accountType) {
        Account newAccount = new Account(clientId, 0, accountType, 0);
        apiMapper.createNewAccount(newAccount);
        System.out.println("You have successfully created a " + accountType + "account with the id " + newAccount.getAccountId() + "under client id " + clientId);
        return "You have successfully created a " + accountType + "account with the id " + newAccount.getAccountId() + "under client id " + clientId;
    }

    public String createNewClient() {
        Client client = new Client();
        apiMapper.createNewClient(client);
        return "You have successfully created a client with the id of " + client.getClientId();
    }
}
