package com.buildchallenge.assignment1;

import java.time.LocalDateTime;
import java.util.UUID;

public class Transaction {
    private final String transactionId;
    private final LocalDateTime timestamp;
    private final TransactionType type;
    private final double amount;
    private final double balanceBefore;
    private final double balanceAfter;
    private final TransactionStatus status;
    private final String reason;

    public Transaction(TransactionType type, double amount,
                       double balanceBefore, double balanceAfter,
                       TransactionStatus status, String reason) {
        this.transactionId = UUID.randomUUID().toString();
        this.timestamp = LocalDateTime.now();
        this.type = type;
        this.amount = amount;
        this.balanceBefore = balanceBefore;
        this.balanceAfter = balanceAfter;
        this.status = status;
        this.reason = reason;
    }

    public String getTransactionId() { return transactionId; }
    public LocalDateTime getTimestamp() { return timestamp; }
    public TransactionType getType() { return type; }
    public double getAmount() { return amount; }
    public double getBalanceBefore() { return balanceBefore; }
    public double getBalanceAfter() { return balanceAfter; }
    public TransactionStatus getStatus() { return status; }
    public String getReason() { return reason; }

    @Override
    public String toString() {
        return "Transaction{" +
                "id='" + transactionId + '\'' +
                ", time=" + timestamp +
                ", type=" + type +
                ", amount=" + amount +
                ", before=" + balanceBefore +
                ", after=" + balanceAfter +
                ", status=" + status +
                (reason != null ? ", reason='" + reason + '\'' : "") +
                '}';
    }
}
