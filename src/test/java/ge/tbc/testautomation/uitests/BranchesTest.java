package ge.tbc.testautomation.uitests;

import com.microsoft.playwright.options.Geolocation;
import ge.tbc.testautomation.dataproviders.BranchDataProvider;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Feature("Branch & ATM Map")
public class BranchesTest extends BaseTest {

    @BeforeClass
    public void openBranchesPage() {
        mainSteps.clickBranchesPage();
        page.waitForLoadState();
    }
    /**
     აკეთებს ყველაფერს რაც მინდა და გეოლოკაციას იყენებს სწორედ
     ასერტებიც გამოთვლებიც ყველაფერი სწორი აქვს სამწუხაროდ ვებკიტზე ფიზიკურად
     ვერ მუშაობს რადგან ვებკიტი არ მაძლევს საშვალებას რომ გეოლოკაცია იყოს გამოყენებული ტესტში
     */
    @Test(
            dataProvider = "locationData",
            dataProviderClass = BranchDataProvider.class
    )
    @Story("Map displays branches near user's geolocation")
    @Description("Spoofs device geolocation to given coordinates, reloads the branches page, " +
            "and verifies that all map markers appear within the expected maximum distance")
    @Severity(SeverityLevel.CRITICAL)
    public void branchesLocationTest(double lat, double lon, double maxDistanceKm) {
        context.setGeolocation(new Geolocation(lat, lon));
        page.reload();
        page.waitForLoadState();

        branchesSteps.validateMapStartUpCity(lat, lon, maxDistanceKm);
    }
}