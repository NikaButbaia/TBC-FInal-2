package ge.tbc.testautomation.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class AbroadTransferPage {
    private final Page page;

    public AbroadTransferPage(Page page){
        this.page=page;
    }

    public Locator moneyInputLocator(){
        return page.locator("input[type='text']").first();
    }

    public Locator moneyOutputLocator(){
        return page.locator("input[type='text']").nth(1);
    }

    public Locator inputCurrencyDropdown(){
        return page.locator("button.tbcx-field.tbcx-bg-color-high").first();
    }

    public Locator outputCurrencyDropdown(){
        return page.locator("button.tbcx-field.tbcx-bg-color-high").nth(1);
    }

    public Locator currencyConvertion(){
        return page.locator(".tbcx-pw-exchange-rates-calculator__description");
    }

    public Locator currencyDropdown(){
        return page.locator(".tbcx-dropdown-popover-item__title");
    }
}
