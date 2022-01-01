package com.atm;
import com.atm.model.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ApiDAO { // TODO: Add Deposit() and Withdraw() methods for each account type, incrementally

    @Autowired
    ApiMapper apiMapper;

    public double checkBankBalance() {
        return apiMapper.getClientById(1).getBankBalance();
    }

    public double checkSavingsBalance() {
        return apiMapper.getClientById(1).getSavingsBalance();
    }

    public double checkCheckingBalance() {
        return apiMapper.getClientById(1).getCheckingBalance();
    }

    public void depositIntoBank(double amountToDeposit) {
        double bankBalance = checkBankBalance();
        double newBankBalance = bankBalance + amountToDeposit;
        Client client = apiMapper.getClientById(1);
        client.setBankBalance(newBankBalance);
        apiMapper.updateClient(client);
    }

    public void depositIntoSavings(double amountToDeposit) {
        double savingsBalance = checkSavingsBalance();
        double newSavingsBalance = savingsBalance + amountToDeposit;
        Client client = apiMapper.getClientById(1);
        client.setSavingsBalance(newSavingsBalance);
        int numOfTransactions = client.getNumOfTransactions();
        numOfTransactions += 1;
        client.setNumOfTransactions(numOfTransactions);
        apiMapper.updateClient(client);
    }

    public void depositIntoChecking(double amountToDeposit) {
        double checkingBalance = checkCheckingBalance();
        double newCheckingBalance = checkingBalance + amountToDeposit;
        Client client = apiMapper.getClientById(1);
        client.setCheckingBalance(newCheckingBalance);
        apiMapper.updateClient(client);
    }

    public void withdrawFromBank(double amountToWithdraw) {
        double bankBalance = checkBankBalance();
        double newBankBalance = bankBalance - amountToWithdraw;
        Client client = apiMapper.getClientById(1);
        client.setBankBalance(newBankBalance);
        apiMapper.updateClient(client);
    }

    public void withdrawFromSavings(double amountToWithdraw) {
        double savingsBalance = checkSavingsBalance();
        double newSavingsBalance = savingsBalance - amountToWithdraw;
        Client client = apiMapper.getClientById(1);
        client.setSavingsBalance(newSavingsBalance);
        apiMapper.updateClient(client);
    }

    public void withdrawFromChecking(double amountToWithdraw) {
        double checkingBalance = checkCheckingBalance();
        double newCheckingBalance = checkingBalance - amountToWithdraw;
        Client client = apiMapper.getClientById(1);
        client.setCheckingBalance(newCheckingBalance);
        apiMapper.updateClient(client);
    }
}
