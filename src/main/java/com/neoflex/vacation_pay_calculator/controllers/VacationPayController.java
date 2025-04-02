package com.neoflex.vacation_pay_calculator.controllers;

import com.neoflex.vacation_pay_calculator.dto.VacationPayRequest;
import com.neoflex.vacation_pay_calculator.dto.VacationPayResponse;
import com.neoflex.vacation_pay_calculator.services.VacationPayService;
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
    public VacationPayResponse calculate(@Valid @ModelAttribute VacationPayRequest request) {
        double vacationPay = request.hasDates()
                ? vacationPayService.calculateByDates(request.getSalary(), request.getDates())
                : vacationPayService.calculateByDays(request.getSalary(), request.getDaysNumber());
        return new VacationPayResponse(vacationPay);
    }
}
