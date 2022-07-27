package com.codurance.bank;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class TransactionRepositoryShould {

    @Mock ClockService clockService;
    private TransactionRepository transactionRepository;

    @BeforeEach
    void setUp() {
        transactionRepository = new TransactionRepository(clockService);
    }

    @Test
    void storeDepositTransaction() {
        given(clockService.todayAsString()).willReturn("10/07/2022"); // Stub

        List<Transaction> expected = new ArrayList<>();
        expected.add(new Transaction("10/07/2022", 1000));

        transactionRepository.deposit(1000);

        List<Transaction> allTransactions = transactionRepository.allTransactions();

        Assertions.assertEquals(1, allTransactions.size());
        Assertions.assertEquals(expected, allTransactions);
    }

    @Test
    void storeWithdrawalTransaction() {
        given(clockService.todayAsString()).willReturn("25/10/2021"); // Stub

        List<Transaction> expected = new ArrayList<>();
        expected.add(new Transaction("25/10/2021", -500));

        transactionRepository.withdraw(500);

        List<Transaction> allTransactions = transactionRepository.allTransactions();

        Assertions.assertEquals(1, allTransactions.size());
        Assertions.assertEquals(expected, allTransactions);
    }
}