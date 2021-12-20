package com.atm;

import Exceptions.InvalidInputException;
import Exceptions.OverdraftWithdrawlException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

@SpringBootApplication(scanBasePackages={
        "com.atm"})
@RestController
public class Main implements CommandLineRunner {

    @Autowired
    ScannerService scanningMethods;

    @Autowired
    ApiService apiMethods;

    @GetMapping(value = "/previous")
    public ArrayList<String> previous() {
        return apiMethods.prevTransactions;
    }

    @PostMapping(value = "/{id1}/{id2}/{id3}")
    public String apiTransaction(@PathVariable("id1") String url1, @PathVariable("id2") String url2, @PathVariable("id3") double url3) {

        try {
            String accountScannerPasser = apiMethods.accountType(url1);
            String transactionScannerPasser = apiMethods.transactionType(accountScannerPasser, url2);
            apiMethods.moneyInput(accountScannerPasser, transactionScannerPasser, url3);
        } catch (InvalidInputException ex) {
            System.out.println(ex.getMessage());
        } catch (OverdraftWithdrawlException ex) {
            System.out.println(ex);
        }

        return "Transaction Complete";
    }

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
