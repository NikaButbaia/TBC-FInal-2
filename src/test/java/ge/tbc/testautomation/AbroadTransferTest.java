package ge.tbc.testautomation;

import org.testng.annotations.Test;

import static ge.tbc.testautomation.data.Constants.*;

public class AbroadTransferTest extends BaseTest {
    @Test
    public void currencyTest(){
        utils.navigateToPage(TBC_Abroad_Transfer_URL);

        abroadTransferSteps
                .inputCurrency(GEL)
                .outputCurrency(USD)
                .inputMoney(Money)
                .verifyConversion();
    }
}
