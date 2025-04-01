package com.neoflex.vacationpaycalculator.services;

import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

import static java.lang.Math.round;

@Service
public class VacationPayService {
    private static final double AVERAGE_DAYS_IN_MONTHS = 29.3;
    public double calculateVacationPay(double averageSalary, int vacationDaysNumber){
        validateValues(averageSalary, vacationDaysNumber);
        return (double) round(averageSalary / AVERAGE_DAYS_IN_MONTHS * vacationDaysNumber * 10000) /10000;
    }

    public double calculateVacationPayWithDates(double averageSalary, List<LocalDate> vacationDates) {
        validateValues(averageSalary, 1);

        int workingDays = 0;
        for (LocalDate date : vacationDates) {
            if (!isWeekendOrHoliday(date)) {
                workingDays++;
            }
        }

        return (double) round(averageSalary / AVERAGE_DAYS_IN_MONTHS * workingDays * 10000) / 10000;
    }

    private boolean isWeekendOrHoliday(LocalDate date) {
        if (date.getDayOfWeek() == DayOfWeek.SATURDAY ||
                date.getDayOfWeek() == DayOfWeek.SUNDAY) {
            return true;
        }

        return (date.getMonthValue() == 1 && date.getDayOfMonth() <= 8) ||
                (date.getMonthValue() == 2 && date.getDayOfMonth() == 23) ||
                (date.getMonthValue() == 3 && date.getDayOfMonth() == 8) ||
                (date.getMonthValue() == 5 && (date.getDayOfMonth() == 1 || date.getDayOfMonth() == 9)) ||
                (date.getMonthValue() == 6 && date.getDayOfMonth() == 12) ||
                (date.getMonthValue() == 11 && date.getDayOfMonth() == 4);
    }

    private void validateValues(double averageSalary, int vacationDaysNumber){
        if (averageSalary <= 0) {
            throw new IllegalArgumentException("Salary must be positive");
        }
        if (vacationDaysNumber <= 0) {
            throw new IllegalArgumentException("Number of days must positive");
        }
    }
}
