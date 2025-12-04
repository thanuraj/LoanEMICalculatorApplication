package nl.rabobank.loan.emi.calculator.service;


import lombok.extern.slf4j.Slf4j;
import nl.rabobank.loan.emi.calculator.dto.LoanRequest;
import nl.rabobank.loan.emi.calculator.dto.LoanResponse;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Slf4j
@Service
public class LoanService {

    public Mono<LoanResponse> calculateEmi(LoanRequest request) {
        log.info("Calculating emi!!!");
        return Mono.fromSupplier(() -> {
            double emi = computeEmi(
                    request.getLoanAmount(),
                    request.getYearlyInterestRate(),
                    request.getLoanTermMonths()
            );

            return new LoanResponse(emi);
        });
    }

    /**
     * Computes EMI using standard EMI formula
     */
    private double computeEmi(double principal, double yearlyRate, int termMonths) {

        double monthlyRate = yearlyRate / 12 / 100; // convert to monthly interest rate

        double numerator = principal * monthlyRate * Math.pow(1 + monthlyRate, termMonths);
        double denominator = Math.pow(1 + monthlyRate, termMonths) - 1;

        return numerator / denominator;
    }
}
