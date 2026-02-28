package ge.tbc.testautomation.uitests;

import ge.tbc.testautomation.dataproviders.LoanDataProvider;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Feature("Consumer Loan Calculator")
public class LoanTest extends BaseTest {

    @BeforeClass
    public void navigateToLoanPage() {
        mainSteps
                .hoverOnForMe()
                .navigateToLoansPage()
                .clickOnLoansPage();
    }

    @Test(
            dataProvider = "loanData",
            dataProviderClass = LoanDataProvider.class
    )
    @Story("User calculates loan by amount")
    @Description("Enters a loan amount and period into the calculator and verifies " +
            "that the monthly payment returned is greater than the minimum possible payment")
    @Severity(SeverityLevel.CRITICAL)
    public void loanTestBasedOnAmount(String amount, String period) {
        loansSteps
                .inputLoanAmount(amount)
                .inputLoanPeriod(period)
                .assertCorrectLoan(amount, period);
    }

    @Test(
            dataProvider = "loanData",
            dataProviderClass = LoanDataProvider.class
    )
    @Story("User calculates loan by income")
    @Description("Navigates to the income-based loan section, enters an income amount and period, " +
            "and verifies that the monthly payment returned is greater than the minimum possible payment")
    @Severity(SeverityLevel.CRITICAL)
    public void loanTestBasedOnIncome(String amount, String period) {
        loansSteps
                .navigateToLoansByAmount()
                .loanAmountByIncome(amount)
                .loanPeriodByIncome(period)
                .assertCorrectLoan(amount, period);
    }
}