# FuelExpenseService
**Application url:**
https://fuel-expense-service.cfapps.io/api/expense?date=2018-02-14&type=ULSP&mpg=60&mileage=10.0
## Building and running the application
### Building the artifact
*mnv clean package* from the project folder
### Running integration tests
*mvn clean verify -P int-test* from the project folder
### Running the service locally
- After building the artifact
- *java -jar target/FuelExpenseService-0.0.1-SNAPSHOT.jar* 
### Using the service
- After the service have been started
- Open the following url in the browser: *http://localhost:8080/api/expense?date=2018-02-14&type=ULSP&mpg=60.0&mileage=100.23*
## Design decisions
- Pumping price calculation is implemented according to this:
"VAT is applied after fuel duty, so, for example, the pump price of a
 litre of petrol currently reflects the pre-tax price plus 57.95p for fuel duty plus 
20 per cent VAT on the pre-tax price and a further 11.59p for VAT at 20 per cent on fuel duty."
- BigDecimals were used for precision
- Data have been converted to inserts for the ease of use
## Possible improvements
- Change formatting of the response body to include currency
- Caching can be introduced on DAO level
- Parallel calls can be introduced on the FuelExpenseService level, so far very limited gain
- CSV upload/ data feed mechanism to keep data up to date
- Error handling page
- Adding code coverage to build
- Adding mutation testing to build
- FuelPriceDaoIml breaks SRP
- Data validation happens only on the rest controller level, might need to be improved
- Some of the validation errors result in 500 hence some of the strange MVC test
- Improved logging