package com.codurance.bank;

import java.util.List;
import java.util.Stack;

public class StatementPrinter {
    private final Console console;

    public StatementPrinter(Console console) {

        this.console = console;
    }

    public void print(List<Transaction> transactions) {
        console.printLine("Date | Amount | Balance");

        int runningBalance = 0;
        Stack<String> lines = new Stack<>();

        for (Transaction transaction :
                transactions) {
            runningBalance = transaction.recalculateBalance(runningBalance);

            lines.push(transaction.formatted(runningBalance));
        }

        while (!lines.isEmpty()) {
            console.printLine(lines.pop());
        }
    }

}
