package com.atm;

import Exceptions.InvalidInputException;
import Exceptions.OverdraftWithdrawlException;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        ScannerClass scanningMethods = new ScannerClass();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            try {
                String accountScannerPasser = scanningMethods.accountType(scanner);
                String transactionScannerPasser = scanningMethods.transactionType(accountScannerPasser, scanner);
                scanningMethods.moneyInput(accountScannerPasser, transactionScannerPasser, scanner);
            } catch (InvalidInputException ex) {
                System.out.println(ex.getMessage());
            } catch (OverdraftWithdrawlException ex) {
                System.out.println(ex);
            }
        }
    }
}
