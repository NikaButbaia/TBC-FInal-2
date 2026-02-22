package ge.tbc.testautomation.steps;

import com.microsoft.playwright.Page;
import ge.tbc.testautomation.pages.LoansPage;

import static org.testng.Assert.assertTrue;

public class LoansSteps {
    private final LoansPage page;

    public LoansSteps(Page page, boolean isMobile) {
        this.page = new LoansPage(page, isMobile);
    }

    public LoansSteps inputLoanAmount(String amount){
        page.loanAmountInputByLoan().fill(amount);
        return this;
    }

    public LoansSteps inputLoanPeriod(String period){
        page.loanPeriodInputByLoan().fill(period);
        return this;
    }

    public LoansSteps assertCorrectLoan(String loanAmount, String loanPeriod){
        String monthlyPaymentText = page.monthlyPayment().textContent().trim();
        double monthlyPayment = Double.parseDouble(monthlyPaymentText.replaceAll("[^0-9.]", ""));
        double amount = Double.parseDouble(loanAmount);
        double period = Double.parseDouble(loanPeriod);
        double minimumPayment = amount / period;
        assertTrue(monthlyPayment > minimumPayment);
        return this;
    }
}