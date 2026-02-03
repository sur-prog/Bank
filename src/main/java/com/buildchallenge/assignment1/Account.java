package com.buildchallenge.assignment1;

import java.util.ArrayList;
import java.util.List;

public class Account {
    private final String accountNumber;
    private final String customerName;
    private final AccountType accountType;

    private double balance;
    private int monthlyTransactionCount;
    private int monthlyWithdrawalsCount;

    private final List<Transaction> transactionHistory = new ArrayList<>();

    public Account(String accountNumber, String customerName, AccountType accountType, double initialDeposit) {
        this.accountNumber = accountNumber;
        this.customerName = customerName;
        this.accountType = accountType;
        this.balance = initialDeposit;
        this.monthlyTransactionCount = 0;
        this.monthlyWithdrawalsCount = 0;
    }

    public String getAccountNumber() { return accountNumber; }
    public String getCustomerName() { return customerName; }
    public AccountType getAccountType() { return accountType; }
    public double getBalance() { return balance; }
    public int getMonthlyTransactionCount() { return monthlyTransactionCount; }
    public int getMonthlyWithdrawalsCount() { return monthlyWithdrawalsCount; }

    public List<Transaction> getTransactionHistory() {
        return new ArrayList<>(transactionHistory);
    }

    public void addTransaction(Transaction transaction) {
        transactionHistory.add(transaction);
    }

    public void incrementMonthlyTransactionCount() {
        monthlyTransactionCount++;
    }

    public void incrementMonthlyWithdrawalsCount() {
        monthlyWithdrawalsCount++;
    }

    public void resetMonthlyCounts() {
        monthlyTransactionCount = 0;
        monthlyWithdrawalsCount = 0;
    }

    public void setBalance(double newBalance) {
        this.balance = newBalance;
    }
}
