package com.atm;

import Exceptions.InvalidInputException;
import Exceptions.OverdraftWithdrawlException;
import com.atm.services.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
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

    @GetMapping(value = "/balance/{id1}")
    public String balance(@PathVariable("id1") int accountId) {
        String print = "";

        try {
            print = apiMethods.transactionType(accountId, "balance");
        } catch (InvalidInputException ex) {
            print = ex.getMessage();
        }

        return print;
    }

    @GetMapping(value = "/all-accounts/{id1}")
    public List<String> printAllAccounts(@PathVariable("id1") int clientId) {
        return apiMethods.returnAccountsInClient(clientId);
    }

    @PostMapping(value = "/{id1}/{id2}/{id3}")
    public String apiTransaction(@PathVariable("id1") int url1, @PathVariable("id2") String url2, @PathVariable("id3") double url3) {
        String print = "";

        try {
            apiMethods.accountType(url1);
            apiMethods.transactionType(apiMethods.accountTypePasser, url2);
            print = apiMethods.moneyInput(apiMethods.accountTypePasser, apiMethods.transactionTypePasser, url3);
        } catch (InvalidInputException ex) {
            print = "Invalid input, try again";
        } catch (OverdraftWithdrawlException ex) {
            print = ex.getMessage();
        }

        return print;
    }

    @PostMapping(value = "create-account/{id1}/{id2}")
    public String accountCreation(@PathVariable("id1") int url1, @PathVariable("id2") String url2) {
        return apiMethods.createNewAccount(url1, url2);
    }

    @PostMapping(value = "create-client")
    public String clientCreation() {
        return apiMethods.createNewClient();
    }

    @PostMapping(value = "/{id1}")
    public String specialRequests(@PathVariable("id1") String specialRequest) {
        String print = "";

        apiMethods.specialRequests(specialRequest);
        return print;
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
                int accountScannerPasser = scanningMethods.accountType(scanner);
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
