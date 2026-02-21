package ge.tbc.testautomation.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class LoansPage {
    private final Page page;

    public LoansPage(Page page) {
        this.page = page;
    }
    public Locator loanAmountInputByLoan(){
        return page.locator("input[type='number'][min='200']");
    }
    public Locator loanPeriodInputByLoan(){
        return page.locator("input[type='number'][min='3']");
    }
    public Locator monthlyPayment(){
        return page.locator(".tbcx-pw-calculated-info__number--new").first();
    }
}
