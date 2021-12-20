package com.atm;

import Exceptions.InvalidInputException;
import Exceptions.OverdraftWithdrawlException;
import com.atm.model.Account;
import com.atm.services.BankServices;
import com.atm.services.CheckingServices;
import com.atm.services.SavingsServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.InputMismatchException;

@Service
public class ApiService {

    @Autowired
    BankServices bankServices;

    @Autowired
    SavingsServices savingsServices;

    @Autowired
    CheckingServices checkingServices;

    @Autowired
    Account account;

    public ArrayList<String> prevTransactions = new ArrayList<String>();

    public void prevTransactions(String accountType, String actionType, double moneyAmount) {
        if (accountType.equals("bank")) {
            if (actionType.equals("deposit")) {
                prevTransactions.add(0, "Bank: Deposited " + moneyAmount);
            } else if (actionType.equals("withdraw")) {
                prevTransactions.add(0, "Bank: Withdraw " + moneyAmount);
            }
        } else if (accountType.equals("savings")) {
            if (actionType.equals("deposit")) {
                prevTransactions.add(0, "Savings: Deposited " + moneyAmount);
            } else if (actionType.equals("withdraw")) {
                prevTransactions.add(0, "Savings: Withdraw " + moneyAmount);
            }
        } else if (accountType.equals("checking")) {
            if (actionType.equals("deposit")) {
                prevTransactions.add(0, "Checking: Deposited " + moneyAmount);
            } else if (actionType.equals("withdraw")) {
                prevTransactions.add(0, "Checking: Withdraw " + moneyAmount);
            }
        }
    }

    public void checkBalance(String accountInput, Account account) {
        if (accountInput.equals("bank")) {
            bankServices.checkBalance(account);
        } else if (accountInput.equals("savings")) {
            savingsServices.checkBalance(account);
        } else if (accountInput.equals("checking")) {
            checkingServices.checkBalance(account);
        }
    }

    public void transactionHandler(String accountInput, String transactionInput, double moneyInput, Account account) throws OverdraftWithdrawlException {
        if (transactionInput.equals("deposit")) {
            if (accountInput.equals("bank")) {
                bankServices.deposit(moneyInput, account);
            } else if (accountInput.equals("savings")) {
                savingsServices.deposit(moneyInput, account);
            } else if (accountInput.equals("checking")) {
                checkingServices.deposit(moneyInput, account);
            }
        }

        if (transactionInput.equals("withdraw")) {
            if (accountInput.equals("bank")) {
                bankServices.withdraw(moneyInput, account);
            } else if (accountInput.equals("savings")) {
                savingsServices.withdraw(moneyInput, account);
            } else if (accountInput.equals("checking")) {
                checkingServices.withdraw(moneyInput, account);
            }
        }
    }

    public String accountType(String apiAccountInput) throws InvalidInputException {
        String accountScannerPasser = "";

            if (apiAccountInput.equals("bank") || apiAccountInput.equals("savings") || apiAccountInput.equals("checking")) {
                accountScannerPasser = apiAccountInput;
            } else if (apiAccountInput.equals("monthly")) {
                savingsServices.monthlyFunctionsSavings(account);
                checkingServices.monthlyFunctionsChecking(account);
            } else if (apiAccountInput.startsWith("previous")) {
                System.out.println(prevTransactions);
            } else {
                throw new InvalidInputException("You have entered an invalid input; please enter a valid word or check your spelling");
            }

        return accountScannerPasser;
    }

    public String transactionType(String accountInput, String apiTransactionInput) throws InvalidInputException {
        String transactionScannerResult = "";
        
            try {
                if (apiTransactionInput.equals("deposit") || apiTransactionInput.equals("withdraw")) {
                    transactionScannerResult = apiTransactionInput;
                } else if (apiTransactionInput.equals("balance")) {
                    checkBalance(accountInput, account);
                } else if (apiTransactionInput.startsWith("previous")) {
                    System.out.println(prevTransactions);
                } else {
                    throw new InvalidInputException("You have entered an invalid input; please enter a valid word or check your spelling");
                }
            } catch (InvalidInputException ex) {
                System.out.println("Invalid input, try again");
            }

        return transactionScannerResult;
    }

    public void moneyInput(String accountInput, String transactionInput, double apiMoneyInput) throws OverdraftWithdrawlException {
            System.out.print("Enter your desired amount to " + transactionInput + ": ");
            try {
                transactionHandler(accountInput, transactionInput, apiMoneyInput, account);
                prevTransactions(accountInput, transactionInput, apiMoneyInput);
            } catch (OverdraftWithdrawlException e) {
                System.out.println("Exception occurred: " + e);
            } catch (InputMismatchException e) {
                System.out.println("Please enter a valid number!");
            }
        }
    }

