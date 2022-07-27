package com.codurance.bank;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ClockService {
    public String todayAsString() {
        return today().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    protected LocalDate today() {
        return LocalDate.now();
    }
}
