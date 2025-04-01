package com.neoflex.vacationpaycalculator.controllers;

import com.neoflex.vacationpaycalculator.dto.VacationPayRequest;
import com.neoflex.vacationpaycalculator.dto.VacationPayResponse;
import com.neoflex.vacationpaycalculator.services.VacationPayService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VacationPayController {
    private final VacationPayService vacationPayService;

    public VacationPayController(VacationPayService vacationPayService) {
        this.vacationPayService = vacationPayService;
    }

    @GetMapping("/calculate")
    public VacationPayResponse calculate(@Valid @ModelAttribute VacationPayRequest request){
        double vacationPay;
        if (request.getVacationDates() != null && !request.getVacationDates().isEmpty()) {
            vacationPay = vacationPayService.calculateVacationPayWithDates(
                    request.getAverageSalary(), request.getVacationDates());
        } else {
            vacationPay = vacationPayService.calculateVacationPay(
                    request.getAverageSalary(), request.getVacationDaysNumber());
        }
        return new VacationPayResponse(vacationPay);
    }
}
