package com.buildchallenge.assignment1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BankTest {

    @Test
    void testSavingsMinBalanceRule() {
        Bank bank = new Bank();
        String savings = bank.openAccount("Test", AccountType.SAVINGS, 200);

        Transaction t = bank.withdraw(savings, 150);
        assertEquals(TransactionStatus.FAILED, t.getStatus());
    }

    @Test
    void testCheckingCannotGoNegative() {
        Bank bank = new Bank();
        String checking = bank.openAccount("Test", AccountType.CHECKING, 100);

        Transaction t = bank.withdraw(checking, 200);
        assertEquals(TransactionStatus.FAILED, t.getStatus());
    }

    @Test
    void testTransferSuccess() {
        Bank bank = new Bank();
        String a1 = bank.openAccount("A", AccountType.CHECKING, 500);
        String a2 = bank.openAccount("B", AccountType.CHECKING, 100);

        Transaction t = bank.transfer(a1, a2, 200);
        assertEquals(TransactionStatus.SUCCESS, t.getStatus());
    }
}
