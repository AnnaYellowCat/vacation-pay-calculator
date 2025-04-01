package com.neoflex.vacationpaycalculator.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class VacationPayServiceTest {
    @InjectMocks
    private VacationPayService vacationPayService;

    @Test
    public void testCalculation() {
        assertEquals(28668.9419, vacationPayService.calculateVacationPay(30000, 28), 0.001);
    }

    @Test
    void testSalaryException() {
        assertThrows(IllegalArgumentException.class,
                () -> vacationPayService.calculateVacationPay(-30000, 28));
    }

    @Test
    void testNumberOfDaysException() {
        assertThrows(IllegalArgumentException.class,
                () -> vacationPayService.calculateVacationPay(30000, -28));
    }
    @Test
    void testCalculateWithDates() {
        List<LocalDate> dates = List.of(
                LocalDate.of(2025, 5, 12),
                LocalDate.of(2025, 5, 13),
                LocalDate.of(2025, 5, 14)
        );
        double result = vacationPayService.calculateVacationPayWithDates(30000, dates);
        assertEquals(3071.6723, result, 0.001);
    }

    @Test
    void testCalculateWithDatesWeekend() {
        List<LocalDate> dates = List.of(
                LocalDate.of(2025, 5, 11), //Воскресенье
                LocalDate.of(2025, 5, 12),
                LocalDate.of(2025, 5, 13)
        );
        double result = vacationPayService.calculateVacationPayWithDates(30000, dates);
        assertEquals(2047.7815, result, 0.001);
    }

    @Test
    void testCalculateWithDatesHolidays() {
        List<LocalDate> dates = List.of(
                LocalDate.of(2025, 5, 7),
                LocalDate.of(2025, 5, 8),
                LocalDate.of(2025, 5, 9)  //9 мая
        );
        double result = vacationPayService.calculateVacationPayWithDates(30000, dates);
        assertEquals(2047.7815, result, 0.001);
    }
}
