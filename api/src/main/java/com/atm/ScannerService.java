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
import java.util.Scanner;

@Service
public class ScannerService {

    @Autowired
    ApiService apiMethods;

    @Autowired
    Account account;

    public String accountType(Scanner scanner) throws InvalidInputException {
        String accountScannerPasser;

        while (true) {
            System.out.print("Welcome to the ATM machine, enter \"bank\", \"savings\", \"checking\", \"monthly\" or \"previous\": ");
            String accountScanner = scanner.next();

            if (accountScanner.startsWith("bank") || accountScanner.startsWith("savings") || accountScanner.startsWith("checking")) {
                accountScannerPasser = accountScanner;
                break;
            } else if (accountScanner.startsWith("monthly")) {
                apiMethods.accountType("monthly");
            } else if (accountScanner.startsWith("previous")) {
                apiMethods.accountType("previous");
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
                    apiMethods.checkBalance(accountInput, account);
                } else if (transactionScanner.startsWith("previous")) {
                    apiMethods.accountType("previous");
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
                apiMethods.transactionHandler(accountInput, transactionInput, moneyInput, account);
                apiMethods.prevTransactions(accountInput, transactionInput, moneyInput);
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
