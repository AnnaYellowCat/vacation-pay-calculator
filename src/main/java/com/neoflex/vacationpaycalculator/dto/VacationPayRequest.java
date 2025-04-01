package com.neoflex.vacationpaycalculator.dto;

import jakarta.validation.constraints.Positive;
import lombok.Getter;

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
