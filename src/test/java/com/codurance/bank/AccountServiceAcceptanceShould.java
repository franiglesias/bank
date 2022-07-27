package com.codurance.bank;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class AccountServiceAcceptanceShould {
    private AccountService accountService;
    @Mock
    Console console;
    @Mock
    ClockService clockService;

    @BeforeEach
    void setUp() {
        TransactionRepository transactionRepository = new TransactionRepository(clockService);
        StatementPrinter statementPrinter = new StatementPrinter(console);

        accountService = new AccountService(
                transactionRepository,
                statementPrinter
        );
    }

    @Test
    void printStatementWithTransactions() {
        // Stub
        given(clockService.todayAsString()).willReturn(
                "10/01/2012",
                "13/01/2012",
                "14/01/2012"
        );

        accountService.deposit(1000);
        accountService.deposit(2000);
        accountService.withdraw(500);

        accountService.printStatement();

        verify(console).printLine("Date | Amount | Balance");
        verify(console).printLine("14/01/2012 | -500 | 2500");
        verify(console).printLine("13/01/2012 | 2000 | 3000");
        verify(console).printLine("10/01/2012 | 1000 | 1000");

    }

}
