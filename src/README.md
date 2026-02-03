# Build Challenge - Java Maven

## Requirements
- Java 17+
- Maven

## Run Tests
```bash
mvn test
```

## Run Assignment 1 Demo
Run:
`com.buildchallenge.assignment1.BankDemo`

## Run Assignment 2 Demo
Run:
`com.buildchallenge.assignment2.GradeBookDemo`

## Notes / Assumptions
### Assignment 1
- Checking: first 10 transactions are free, after that $2.50 fee per transaction
- Savings: minimum balance $100 maintained after withdrawals/transfers
- Savings: max 5 withdrawals per month
- Transactions store SUCCESS/FAILED + reason
- Monthly interest is applied at 2% for savings accounts

### Assignment 2
- Category weights follow problem statement
- If a category has no assignments, it is excluded from calculation
- GPA uses letter grade mapping and is weighted by credit hours
