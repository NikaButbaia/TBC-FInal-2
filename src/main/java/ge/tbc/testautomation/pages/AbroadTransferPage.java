package ge.tbc.testautomation.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class AbroadTransferPage {

    private final Page page;

    public Locator moneyInput, moneyOutput, inputCurrencyDropdown, outputCurrencyDropdown, currencyConversion, currencyDropdown;

    public AbroadTransferPage(Page page) {
        this.page = page;
        this.moneyInput = page.locator("input[type='text']").first();
        this.moneyOutput = page.locator("input[type='text']").nth(1);
        this.inputCurrencyDropdown = page.locator("button.tbcx-field.tbcx-bg-color-high").first();
        this.outputCurrencyDropdown = page.locator("button.tbcx-field.tbcx-bg-color-high").nth(1);
        this.currencyConversion = page.locator(".tbcx-pw-exchange-rates-calculator__description");
        this.currencyDropdown = page.locator(".tbcx-dropdown-popover-item__title");
    }
}