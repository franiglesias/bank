package com.codurance.bank;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class StatementPrinterTest {
    @Mock
    Console console;

    @Test
    void alwaysPrintHeader() {
        StatementPrinter statementPrinter = new StatementPrinter(console);
        List<Transaction> transactions = Collections.EMPTY_LIST;
        statementPrinter.print(transactions);
        verify(console).printLine("Date | Amount | Balance");
    }

    @Test
    void printTransactions() {
        StatementPrinter statementPrinter = new StatementPrinter(console);
        List<Transaction> transactions = new java.util.ArrayList<Transaction>(Collections.EMPTY_LIST);
        transactions.add(new Transaction("10/01/2012", 1000));
        transactions.add(new Transaction("13/01/2012", 2000));
        transactions.add(new Transaction("14/01/2012", -500));

        statementPrinter.print(transactions);

        InOrder inOrder = Mockito.inOrder(console);
        inOrder.verify(console).printLine("Date | Amount | Balance");
        inOrder.verify(console).printLine("14/01/2012 | -500 | 2500");
        inOrder.verify(console).printLine("13/01/2012 | 2000 | 3000");
        inOrder.verify(console).printLine("10/01/2012 | 1000 | 1000");
    }
}