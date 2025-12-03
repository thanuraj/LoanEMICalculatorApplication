package nl.rabobank.loan.emi.calculator.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoanResponse {
    private Double emi;
}

