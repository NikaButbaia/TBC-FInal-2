package ge.tbc.testautomation.dataproviders;

import org.testng.annotations.DataProvider;

public class AbroadTransferDataProvider {

    @DataProvider(name = "transferData")
    public static Object[][] transferData() {
        return new Object[][]{
                {"GEL", "USD", "100"},
                {"USD", "EUR", "250"},
                {"EUR", "GEL", "75"}
        };
    }
}