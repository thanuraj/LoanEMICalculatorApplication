1.Loan EMI Calculator – Spring Boot (WebFlux, Java 17)
This project is a reactive Spring Boot WebFlux application that exposes an API to calculate the Equated Monthly Installment (EMI) for a loan based on:

Loan Amount
Yearly Interest Rate
Loan Term (in months)

The system uses Java 17, Spring WebFlux, Jakarta Validation, and includes exception handling, reactive programming (Mono), and Java Streams where useful.

2.Technologies Used
Java 17
Spring Boot 3
Spring WebFlux
Jakarta Validation
Reactor Core (Mono)
Lombok
Maven

* Spring security is used to secure the endpoints. For now it'll allow all URL patterns. In Futue we can add authentocation like JWT, Oauth etc.

3.Running the Project
* Build the application
mvn clean install

* Run the application
mvn spring-boot:run

* Server will start at.
http://localhost:8080

4.API Endpoint
POST /api/loan/emi

Request Body Example
    {
    "loanAmount": 500000,
    "yearlyInterestRate": 12,
    "loanTermMonths": 30
    }

Response Example
{
"emi": 11122.210836
}

5.Testing via cURL
curl -X POST http://localhost:8080/api/loan/emi \
-H "Content-Type: application/json" \
-d '{"loanAmount":500000,"yearlyInterestRate":12,"loanTermMonths":30}'