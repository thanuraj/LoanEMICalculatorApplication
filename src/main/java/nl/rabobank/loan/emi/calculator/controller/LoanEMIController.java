package nl.rabobank.loan.emi.calculator.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import nl.rabobank.loan.emi.calculator.dto.LoanRequest;
import nl.rabobank.loan.emi.calculator.dto.LoanResponse;
import nl.rabobank.loan.emi.calculator.service.LoanService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/loan")
@RequiredArgsConstructor
public class LoanEMIController {

    private final LoanService loanService;

    @PostMapping("/emi")
    public Mono<LoanResponse> calculateEmi(@Valid @RequestBody LoanRequest request) {
        return loanService.calculateEmi(request);
    }
}
