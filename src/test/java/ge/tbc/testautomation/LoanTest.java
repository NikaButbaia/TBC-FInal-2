package ge.tbc.testautomation;

import org.testng.annotations.Test;

import static ge.tbc.testautomation.data.Constants.*;

public class LoanTest extends BaseTest{
    @Test
    public void loanTest(){
        utils.navigateToPage(TBC_Loan_URL);

        loansSteps
                .inputLoanAmount(LOAN_AMOUNT)
                .inputLoanPeriod(LOAN_PERIOD)
                .assertCorrectLoan(LOAN_AMOUNT, LOAN_PERIOD);
    }
}
