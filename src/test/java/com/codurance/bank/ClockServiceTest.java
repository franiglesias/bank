package com.codurance.bank;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ClockServiceShould {
    @Test
    void giveTodayFormattedAsDD_MM_YYYY() {
        ClockService clockService = new TestableClockService();

        assertEquals("10/07/2022", clockService.todayAsString());

    }

    private class TestableClockService extends ClockService {
        @Override
        protected LocalDate today() {
            return LocalDate.of(2022, 7, 10);
        }
    }
}