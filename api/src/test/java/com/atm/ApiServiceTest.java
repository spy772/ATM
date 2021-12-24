package com.atm;

import Exceptions.InvalidInputException;
import Exceptions.OverdraftWithdrawlException;
import com.atm.model.Client;
import com.atm.services.BankServices;
import com.atm.services.CheckingServices;
import com.atm.services.SavingsServices;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ATMConfig.class})
public class ApiServiceTest extends TestCase {

    @Autowired
    ApiService apiService;

    @Before
    public void setUp() {
        apiService.client = new Client();
    }

    @Test
    public void testSavingsDeposit() throws OverdraftWithdrawlException {
        apiService.moneyInput("savings", "deposit", 5000);
        assertTrue("Expected a balance of $5000", apiService.client.getSavingsBalance() == 5000);
    }

    @Test
    public void testSavingsDepositAndReturn() throws OverdraftWithdrawlException {
        apiService.moneyInput("savings", "deposit", 10000);
        String apiCheckBalance = apiService.checkBalance("savings", apiService.client);
        assertTrue("Expected a return of $10000", apiCheckBalance.equals(apiService.savingsServices.checkBalance(apiService.client)));
    }

    @Test
    public void testBankDepositAndWithdraw() throws OverdraftWithdrawlException {
        apiService.moneyInput("bank", "deposit", 5000);
        apiService.moneyInput("bank", "withdraw", 2400);
        assertTrue("Expected a balance of $2600", apiService.client.getBankBalance() == 2600);
    }

    @Test
    public void testCheckingPreviousTransactions() throws OverdraftWithdrawlException, InvalidInputException {
        apiService.moneyInput("checking", "deposit", 2500);
        apiService.accountType("previous");
        assertTrue("Expected \"Checking: Deposited 2500.0\"", apiService.prevTransactions.get(0).equals("Checking: Deposited 2500.0"));
    }

    @Test
    public void testCheckingDepositWithdrawAndReturn() throws OverdraftWithdrawlException {
        apiService.moneyInput("checking", "deposit", 10000);
        apiService.moneyInput("checking", "withdraw", 4600);
        String apiCheckBalance = apiService.checkBalance("checking", apiService.client);
        assertTrue("Expected a return of $5400", apiCheckBalance.equals(apiService.checkingServices.checkBalance(apiService.client)));
    }

    @Test
    public void testBankTransactionNoEffectOnOtherAccount() throws OverdraftWithdrawlException, InvalidInputException {
        apiService.moneyInput("bank", "deposit", 5000);
        apiService.transactionType("savings", "balance");
        assertTrue("Expected $0 in savings balance", apiService.client.getSavingsBalance() == 0);
    }
}