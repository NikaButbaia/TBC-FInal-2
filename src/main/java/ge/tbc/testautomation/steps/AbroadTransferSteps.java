package ge.tbc.testautomation.steps;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import ge.tbc.testautomation.pages.AbroadTransferPage;
import ge.tbc.testautomation.utils.Utils;
import io.qameta.allure.Step;

import java.math.BigDecimal;

public class AbroadTransferSteps {

    private final AbroadTransferPage abroadTransferPage;

    public AbroadTransferSteps(Page page) {
        this.abroadTransferPage = new AbroadTransferPage(page);
    }

    @Step("Fill in transfer amount: '{amount}'")
    public AbroadTransferSteps inputMoney(String amount) {
        abroadTransferPage.moneyInput.scrollIntoViewIfNeeded();
        abroadTransferPage.moneyInput.fill(amount);
        return this;
    }

    @Step("Select source currency from dropdown: '{currency}'")
    public AbroadTransferSteps inputCurrency(String currency) {
        abroadTransferPage.inputCurrencyDropdown.scrollIntoViewIfNeeded();
        abroadTransferPage.inputCurrencyDropdown.click();
        Locator option = abroadTransferPage.currencyDropdown
                .filter(new Locator.FilterOptions().setHasText(currency));
        option.scrollIntoViewIfNeeded();
        option.click();
        return this;
    }

    @Step("Select target currency from dropdown: '{currency}'")
    public AbroadTransferSteps outputCurrency(String currency) {
        abroadTransferPage.outputCurrencyDropdown.click();
        Locator option = abroadTransferPage.currencyDropdown
                .filter(new Locator.FilterOptions().setHasText(currency));
        option.scrollIntoViewIfNeeded();
        option.click();
        return this;
    }

    @Step("Assert converted output amount is greater than zero and mathematically valid")
    public AbroadTransferSteps verifyConversion() {
        BigDecimal input = Utils.extractNumber(abroadTransferPage.moneyInput.inputValue());
        BigDecimal output = Utils.extractNumber(abroadTransferPage.moneyOutput.inputValue());
        Utils.verifyConversion(input, output);
        return this;
    }
}