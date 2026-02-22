package ge.tbc.testautomation.dataproviders;

import org.testng.annotations.DataProvider;

public class LoanDataProvider {

    @DataProvider(name = "loanData")
    public static Object[][] loanData() {
        return new Object[][]{
                {"1000", "6"},
                {"5000", "12"},
                {"10000", "24"},
                {"20000", "36"}
        };
    }
}