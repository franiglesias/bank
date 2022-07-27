package com.codurance.bank;

import java.util.ArrayList;
import java.util.List;

public class TransactionRepository {
    List<Transaction> transactions = new ArrayList<>();
    private ClockService clockService;

    public TransactionRepository(ClockService clockService) {

        this.clockService = clockService;
    }

    public void deposit(int amount) {
        String date = clockService.todayAsString();
        Transaction transaction = new Transaction(date, amount);

        transactions.add(transaction);
    }

    public List<Transaction> allTransactions() {
        return transactions;
    }

    public void withdraw(int amount) {
        String date = clockService.todayAsString();
        Transaction transaction = new Transaction(date, -amount);

        transactions.add(transaction);
    }
}
