package com.neoflex.vacation_pay_calculator.dto;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;
import java.util.List;

public class VacationPayRequest {
    @NotNull(message = "Average salary must be provided")
    @Positive(message = "Average salary must be positive")
    private double salary;
    @Positive(message = "Number of days must be positive")
    private Integer daysNumber;
    private List<LocalDate> dates;
    @AssertTrue(message = "Either number of days or dates of vacation must be provided")
    public boolean isValuesValid() {
        return daysNumber != null || (dates != null && !dates.isEmpty());
    }

    public VacationPayRequest() {
    }

    public VacationPayRequest(double averageSalary, int vacationDaysNumber) {
        this.salary = averageSalary;
        this.daysNumber = vacationDaysNumber;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public Integer getDaysNumber() {
        return daysNumber;
    }

    public void setDaysNumber(Integer daysNumber) {
        this.daysNumber = daysNumber;
    }

    public List<LocalDate> getDates() {
        return dates;
    }

    public void setDates(List<LocalDate> dates) {
        this.dates = dates;
    }

    public boolean hasDates(){
        return dates != null;
    }
}
