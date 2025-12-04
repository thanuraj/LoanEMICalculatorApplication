package nl.rabobank.loan.emi.calculator.controller;

import nl.rabobank.loan.emi.calculator.dto.LoanRequest;
import nl.rabobank.loan.emi.calculator.dto.LoanResponse;
import nl.rabobank.loan.emi.calculator.service.LoanService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import static org.mockito.ArgumentMatchers.any;

class LoanControllerTest {

    private LoanService loanService;
    private WebTestClient webTestClient;

    @BeforeEach
    void setup() {
        loanService = Mockito.mock(LoanService.class);

        LoanEMIController controller = new LoanEMIController(loanService);

        webTestClient = WebTestClient.bindToController(controller).build();
    }

    @Test
    void testCalculateEmiEndpoint() {
        Mockito.when(loanService.calculateEmi(any()))
                .thenReturn(Mono.just(new LoanResponse(1234.56)));

        LoanRequest request = new LoanRequest();
        request.setLoanAmount(500000.0);   // valid
        request.setYearlyInterestRate(12.0); // valid (<= 100)
        request.setLoanTermMonths(12);

        webTestClient.post()
                .uri("/api/loan/emi")
                .bodyValue(request)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.emi").isEqualTo(1234.56);
    }
}
