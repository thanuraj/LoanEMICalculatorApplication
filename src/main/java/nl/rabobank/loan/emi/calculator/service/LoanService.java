package nl.rabobank.loan.emi.calculator.service;


import nl.rabobank.loan.emi.calculator.dto.LoanRequest;
import nl.rabobank.loan.emi.calculator.dto.LoanResponse;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class LoanService {

    public Mono<LoanResponse> calculateEmi(LoanRequest request) {

        return Mono.fromSupplier(() -> {

            double P = request.getLoanAmount();
            double annualRate = request.getYearlyInterestRate();
            int N = request.getLoanTermMonths();

            double R = annualRate / 12 / 100;  // monthly interest

            // EMI Calculation
            double numerator = P * R * Math.pow(1 + R, N);
            double denominator = Math.pow(1 + R, N) - 1;

            double emi = numerator / denominator;

            return new LoanResponse(emi);
        });
    }
}
