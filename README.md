## Run

Note: can change expected Java 17 version in `build.gradle.kts`.

#### IntelliJ

Open `build.gradle.kts` in IntelliJ as Gradle project and Run/Debug.

#### Gradle

```shell
./gradlew bootRun
```

#### Manually

```shell
./gradlew build
cd build/libs
java -jar aggregator-0.0.1-SNAPSHOT.jar
```

## Database

http://localhost:8080/h2-console

* JDBC URL: jdbc:h2:mem:aggregatordb
* User Name: username
* Password: password

#### Select all aggregates

```sql
SELECT
	f.id AS fast_id,
	f.status AS fast_status,
	f.monthly_payment_amount AS fast_monthly_payment_amount,
	f.total_repayment_amount AS fast_total_repayment_amount,
	f.number_of_payments AS fast_number_of_payments,
	f.annual_percentage_rate AS fast_annual_percentage_rate,
	f.first_repayment_date AS fast_first_repayment_date,
	s.id AS solid_id,
	s.status AS solid_status,
	s.monthly_payment_amount AS solid_monthly_payment_amount,
	s.total_repayment_amount AS solid_total_repayment_amount,
	s.number_of_payments AS solid_number_of_payments,
	s.annual_percentage_rate AS solid_annual_percentage_rate,
	s.first_repayment_date AS solid_first_repayment_date
FROM aggregate a
	LEFT JOIN application f ON a.fast_id = f.id
	LEFT JOIN application s ON a.solid_id = s.id;
```

## Examples

Create aggregate:
```
POST http://localhost:8080/aggregates
{
	"phone": "+37126000000",
	"email": "john.smith@example.com",
	"monthlyIncome": 150.0,
	"monthlyExpenses": 10.0,
	"monthlyCreditLiabilities": 10.0,
	"maritalStatus": "MARRIED",
	"dependents": 2,
	"agreeToDataSharing": true,
	"agreeToBeScored": true,
	"amount": 150.0
}
```

Get aggregate `id=1`:
```
GET http://localhost:8080/aggregates/1
```
Note: call multiple times until `finalResult: true`.