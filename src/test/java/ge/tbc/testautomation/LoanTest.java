package ge.tbc.testautomation;

import ge.tbc.testautomation.dataproviders.LoanDataProvider;
import org.testng.annotations.Test;

import static ge.tbc.testautomation.data.Constants.*;

public class LoanTest extends BaseTest{
    @Test(
            dataProvider = "loanData",
            dataProviderClass = LoanDataProvider.class
    )
    public void loanTest(String amount, String period){

        utils.navigateToPage(TBC_Loan_URL);

        loansSteps
                .inputLoanAmount(amount)
                .inputLoanPeriod(period)
                .assertCorrectLoan(amount, period);
    }
}
