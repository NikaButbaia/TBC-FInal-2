package ge.tbc.testautomation.dataproviders;

import ge.tbc.testautomation.data.FakerDataGenerator;
import org.testng.annotations.DataProvider;

public class AbroadTransferDataProvider {

    @DataProvider(name = "transferData")
    public static Object[][] transferData() {
        return new Object[][]{
                {
                        FakerDataGenerator.randomCurrency(),
                        FakerDataGenerator.randomDifferentCurrency("GEL"),
                        FakerDataGenerator.randomAmount(100, 200)
                },
                {
                        FakerDataGenerator.randomCurrency(),
                        FakerDataGenerator.randomDifferentCurrency("USD"),
                        FakerDataGenerator.randomAmount(200, 300)
                },
                {
                        FakerDataGenerator.randomCurrency(),
                        FakerDataGenerator.randomDifferentCurrency("EUR"),
                        FakerDataGenerator.randomAmount(300, 400)
                },
                {
                        FakerDataGenerator.randomCurrency(),
                        FakerDataGenerator.randomDifferentCurrency("GBP"),
                        FakerDataGenerator.randomAmount(400, 500)
                }
        };
    }
}