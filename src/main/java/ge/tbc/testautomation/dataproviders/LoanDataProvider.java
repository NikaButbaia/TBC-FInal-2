package ge.tbc.testautomation.dataproviders;

import ge.tbc.testautomation.data.FakerDataGenerator;
import org.testng.annotations.DataProvider;

public class LoanDataProvider {

    @DataProvider(name = "loanData")
    public static Object[][] loanData() {
        return new Object[][]{
                {
                        FakerDataGenerator.randomAmount(100, 200),
                        FakerDataGenerator.randomTerm(1, 10)
                },
                {
                        FakerDataGenerator.randomAmount(200, 300),
                        FakerDataGenerator.randomTerm(20, 30)
                },
                {
                        FakerDataGenerator.randomAmount(300, 400),
                        FakerDataGenerator.randomTerm(30, 40)
                },
                {
                        FakerDataGenerator.randomAmount(400, 500),
                        FakerDataGenerator.randomTerm(40, 50)
                }
        };
    }
}