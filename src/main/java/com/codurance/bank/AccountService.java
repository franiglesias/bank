package com.codurance.bank;

import java.util.List;

public class AccountService {
    private final TransactionRepository transactionRepository;
    private final StatementPrinter statementPrinter;

    public AccountService(TransactionRepository transactionRepository, StatementPrinter statementPrinter) {

        this.transactionRepository = transactionRepository;
        this.statementPrinter = statementPrinter;
    }

    public void deposit(int amount) {
        transactionRepository.deposit(amount);
    }

    public void withdraw(int amount) {
        transactionRepository.withdraw(amount);
    }

    public void printStatement() {
        List<Transaction> transactions = transactionRepository.allTransactions();
        statementPrinter.print(transactions);
    }
}
