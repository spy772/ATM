package com.atm;

import Exceptions.InvalidInputException;
import Exceptions.OverdraftWithdrawlException;
import Utilities.TransactionUtilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.InputMismatchException;

@Service
public class ApiService {

    @Autowired
    ApiDAO apiDAO;

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

    public String checkBalance(String accountInput) {
        String accountBalance = "";

        if (accountInput.equals("bank")) {
            accountBalance = "Your bank balance is: " + apiDAO.checkBankBalance();
        } else if (accountInput.equals("savings")) {
            accountBalance = "Your savings balance is: " + apiDAO.checkSavingsBalance();
        } else if (accountInput.equals("checking")) {
            accountBalance = "Your checking balance is: " + apiDAO.checkCheckingBalance();
        }

        return accountBalance;
    }

    public double transactionHandler(String accountInput, String transactionInput, double moneyInput) throws OverdraftWithdrawlException {
        double transactionHandlerReturn = 0;

        if (transactionInput.equals("deposit")) {
            if (accountInput.equals("bank")) {
                transactionHandlerReturn = moneyInput;
                apiDAO.depositIntoBank(moneyInput);
            } else if (accountInput.equals("savings")) {
                transactionHandlerReturn = moneyInput;
                apiDAO.depositIntoSavings(moneyInput);
            } else if (accountInput.equals("checking")) {
                transactionHandlerReturn = moneyInput;
                apiDAO.depositIntoChecking(moneyInput);
            }
        }

        if (transactionInput.equals("withdraw")) {
            if (accountInput.equals("bank")) {
                transactionHandlerReturn = TransactionUtilities.legalWithdrawChecker(moneyInput, apiDAO.checkBankBalance());
                apiDAO.withdrawFromBank(transactionHandlerReturn);
            } else if (accountInput.equals("savings")) {
                transactionHandlerReturn = TransactionUtilities.legalWithdrawChecker(moneyInput, apiDAO.checkSavingsBalance());
                apiDAO.withdrawFromSavings(transactionHandlerReturn);
            } else if (accountInput.equals("checking")) {
                transactionHandlerReturn = TransactionUtilities.legalWithdrawChecker(moneyInput, apiDAO.checkCheckingBalance());
                apiDAO.withdrawFromChecking(transactionHandlerReturn);
            }
        }

        return transactionHandlerReturn;
    }

    public String accountType(String apiAccountInput) throws InvalidInputException {
        String accountScannerPasser = "";

            if (apiAccountInput.equals("bank") || apiAccountInput.equals("savings") || apiAccountInput.equals("checking")) {
                accountTypePasser = apiAccountInput;
            } else if (apiAccountInput.equals("monthly")) {
                TransactionUtilities.monthlyFunctionsSavings(apiDAO.apiMapper.getClientById(1));
                TransactionUtilities.monthlyFunctionsChecking(apiDAO.apiMapper.getClientById(1));
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

    public String moneyInput(String accountInput, String transactionInput, double apiMoneyInput) throws OverdraftWithdrawlException {
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

