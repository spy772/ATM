package com.atm;

import Exceptions.InvalidInputException;
import Exceptions.OverdraftWithdrawlException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class Main implements CommandLineRunner {

    @Autowired
    ScannerClass scanningMethods;

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(Main.class);
        // disable spring banner
        app.setBannerMode(Banner.Mode.OFF);
        app.run(args);
    }

    @Override
    public void run(String... args) throws Exception {
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
