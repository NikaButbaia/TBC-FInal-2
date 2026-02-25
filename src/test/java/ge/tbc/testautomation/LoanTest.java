package ge.tbc.testautomation;

import ge.tbc.testautomation.dataproviders.LoanDataProvider;
import org.testng.annotations.Test;

import static ge.tbc.testautomation.data.Constants.*;

public class LoanTest extends BaseTest{
    @Test(
            dataProvider = "loanData",
            dataProviderClass = LoanDataProvider.class
    )
    public void loanTestBasedOnAmount(String amount, String period){

        utils.navigateToPage(TBC_Loan_URL);

        loansSteps
                .inputLoanAmount(amount)
                .inputLoanPeriod(period)
                .assertCorrectLoan(amount, period);
    }
    @Test(
            dataProvider = "loanData",
            dataProviderClass = LoanDataProvider.class
    )
    public void loanTestBasedOnIncome(String amount, String period){
        utils.navigateToPage(TBC_Loan_URL);

        loansSteps
                .navigateToLoansByAmount()
                .loanAmountByIncome(amount)
                .loanPeriodByIncome(period)
                .assertCorrectLoan(amount, period);
    }
}
