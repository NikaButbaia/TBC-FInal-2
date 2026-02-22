package ge.tbc.testautomation.steps;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import ge.tbc.testautomation.pages.AbroadTransferPage;
import ge.tbc.testautomation.utils.Utils;

import java.math.BigDecimal;

public class AbroadTransferSteps {
    private final AbroadTransferPage page;

    public AbroadTransferSteps(Page page){
        this.page = new AbroadTransferPage(page);
    }

    public AbroadTransferSteps inputMoney(String amount){
        page.moneyInputLocator().fill(amount);
        return this;
    }
    public AbroadTransferSteps inputCurrency(String currency){
        page.inputCurrencyDropdown().click();
        page.currencyDropdown()
                .filter(new Locator.FilterOptions().setHasText(currency))
                .click(new Locator.ClickOptions().setForce(true));
        return this;
    }

    public AbroadTransferSteps outputCurrency(String currency){
        page.outputCurrencyDropdown().click();
        page.currencyDropdown()
                .filter(new Locator.FilterOptions().setHasText(currency))
                .click(new Locator.ClickOptions().setForce(true));
        return this;
    }
    public AbroadTransferSteps verifyConversion() {
        BigDecimal input = Utils.extractNumber(page.moneyInputLocator().inputValue());
        BigDecimal output = Utils.extractNumber(page.moneyOutputLocator().inputValue());
        BigDecimal rate = Utils.extractRate(page.currencyConvertion().textContent());
        Utils.verifyConversion(input, output);

        return this;
    }
}
