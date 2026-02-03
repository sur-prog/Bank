package com.buildchallenge.assignment1;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class Bank {

    private final Map<String, Account> accounts = new HashMap<>();
    private int accountSequence = 1000;

    public String openAccount(String customerName, AccountType accountType, double initialDeposit) {
        if (customerName == null || customerName.isBlank()) {
            throw new IllegalArgumentException("Customer name cannot be empty");
        }
        if (initialDeposit < 0) {
            throw new IllegalArgumentException("Initial deposit cannot be negative");
        }
        if (accountType == AccountType.SAVINGS && initialDeposit < 100) {
            throw new IllegalArgumentException("Savings account requires minimum initial deposit of $100");
        }

        String accNo = "ACC" + (++accountSequence);
        Account account = new Account(accNo, customerName, accountType, initialDeposit);
        accounts.put(accNo, account);

        Transaction t = new Transaction(TransactionType.DEPOSIT, initialDeposit, 0, initialDeposit,
                TransactionStatus.SUCCESS, "Account opened");
        account.addTransaction(t);

        return accNo;
    }

    public void closeAccount(String accountNumber) {
        Account acc = getAccountOrThrow(accountNumber);
        if (acc.getBalance() != 0) {
            throw new IllegalStateException("Account can be closed only if balance is zero");
        }
        accounts.remove(accountNumber);
    }

    public Transaction deposit(String accountNumber, double amount) {
        Account acc = getAccountOrThrow(accountNumber);
        if (amount <= 0) {
            return recordFailed(acc, TransactionType.DEPOSIT, amount, "Deposit amount must be > 0");
        }

        double before = acc.getBalance();
        double after = before + amount;

        acc.setBalance(after);
        acc.incrementMonthlyTransactionCount();

        Transaction t = new Transaction(TransactionType.DEPOSIT, amount, before, after,
                TransactionStatus.SUCCESS, null);
        acc.addTransaction(t);
        return t;
    }

    public Transaction withdraw(String accountNumber, double amount) {
        Account acc = getAccountOrThrow(accountNumber);
        if (amount <= 0) {
            return recordFailed(acc, TransactionType.WITHDRAWAL, amount, "Withdrawal amount must be > 0");
        }

        if (acc.getAccountType() == AccountType.SAVINGS && acc.getMonthlyWithdrawalsCount() >= 5) {
            return recordFailed(acc, TransactionType.WITHDRAWAL, amount, "Savings max 5 withdrawals per month exceeded");
        }

        double before = acc.getBalance();
        double fee = getTransactionFee(acc);
        double totalDebit = amount + fee;

        if (acc.getAccountType() == AccountType.SAVINGS && (before - totalDebit) < 100) {
            return recordFailed(acc, TransactionType.WITHDRAWAL, amount, "Savings minimum balance $100 must be maintained");
        }

        if (acc.getAccountType() == AccountType.CHECKING && (before - totalDebit) < 0) {
            return recordFailed(acc, TransactionType.WITHDRAWAL, amount, "Insufficient funds");
        }

        double after = before - totalDebit;

        acc.setBalance(after);
        acc.incrementMonthlyTransactionCount();
        acc.incrementMonthlyWithdrawalsCount();

        Transaction t = new Transaction(TransactionType.WITHDRAWAL, amount, before, after,
                TransactionStatus.SUCCESS, fee > 0 ? ("Fee charged: $" + fee) : null);
        acc.addTransaction(t);
        return t;
    }

    public Transaction transfer(String fromAccount, String toAccount, double amount) {
        Account from = getAccountOrThrow(fromAccount);
        Account to = getAccountOrThrow(toAccount);

        if (fromAccount.equals(toAccount)) {
            return recordFailed(from, TransactionType.TRANSFER, amount, "Cannot transfer to same account");
        }

        if (amount <= 0) {
            return recordFailed(from, TransactionType.TRANSFER, amount, "Transfer amount must be > 0");
        }

        double beforeFrom = from.getBalance();
        double fee = getTransactionFee(from);
        double totalDebit = amount + fee;

        if (from.getAccountType() == AccountType.SAVINGS && from.getMonthlyWithdrawalsCount() >= 5) {
            return recordFailed(from, TransactionType.TRANSFER, amount, "Savings max 5 withdrawals per month exceeded");
        }

        if (from.getAccountType() == AccountType.SAVINGS && (beforeFrom - totalDebit) < 100) {
            return recordFailed(from, TransactionType.TRANSFER, amount, "Savings minimum balance $100 must be maintained");
        }

        if (from.getAccountType() == AccountType.CHECKING && (beforeFrom - totalDebit) < 0) {
            return recordFailed(from, TransactionType.TRANSFER, amount, "Insufficient funds");
        }

        double afterFrom = beforeFrom - totalDebit;
        from.setBalance(afterFrom);
        from.incrementMonthlyTransactionCount();
        from.incrementMonthlyWithdrawalsCount();

        Transaction debit = new Transaction(TransactionType.TRANSFER, amount, beforeFrom, afterFrom,
                TransactionStatus.SUCCESS, fee > 0 ? ("Fee charged: $" + fee) : null);
        from.addTransaction(debit);

        double beforeTo = to.getBalance();
        double afterTo = beforeTo + amount;
        to.setBalance(afterTo);
        to.incrementMonthlyTransactionCount();

        Transaction credit = new Transaction(TransactionType.TRANSFER, amount, beforeTo, afterTo,
                TransactionStatus.SUCCESS, "Received from " + fromAccount);
        to.addTransaction(credit);

        return debit;
    }

    public List<Transaction> getTransactionHistory(String accountNumber, LocalDateTime start, LocalDateTime end) {
        Account acc = getAccountOrThrow(accountNumber);
        return acc.getTransactionHistory().stream()
                .filter(t -> (start == null || !t.getTimestamp().isBefore(start)))
                .filter(t -> (end == null || !t.getTimestamp().isAfter(end)))
                .collect(Collectors.toList());
    }

    public void applyMonthlyInterest() {
        for (Account acc : accounts.values()) {
            if (acc.getAccountType() == AccountType.SAVINGS) {
                double before = acc.getBalance();
                double interest = before * 0.02;
                double after = before + interest;

                acc.setBalance(after);
                Transaction t = new Transaction(TransactionType.DEPOSIT, interest, before, after,
                        TransactionStatus.SUCCESS, "Monthly interest applied (2%)");
                acc.addTransaction(t);
            }
            acc.resetMonthlyCounts();
        }
    }

    public String generateMonthlyStatement(String accountNumber) {
        Account acc = getAccountOrThrow(accountNumber);
        StringBuilder sb = new StringBuilder();

        sb.append("\n===== Monthly Statement =====\n");
        sb.append("Account: ").append(acc.getAccountNumber()).append("\n");
        sb.append("Customer: ").append(acc.getCustomerName()).append("\n");
        sb.append("Type: ").append(acc.getAccountType()).append("\n");
        sb.append("Transactions:\n");

        for (Transaction t : acc.getTransactionHistory()) {
            sb.append(t).append("\n");
        }

        sb.append("Ending Balance: $").append(String.format("%.2f", acc.getBalance())).append("\n");
        sb.append("=============================\n");

        return sb.toString();
    }

    private Account getAccountOrThrow(String accountNumber) {
        Account acc = accounts.get(accountNumber);
        if (acc == null) throw new IllegalArgumentException("Account not found: " + accountNumber);
        return acc;
    }

    private double getTransactionFee(Account acc) {
        if (acc.getAccountType() == AccountType.CHECKING) {
            return (acc.getMonthlyTransactionCount() >= 10) ? 2.50 : 0.0;
        }
        return 0.0;
    }

    private Transaction recordFailed(Account acc, TransactionType type, double amount, String reason) {
        double before = acc.getBalance();
        Transaction t = new Transaction(type, amount, before, before, TransactionStatus.FAILED, reason);
        acc.addTransaction(t);
        return t;
    }

    public Map<String, Account> getAccounts() {
        return new HashMap<>(accounts);
    }
}
