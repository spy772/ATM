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

    Account account = new Account();

    public ArrayList<String> prevTransactions = new ArrayList<String>();

    public String accountTypePasser = "";

    public String transactionTypePasser = "";

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

    public String checkBalance(String accountInput, Account account) {
        String accountBalance = "";

        if (accountInput.equals("bank")) {
            accountBalance = bankServices.checkBalance(account);
        } else if (accountInput.equals("savings")) {
            accountBalance = savingsServices.checkBalance(account);
        } else if (accountInput.equals("checking")) {
            accountBalance = checkingServices.checkBalance(account);
        }

        return accountBalance;
    }

    public String transactionHandler(String accountInput, String transactionInput, double moneyInput, Account account) throws OverdraftWithdrawlException {
        String transactionHandlerReturn = "";

        if (transactionInput.equals("deposit")) {
            if (accountInput.equals("bank")) {
                transactionHandlerReturn = bankServices.deposit(moneyInput, account);
            } else if (accountInput.equals("savings")) {
                transactionHandlerReturn = savingsServices.deposit(moneyInput, account);
            } else if (accountInput.equals("checking")) {
                transactionHandlerReturn = checkingServices.deposit(moneyInput, account);
            }
        }

        if (transactionInput.equals("withdraw")) {
            if (accountInput.equals("bank")) {
                transactionHandlerReturn = bankServices.withdraw(moneyInput, account);
            } else if (accountInput.equals("savings")) {
                transactionHandlerReturn = savingsServices.withdraw(moneyInput, account);
            } else if (accountInput.equals("checking")) {
                transactionHandlerReturn = checkingServices.withdraw(moneyInput, account);
            }
        }

        return transactionHandlerReturn;
    }

    public String accountType(String apiAccountInput) throws InvalidInputException {
        String accountScannerPasser = "";

            if (apiAccountInput.equals("bank") || apiAccountInput.equals("savings") || apiAccountInput.equals("checking")) {
                accountTypePasser = apiAccountInput;
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
        String transactionTypeReturn = "";
        
            try {
                if (apiTransactionInput.equals("deposit") || apiTransactionInput.equals("withdraw")) {
                    transactionTypePasser = apiTransactionInput;
                } else if (apiTransactionInput.equals("balance")) {
                    transactionTypeReturn = checkBalance(accountInput, account);
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

    public String moneyInput(String accountInput, String transactionInput, double apiMoneyInput) throws OverdraftWithdrawlException {
        String moneyInputReturn = "";

            System.out.print("Enter your desired amount to " + transactionInput + ": ");
            try {
                moneyInputReturn = transactionHandler(accountInput, transactionInput, apiMoneyInput, account);
                prevTransactions(accountInput, transactionInput, apiMoneyInput);
            } catch (OverdraftWithdrawlException e) {
                System.out.println("Exception occurred: " + e);
            } catch (InputMismatchException e) {
                System.out.println("Please enter a valid number!");
            }

            return moneyInputReturn;
        }
    }

