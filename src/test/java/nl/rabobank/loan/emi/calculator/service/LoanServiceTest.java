package nl.rabobank.loan.emi.calculator.service;

import nl.rabobank.loan.emi.calculator.dto.LoanRequest;
import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;

class LoanServiceTest {

    private final LoanService loanService = new LoanService();

    @Test
    void testCalculateEmi() {
        // Arrange
        LoanRequest request = new LoanRequest();
        request.setLoanAmount(500000.0);
        request.setYearlyInterestRate(12.0);
        request.setLoanTermMonths(60);

        // Expected EMI manually calculated
        double expectedEmi = computeExpectedEmi(500000.0, 12.0, 60);

        // Act + Assert using StepVerifier
        StepVerifier.create(loanService.calculateEmi(request))
                .expectNextMatches(response -> {
                    double actual = response.getEmi();
                    return Math.abs(actual - expectedEmi) < 0.01; // tolerance
                })
                .verifyComplete();
    }

    /**
     * Local helper to compute expected EMI for test validation.
     */
    private double computeExpectedEmi(double principal, double yearlyRate, int termMonths) {

        double monthlyRate = yearlyRate / 12 / 100;

        double numerator = principal * monthlyRate * Math.pow(1 + monthlyRate, termMonths);
        double denominator = Math.pow(1 + monthlyRate, termMonths) - 1;

        return numerator / denominator;
    }
}
