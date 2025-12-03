package nl.rabobank.loan.emi.calculator.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class LoanRequest {

    @NotNull(message = "Loan amount cannot be null")
    @Min(value = 1, message = "Loan amount must be greater than zero")
    private Double loanAmount;

    @NotNull(message = "Interest rate cannot be null")
    @Min(value = 1, message = "Interest rate must be greater than zero")
    private Double yearlyInterestRate;

    @NotNull(message = "Loan term cannot be null")
    @Min(value = 1, message = "Loan term must be at least 1 month")
    private Integer loanTermMonths;
}
