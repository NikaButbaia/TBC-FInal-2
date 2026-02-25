package ge.tbc.testautomation.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class LoansPage {
    private final Page page;
    private final boolean isMobile;

    public LoansPage(Page page, boolean isMobile) {
        this.page = page;
        this.isMobile = isMobile;
    }

    public Locator loanAmountInputByLoan(){
        if (isMobile) {
            return page.locator("input[type='number']").first();
        } else {
            return page.locator("input[type='number'][min='200']");
        }
    }

    public Locator loanPeriodInputByLoan(){
        if (isMobile) {
            return page.locator("input.input[type='number']").nth(1);
        } else {
            return page.locator("input[type='number'][min='3']");
        }
    }

    public Locator monthlyPayment(){
        return page.locator(".tbcx-pw-calculated-info__number--new").first();
    }

    public Locator byIncomeSection(){
        return page.getByText("შემოსავლით");
    }

    public Locator loanAmountInputByIncome(){
        if (isMobile) {
            return page.locator(".input.ng-untouched ").first();
        }else {
            return page.locator("input[type='number']").first();
        }
    }
    public Locator loanPeriodInputByIncome(){
        return page.locator(".input[type='number']");
    }

}