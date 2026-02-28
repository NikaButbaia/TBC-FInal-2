package ge.tbc.testautomation.uitests;

import ge.tbc.testautomation.dataproviders.AbroadTransferDataProvider;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Feature("Abroad Money Transfer")
public class AbroadTransferTest extends BaseTest {

    @BeforeClass
    public void navigateToAbroadTransferPage() {
        mainSteps
                .hoverOnForMe()
                .moneyTransferPage();
    }

    @Test(
            dataProvider = "transferData",
            dataProviderClass = AbroadTransferDataProvider.class,
            description = "Verify currency conversion between {0} and {1} for amount {2}"
    )
    @Story("User converts currency using the exchange rate calculator")
    @Description("Selects source and target currencies, inputs a transfer amount, " +
            "and verifies that the converted output is valid and non-zero")
    @Severity(SeverityLevel.CRITICAL)
    public void currencyTest(String from, String to, String amount) {
        abroadTransferSteps
                .inputCurrency(from)
                .outputCurrency(to)
                .inputMoney(amount)
                .verifyConversion();
    }
}