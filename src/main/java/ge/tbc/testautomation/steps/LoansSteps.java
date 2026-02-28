package ge.tbc.testautomation.steps;

import com.microsoft.playwright.Page;
import ge.tbc.testautomation.pages.LoansPage;
import io.qameta.allure.Step;

import static org.testng.Assert.assertTrue;

public class LoansSteps {

    private final LoansPage loansPage;

    public LoansSteps(Page page, boolean isMobile) {
        this.loansPage = new LoansPage(page, isMobile);
    }

    @Step("Enter loan amount: '{amount}'")
    public LoansSteps inputLoanAmount(String amount) {
        loansPage.loanAmountInputByLoan.fill(amount);
        return this;
    }

    @Step("Enter loan period: '{period}' months")
    public LoansSteps inputLoanPeriod(String period) {
        loansPage.loanPeriodInputByLoan.fill(period);
        return this;
    }

    @Step("Verify monthly payment is greater than minimum payment for amount '{loanAmount}' over '{loanPeriod}' months")
    public LoansSteps assertCorrectLoan(String loanAmount, String loanPeriod) {
        String monthlyPaymentText = loansPage.monthlyPayment.textContent().trim();
        double monthlyPayment = Double.parseDouble(monthlyPaymentText.replaceAll("[^0-9.]", ""));
        double amount = Double.parseDouble(loanAmount);
        double period = Double.parseDouble(loanPeriod);
        double minimumPayment = amount / period;
        assertTrue(monthlyPayment > minimumPayment);
        return this;
    }

    @Step("Navigate to 'By Income' loan section")
    public LoansSteps navigateToLoansByAmount() {
        loansPage.byIncomeSection.click();
        return this;
    }

    @Step("Enter income-based loan amount: '{amount}'")
    public LoansSteps loanAmountByIncome(String amount) {
        loansPage.loanAmountInputByIncome.fill(amount);
        return this;
    }

    @Step("Enter income-based loan period: '{period}' months")
    public LoansSteps loanPeriodByIncome(String period) {
        loansPage.loanPeriodInputByIncome.fill(period);
        return this;
    }
}