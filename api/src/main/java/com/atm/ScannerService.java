package com.atm;

import Exceptions.InvalidInputException;
import Exceptions.OverdraftWithdrawlException;
import com.atm.services.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

@Service
public class ScannerService {

    @Autowired
    ApiService apiMethods;

    public int accountType(Scanner scanner) throws InvalidInputException {
        int accountScannerPasser;

        while (true) {
            System.out.print("Welcome to the ATM machine, enter your Account/Client ID, \"monthly\" or \"previous\": ");
            String accountScanner = scanner.next();

                if (accountScanner.startsWith("monthly")) {
                    apiMethods.specialRequests("monthly");
                } else if (accountScanner.startsWith("previous")) {
                    apiMethods.specialRequests("previous");
                } else if (!accountScanner.startsWith("monthly") || !accountScanner.startsWith("previous")) {
                    accountScannerPasser = Integer.parseInt(accountScanner);
                    break;
                } else {
                    throw new InvalidInputException("You have entered an invalid input; please enter a valid word or check your spelling");
                }
            }

        return accountScannerPasser;
    }

    public String transactionType(int accountInput, Scanner scanner) throws InvalidInputException {
        String transactionScannerResult;

        while (true) {
            System.out.print("Welcome to your account/client with ID " + accountInput + ", enter \"balance\", \"deposit\", \"withdraw\", \"previous\" or \"return all\": ");
            String transactionScanner = scanner.next();

            try {
                if (transactionScanner.startsWith("deposit") || transactionScanner.startsWith("withdraw")) {
                    transactionScannerResult = transactionScanner;
                    break;
                } else if (transactionScanner.startsWith("balance")) {
                    apiMethods.transactionType(accountInput, "balance");
                } else if (transactionScanner.startsWith("previous")) {
                    apiMethods.specialRequests("previous");
                } else if (transactionScanner.startsWith("return all")) {
                    apiMethods.returnAccountsInClient(accountInput);
                } else {
                    throw new InvalidInputException("You have entered an invalid input; please enter a valid word or check your spelling");
                }
            } catch (InvalidInputException ex) {
                System.out.println("Invalid input, try again");
            }
        }

        return transactionScannerResult;
    }

    public void moneyInput(int accountInput, String transactionInput, Scanner scanner) throws OverdraftWithdrawlException {

        while (true) {
            System.out.print("Enter your desired amount to " + transactionInput + ": ");
            try {
                double moneyInput = scanner.nextDouble();
                apiMethods.moneyInput(accountInput, transactionInput, moneyInput);
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
