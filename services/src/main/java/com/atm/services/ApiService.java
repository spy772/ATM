package com.atm.services;

import Exceptions.InvalidInputException;
import Exceptions.OverdraftWithdrawlException;
import Utilities.TransactionUtilities;
import com.atm.model.AccountTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.InputMismatchException;

@Service
public class ApiService {

    @Autowired
    ApiDAO apiDAO;

    public ArrayList<String> prevTransactions = new ArrayList<String>();

    public int accountTypePasser;

    public String transactionTypePasser;

    public void prevTransactions(int accountType, String actionType, double moneyAmount) {
        if (actionType.equals("deposit")) {
            prevTransactions.add(0, "Deposited " + moneyAmount + "in account" + accountType);
        } else if (actionType.equals("withdraw")) {
            prevTransactions.add(0, "Withdrew " + moneyAmount + "from account" + accountType);
        }
    }

    public String checkBalance(int accountInput) {
        String accountBalance = "";
        accountBalance = "Your account balance is: " + apiDAO.checkAccountBalance(accountInput);
        return accountBalance;
    }

    public ArrayList specialRequests(String requestInput) {
        ArrayList previousTransactions = new ArrayList<String>();
        previousTransactions.add("Successful monthly transactions");

        if (requestInput.equals("monthly")) {
            TransactionUtilities.monthlyFunctionsSavings(apiDAO.apiMapper.getClientById());
            TransactionUtilities.monthlyFunctionsChecking(apiDAO.apiMapper.getClientById());
        } else if (requestInput.equals("previous")) {
            System.out.println(prevTransactions);
            previousTransactions = prevTransactions;
        }

        return previousTransactions;
    }

    public String createNewAccount(int clientId, String accountType) {
        String creationReturn = "";

        if (accountType.equals("bank") || accountType.equals("BANK")) {
            creationReturn   = apiDAO.createNewAccount(clientId, AccountTypes.BANK);
        } else if (accountType.equals("savings") || accountType.equals("SAVINGS")) {
            creationReturn = apiDAO.createNewAccount(clientId, AccountTypes.SAVINGS);
        } else if (accountType.equals("checking") || accountType.equals("CHECKING")) {
            creationReturn = apiDAO.createNewAccount(clientId, AccountTypes.CHECKING);
        } else {
            System.out.println("You have entered an invalid account type - please check your spelling");
            creationReturn = "You have entered an invalid account type - please check your spelling";
        }

        return creationReturn;
    }

    public String createNewClient() {
        return apiDAO.createNewClient();
    }

    public double transactionHandler(int accountInput, String transactionInput, double moneyInput) throws OverdraftWithdrawlException {
        double transactionHandlerReturn = 0;

        if (transactionInput.equals("deposit")) {
            transactionHandlerReturn = moneyInput;
            apiDAO.deposit(accountInput, moneyInput);
        } else if (transactionInput.equals("withdraw")) {
            transactionHandlerReturn = TransactionUtilities.legalWithdrawChecker(moneyInput, apiDAO.checkAccountBalance(accountInput));
            apiDAO.withdraw(accountInput, transactionHandlerReturn);
        }

        return transactionHandlerReturn;
    }

    public int accountType(int apiAccountInput) {
            accountTypePasser = apiAccountInput;
            return accountTypePasser;
    }

    public String transactionType(int accountInput, String apiTransactionInput) throws InvalidInputException {
        String transactionTypeReturn = "";
        
            try {
                if (apiTransactionInput.equals("deposit") || apiTransactionInput.equals("withdraw")) {
                    transactionTypePasser = apiTransactionInput;
                } else if (apiTransactionInput.equals("balance")) {
                    transactionTypeReturn = checkBalance(accountInput);
                } else if (apiTransactionInput.startsWith("previous")) {
                    System.out.println(prevTransactions);
                } else {
                    throw new InvalidInputException("You have entered an invalid input; please enter a valid word or check your spelling");
                }
            } catch (InvalidInputException ex) {
                System.out.println("Invalid input, try again");
            }

        return transactionTypeReturn;
    }

    public String moneyInput(int accountInput, String transactionInput, double apiMoneyInput) throws OverdraftWithdrawlException {
        String moneyInputReturn = "";

            System.out.print("Enter your desired amount to " + transactionInput + ": ");
            try {
                if (transactionInput.equals("deposit")) {
                    moneyInputReturn = "You have successfully deposited " + transactionHandler(accountInput, transactionInput, apiMoneyInput);
                } else if (transactionInput.equals("withdraw")) {
                    moneyInputReturn = "You have successfully withdrawn " + transactionHandler(accountInput, transactionInput, apiMoneyInput);
                }
                prevTransactions(accountInput, transactionInput, apiMoneyInput);
            } catch (OverdraftWithdrawlException e) {
                System.out.println("Exception occurred: " + e);
            } catch (InputMismatchException e) {
                System.out.println("Please enter a valid number!");
            }

            return moneyInputReturn;
        }
    }