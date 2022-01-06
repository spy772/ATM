package Utilities;

import Exceptions.OverdraftWithdrawlException;
import com.atm.model.Account;
import com.atm.model.Client;

import java.text.NumberFormat;
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

    public static void monthlyFunctionsSavings(Account account) {
        account.setNumOfTransactions(0);
        double afterInterest = account.getBalance() * (1 + savingsInterest * YEARS);
        account.setBalance(afterInterest);
        double afterMonthlyFee = account.getBalance() - 500;
        account.setBalance(afterMonthlyFee);

        System.out.println("You have added " + NumberFormat.getCurrencyInstance().format(afterInterest) + " to your account via interest");
        System.out.println("$500 of monthly maintenance fees have been deducted from your savings account");
        System.out.println("Your current savings balance is: " + NumberFormat.getCurrencyInstance().format(account.getBalance()));
    }

    public static void monthlyFunctionsChecking(Account account) {
        double afterInterest = account.getBalance() * (1 + checkingInterest * YEARS);
        account.setBalance(afterInterest);
        double afterMonthlyFee = account.getBalance() - 500;
        account.setBalance(afterMonthlyFee);

        System.out.println("You have added " + NumberFormat.getCurrencyInstance().format(afterInterest) + " to your account via interest");
        System.out.println("$500 of monthly maintenance fees have been deducted from your checking account");
        System.out.println("Your current checking balance is: " + NumberFormat.getCurrencyInstance().format(account.getBalance()));
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
