package nl.rabobank.loan.emi.calculator.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class LoanRequest {

    @NotNull(message = "Loan amount cannot be null")
    @Positive(message = "Loan amount must be a positive number")
    private Double loanAmount;

    @NotNull(message = "Interest rate cannot be null")
    @Positive(message = "Interest rate must be a positive number")
    @DecimalMax(value = "100", message = "Interest rate must be less than or equal to 100")
    private Double yearlyInterestRate;

    @NotNull(message = "Loan term cannot be null")
    @Positive(message = "Loan term must be a positive number")
    @Max(value = 30, message = "Loan term cannot exceed 30 months")
    private Integer loanTermMonths;
}
