package com.neoflex.vacation_pay_calculator.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class VacationPayServiceTest {
    @InjectMocks
    private VacationPayService vacationPayService;

    @Test
    public void calculateByDays_ValidInput_ReturnsCorrectAmount() {
        assertEquals(28668.94, vacationPayService.calculateByDays(30000, 28), 0.01);
    }
    @Test
    void calculateByDates_WorkingDaysOnly_ReturnsCorrectAmount() {
        List<LocalDate> dates = List.of(
                LocalDate.of(2025, 5, 12),
                LocalDate.of(2025, 5, 13),
                LocalDate.of(2025, 5, 14)
        );
        double result = vacationPayService.calculateByDates(30000, dates);
        assertEquals(3071.67, result, 0.01);
    }

    @Test
    void calculateByDates_WeekendIncluded_ExcludesWeekend() {
        List<LocalDate> dates = List.of(
                LocalDate.of(2025, 5, 11), //Воскресенье
                LocalDate.of(2025, 5, 12),
                LocalDate.of(2025, 5, 13)
        );
        double result = vacationPayService.calculateByDates(30000, dates);
        assertEquals(2047.78, result, 0.01);
    }

    @Test
    void calculateByDates_HolidayIncluded_ExcludesHoliday() {
        List<LocalDate> dates = List.of(
                LocalDate.of(2025, 5, 7),
                LocalDate.of(2025, 5, 8),
                LocalDate.of(2025, 5, 9)  //9 мая
        );
        double result = vacationPayService.calculateByDates(30000, dates);
        assertEquals(2047.78, result, 0.01);
    }

    @Test
    void calculateByDates_HolidayAndWeekendIncluded_ExcludesHolidayAndWeekend() {
        List<LocalDate> dates = List.of(
                LocalDate.of(2025, 5, 9),  //9 мая
                LocalDate.of(2025, 5, 11) //Воскресенье
        );
        double result = vacationPayService.calculateByDates(30000, dates);
        assertEquals(0, result, 0.01);
    }
}
