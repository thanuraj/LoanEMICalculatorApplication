package nl.rabobank.loan.emi.calculator.exception;

import nl.rabobank.loan.emi.calculator.controller.LoanEMIController;
import nl.rabobank.loan.emi.calculator.dto.LoanRequest;
import nl.rabobank.loan.emi.calculator.service.LoanService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.test.web.reactive.server.WebTestClient;


class GlobalExceptionHandlerTest {

    private WebTestClient webTestClient;
    private LoanService loanService;

    @BeforeEach
    void setup() {
        loanService = Mockito.mock(LoanService.class);
        LoanEMIController controller = new LoanEMIController(loanService);

        webTestClient = WebTestClient
                .bindToController(controller)
                .controllerAdvice(new GlobalExceptionHandler())
                .build();
    }

    @Test
    void testValidationErrorResponse() {
        LoanRequest invalidRequest = new LoanRequest();
        invalidRequest.setLoanAmount(-1.0);
        invalidRequest.setYearlyInterestRate(0.0);
        invalidRequest.setLoanTermMonths(0);

        webTestClient.post()
                .uri("/api/loan/emi")
                .bodyValue(invalidRequest)
                .exchange()
                .expectStatus().isBadRequest()
                .expectBody()
                .jsonPath("$").isArray();
    }

    @Test
    void testGenericExceptionHandling() {
        Mockito.when(loanService.calculateEmi(Mockito.any()))
                .thenThrow(new RuntimeException("Test exception"));

        LoanRequest request = new LoanRequest();
        request.setLoanAmount(10000.0);
        request.setYearlyInterestRate(10.0);
        request.setLoanTermMonths(12);

        webTestClient.post()
                .uri("/api/loan/emi")
                .bodyValue(request)
                .exchange()
                .expectStatus().is5xxServerError()
                .expectBody(String.class)
                .isEqualTo("Test exception");
    }
}
