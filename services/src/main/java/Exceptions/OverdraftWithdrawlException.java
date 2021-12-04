package Exceptions;

public class OverdraftWithdrawlException extends Exception {
    public OverdraftWithdrawlException(String errorMessage) {
        super(errorMessage);
    }
}

