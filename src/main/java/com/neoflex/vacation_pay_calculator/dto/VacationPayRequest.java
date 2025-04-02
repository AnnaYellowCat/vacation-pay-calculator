package com.neoflex.vacation_pay_calculator.dto;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;
import java.util.List;

public class VacationPayRequest {
    @Positive(message = "Salary must be positive")
    private double averageSalary;
    @Positive(message = "Number of days must be positive")
    private Integer vacationDaysNumber;
    private List<LocalDate> vacationDates;

    public VacationPayRequest() {
    }

    public VacationPayRequest(double averageSalary, int vacationDaysNumber) {
        this.averageSalary = averageSalary;
        this.vacationDaysNumber = vacationDaysNumber;
    }

    public double getAverageSalary() {
        return averageSalary;
    }

    public void setAverageSalary(double averageSalary) {
        this.averageSalary = averageSalary;
    }

    public Integer getVacationDaysNumber() {
        return vacationDaysNumber;
    }

    public void setVacationDaysNumber(Integer vacationDaysNumber) {
        this.vacationDaysNumber = vacationDaysNumber;
    }

    public List<LocalDate> getVacationDates() {
        return vacationDates;
    }

    public void setVacationDates(List<LocalDate> vacationDates) {
        this.vacationDates = vacationDates;
    }

    @AssertTrue(message = "Either vacationDaysNumber or vacationDates must be provided")
    public boolean isVacationInputValid() {
        return vacationDaysNumber != null || (vacationDates != null && !vacationDates.isEmpty());
    }
}
