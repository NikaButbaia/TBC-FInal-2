package ge.tbc.testautomation.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class LoansPage {

    private final Page page;
    private final boolean isMobile;

    public Locator loanAmountInputByLoan, loanPeriodInputByLoan, monthlyPayment, byIncomeSection, loanAmountInputByIncome, loanPeriodInputByIncome;

    public LoansPage(Page page, boolean isMobile) {
        this.page = page;
        this.isMobile = isMobile;
        this.loanAmountInputByLoan = resolveLoanAmountInput();
        this.loanPeriodInputByLoan = resolveLoanPeriodInput();
        this.monthlyPayment = page.locator(".tbcx-pw-calculated-info__number--new").first();
        this.byIncomeSection = page.getByText("შემოსავლით");
        this.loanAmountInputByIncome = resolveIncomeAmountInput();
        this.loanPeriodInputByIncome = resolveIncomePeriodInput();
    }

    private Locator resolveLoanAmountInput() {
        if (isMobile) {
            return page.locator("input[type='number']").first();
        } else {
            return page.locator("input[type='number'][min='200']");
        }
    }

    private Locator resolveLoanPeriodInput() {
        if (isMobile) {
            return page.locator("input.input[type='number']").nth(1);
        } else {
            return page.locator("input[type='number'][min='3']");
        }
    }

    private Locator resolveIncomeAmountInput() {
        if (isMobile) {
            return page.locator("input[type='number']").first();
        } else {
            return page.locator("input[type='number']").last();
        }
    }

    private Locator resolveIncomePeriodInput() {
        if (isMobile) {
            return page.locator("input[type='number']").nth(1);
        } else {
            return page.locator("input[type='number']").last();
        }
    }
}