package com.neoflex.vacation_pay_calculator.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class VacationPayResponse {
    private double vacationPay;

    public VacationPayResponse(double vacationPay) {
        this.vacationPay = vacationPay;
    }

    public double getVacationPay(){
        return vacationPay;
    }
}
