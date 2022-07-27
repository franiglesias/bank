package com.codurance.bank;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class AccountServiceShould { // Unitary test

    private AccountService accountService;
    @Mock
    TransactionRepository transactionRepository; // Direct dependency - neighbour

    @Mock
    StatementPrinter statementPrinter; // Direct dependency - neighbour

    @BeforeEach
    void setUp() {
        accountService = new AccountService(
                transactionRepository,
                statementPrinter
        );
    }

    @Test
    void depositAnAmount() {
        accountService.deposit(1000);

        verify(transactionRepository).deposit(1000); // Mock / Spy
    }

    @Test
    void withdrawAmount() {
        accountService.withdraw(500);
        verify(transactionRepository).withdraw(500); // Mock / Spy
    }

    @Test
    void printStatement() {

        // Stub
        List<Transaction> transactions = new ArrayList<>();
        given(transactionRepository.allTransactions()).willReturn(transactions);

        accountService.printStatement();

        // Mock / Spy
        verify(statementPrinter).print(transactions);
    }
}