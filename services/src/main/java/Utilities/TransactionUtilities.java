package Utilities;

import Exceptions.OverdraftWithdrawlException;
import com.atm.model.Account;
import com.atm.model.Client;

import java.security.AccessControlContext;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class TransactionUtilities {

    final public static int YEARS = 30;
    final public static int PERCENT = 100;
    private static double savingsInterest = 0.6 / PERCENT;
    private static double checkingInterest = 0.03 / PERCENT;

    public static void numberOfTransactions(Account account) {
        double afterTransaction = account.getBalance() - 500;
        if (account.getNumOfTransactions() < 6) {
            int addNumOfTransactions = account.getNumOfTransactions() + 1;
            account.setNumOfTransactions(addNumOfTransactions);
            System.out.println("You have made " + account.getNumOfTransactions() + " transactions out of your maximum 6 this month.");
        } else {
            System.out.println("You have exceeded your monthly number of transactions in this account, a $500 fee will be applied");
            account.setBalance(afterTransaction);
        }
    }

    public static void monthlyFunctionsSavings(List<Account> account) {
        int accountsAffected = 0;

        for (int i = 0; i < account.size(); i++) {
            account.get(i).getNumOfTransactions();
            double afterInterest = account.get(i).getBalance() * (1 + savingsInterest * YEARS);
            account.get(i).setBalance(afterInterest);
            double afterMonthlyFee = account.get(i).getBalance() - 500;
            account.get(i).setBalance(afterMonthlyFee);
            accountsAffected += 1;
        }

        System.out.println("$500 of monthly maintenance fees have been deducted from " + accountsAffected + " savings accounts");
    }

    public static void monthlyFunctionsChecking(List<Account> account) {
        int accountsAffected = 0;

        for (int i = 0; i < account.size(); i++) {
            account.get(i).getNumOfTransactions();
            double afterInterest = account.get(i).getBalance() * (1 + savingsInterest * YEARS);
            account.get(i).setBalance(afterInterest);
            double afterMonthlyFee = account.get(i).getBalance() - 500;
            account.get(i).setBalance(afterMonthlyFee);
            accountsAffected += 1;
        }

        System.out.println("$500 of monthly maintenance fees have been deducted from " + accountsAffected + " checking accounts");
    }

    public static double legalWithdrawChecker(double amountToWithdraw, double accountBalance) throws OverdraftWithdrawlException {

        double possibleWithdrawl = accountBalance - amountToWithdraw;

        if (possibleWithdrawl < 0) {
            throw new OverdraftWithdrawlException("Withdrawing more than available balance, you current balance is: " + NumberFormat.getCurrencyInstance(Locale.CANADA).format(accountBalance));
        } else {
            System.out.println("You have successfully withdrawn: " + amountToWithdraw);
        }

        return amountToWithdraw;
    }
}
