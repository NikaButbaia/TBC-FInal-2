package ge.tbc.testautomation;

import ge.tbc.testautomation.dataproviders.LocationDataProvider;
import com.microsoft.playwright.options.Geolocation;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class BranchesTest extends BaseTest {

    @BeforeClass
    public void openBranchesPage() {
        mainSteps.clickBranchesPage();
        page.waitForLoadState();
    }

    @Test(
            dataProvider = "locationData",
            dataProviderClass = LocationDataProvider.class
    )
    public void branchesLocationTest(double lat, double lon, double maxDistanceKm) {

        context.setGeolocation(new Geolocation(lat, lon));

        page.waitForLoadState();

        branchesSteps.validateMapStartUpCity(lat, lon, maxDistanceKm);
    }
}