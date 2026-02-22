package ge.tbc.testautomation;

import ge.tbc.testautomation.dataproviders.AbroadTransferDataProvider;
import org.testng.annotations.Test;

import static ge.tbc.testautomation.data.Constants.*;

public class AbroadTransferTest extends BaseTest {
    @Test(
            dataProvider = "transferData",
            dataProviderClass = AbroadTransferDataProvider.class
    )
    public void currencyTest(String from, String to, String amount){

        utils.navigateToPage(TBC_Abroad_Transfer_URL);

        abroadTransferSteps
                .inputCurrency(from)
                .outputCurrency(to)
                .inputMoney(amount)
                .verifyConversion();
    }
}
