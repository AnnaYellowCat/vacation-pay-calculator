package com.neoflex.vacation_pay_calculator.dto;

import jakarta.validation.constraints.Positive;

import java.time.LocalDate;
import java.util.List;

public class VacationPayRequest {
    @Positive(message = "Salary must be positive")
    private double averageSalary;
    @Positive(message = "Number of days must be positive")
    private Integer vacationDaysNumber;
    private List<LocalDate> vacationDates;

    public VacationPayRequest(double averageSalary, int vacationDaysNumber){
        this.averageSalary = averageSalary;
        this.vacationDaysNumber = vacationDaysNumber;
    }

    public double getAverageSalary(){
        return averageSalary;
    }

    public int getVacationDaysNumber(){
        return vacationDaysNumber;
    }

    public List<LocalDate> getVacationDates(){
        return vacationDates;
    }
}
