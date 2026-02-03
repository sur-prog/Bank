package com.buildchallenge.assignment1;

public class BankDemo {
    public static void main(String[] args) {
        Bank bank = new Bank();

        String a1 = bank.openAccount("Raj", AccountType.CHECKING, 500);
        String a2 = bank.openAccount("Simran", AccountType.CHECKING, 300);
        String a3 = bank.openAccount("Rahul", AccountType.SAVINGS, 1000);
        String a4 = bank.openAccount("Rishi", AccountType.SAVINGS, 200);

        bank.deposit(a1, 200);
        bank.withdraw(a1, 50);
        bank.transfer(a1, a2, 100);
        bank.withdraw(a2, 500); // fail
        bank.deposit(a2, 150);
        bank.transfer(a2, a3, 50);
        bank.withdraw(a3, 200);
        bank.withdraw(a3, 200);
        bank.withdraw(a3, 200);
        bank.withdraw(a3, 200);
        bank.withdraw(a3, 200);
        bank.withdraw(a3, 50); // fail (withdraw limit)
        bank.withdraw(a4, 150); // fail (min balance)
        bank.deposit(a4, 500);
        bank.withdraw(a4, 100);
        bank.transfer(a4, a1, 50);
        bank.deposit(a1, 10);
        bank.deposit(a1, 10);
        bank.deposit(a1, 10);
        bank.deposit(a1, 10);

        bank.applyMonthlyInterest();

        System.out.println(bank.generateMonthlyStatement(a1));
        System.out.println(bank.generateMonthlyStatement(a2));
        System.out.println(bank.generateMonthlyStatement(a3));
        System.out.println(bank.generateMonthlyStatement(a4));
    }
}
