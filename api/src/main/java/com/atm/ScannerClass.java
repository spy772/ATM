package com.atm;

import Exceptions.InvalidInputException;
import Exceptions.OverdraftWithdrawlException;
import com.atm.model.Account;
import com.atm.services.BankServices;
import com.atm.services.CheckingServices;
import com.atm.services.SavingsServices;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

@Service
public class ScannerClass {

    public ArrayList<String> prevTransactions = new ArrayList<String>();
    Account account = new Account();
    BankServices myAccount = new BankServices();
    SavingsServices mySavingsServices = new SavingsServices();
    CheckingServices myCheckingServices = new CheckingServices();

    public void prevTransactions(String accountType, String actionType, double moneyAmount) {
        if (accountType == "bank") {
            if (actionType == "deposit") {
                prevTransactions.add(0, "Bank: Deposited " + moneyAmount);
            } else if (actionType == "withdraw") {
                prevTransactions.add(0, "Bank: Withdraw " + moneyAmount);
            }
        } else if (accountType == "savings") {
            if (actionType == "deposit") {
                prevTransactions.add(0, "Savings: Deposited " + moneyAmount);
            } else if (actionType == "withdraw") {
                prevTransactions.add(0, "Savings: Withdraw " + moneyAmount);
            }
        } else if (accountType == "checking") {
            if (actionType == "deposit") {
                prevTransactions.add(0, "Checking: Deposited " + moneyAmount);
            } else if (actionType == "withdraw") {
                prevTransactions.add(0, "Checking: Withdraw " + moneyAmount);
            }
        }
    }

    public void checkBalance(String accountInput, Account account) {
        if (accountInput.equals("bank")) {
            myAccount.checkBalance(account);
        } else if (accountInput.equals("savings")) {
            mySavingsServices.checkBalance(account);
        } else if (accountInput.equals("checking")) {
            myCheckingServices.checkBalance(account);
        }
    }

    public void transactionHandler(String accountInput, String transactionInput, double moneyInput, Account account) throws OverdraftWithdrawlException {
        if (transactionInput.equals("deposit")) {
            if (accountInput.equals("bank")) {
                myAccount.deposit(moneyInput, account);
            } else if (accountInput.equals("savings")) {
                mySavingsServices.deposit(moneyInput, account);
            } else if (accountInput.equals("checking")) {
                myCheckingServices.deposit(moneyInput, account);
            }

            prevTransactions(accountInput, "deposit", moneyInput);
        }

        if (transactionInput.equals("withdraw")) {
            if (accountInput.equals("bank")) {
                myAccount.withdraw(moneyInput, account);
            } else if (accountInput.equals("savings")) {
                mySavingsServices.withdraw(moneyInput, account);
            } else if (accountInput.equals("checking")) {
                myCheckingServices.withdraw(moneyInput, account);
            }

            prevTransactions(accountInput, "withdraw", moneyInput);
        }
    }

    public String accountType(Scanner scanner) throws InvalidInputException {
        String accountScannerPasser;

        while (true) {
            System.out.print("Welcome to the ATM machine, enter \"bank\", \"savings\", \"checking\", \"monthly\" or \"previous\": ");
            String accountScanner = scanner.next();

            if (accountScanner.startsWith("bank") || accountScanner.startsWith("savings") || accountScanner.startsWith("checking")) {
                accountScannerPasser = accountScanner;
                break;
            } else if (accountScanner.startsWith("monthly")) {
                mySavingsServices.monthlyFunctionsSavings(account);
                myCheckingServices.monthlyFunctionsChecking(account);
            } else if (accountScanner.startsWith("previous")) {
                System.out.println(prevTransactions);
            } else {
                throw new InvalidInputException("You have entered an invalid input; please enter a valid word or check your spelling");
            }
        }

        return accountScannerPasser;
    }

    public String transactionType(String accountInput, Scanner scanner) throws InvalidInputException {
        String transactionScannerResult;

        while (true) {
            System.out.print("Welcome to your " + accountInput + " account, enter \"balance\", \"deposit\", \"withdraw\" or \"previous\": ");
            String transactionScanner = scanner.next();

            try {
                if (transactionScanner.startsWith("deposit") || transactionScanner.startsWith("withdraw")) {
                    transactionScannerResult = transactionScanner;
                    break;
                } else if (transactionScanner.startsWith("balance")) {
                    checkBalance(accountInput, account);
                } else if (transactionScanner.startsWith("previous")) {
                    System.out.println(prevTransactions);
                } else {
                    throw new InvalidInputException("You have entered an invalid input; please enter a valid word or check your spelling");
                }
            } catch (InvalidInputException ex) {
                System.out.println("Invalid input, try again");
            }
        }

        return transactionScannerResult;
    }

    public void moneyInput(String accountInput, String transactionInput, Scanner scanner) throws OverdraftWithdrawlException {

        while (true) {
            System.out.print("Enter your desired amount to " + transactionInput + ": ");
            try {
                double moneyInput = scanner.nextDouble();
                transactionHandler(accountInput, transactionInput, moneyInput, account);
                prevTransactions(accountInput, transactionInput, moneyInput);
                break;
            } catch (OverdraftWithdrawlException e) {
                System.out.println("Exception occurred: " + e);
                scanner.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Please enter a valid number!");
                scanner.nextLine();
            }
        }
    }
}
