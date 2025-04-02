package com.neoflex.vacation_pay_calculator.services;

import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

import static java.lang.Math.round;

@Service
public class VacationPayService {
    private static final double AVERAGE_DAYS_IN_MONTHS = 29.3;
    public double calculateByDays(double averageSalary, int vacationDaysNumber){
        return (double) round(averageSalary / AVERAGE_DAYS_IN_MONTHS * vacationDaysNumber * 100) /100;
    }

    public double calculateByDates(double averageSalary, List<LocalDate> vacationDates) {
        int workingDays = 0;
        for (LocalDate date : vacationDates) {
            if (!isWeekendOrHoliday(date)) {
                workingDays++;
            }
        }

        return (double) round(averageSalary / AVERAGE_DAYS_IN_MONTHS * workingDays * 100) / 100;
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
}
