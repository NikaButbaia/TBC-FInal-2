package ge.tbc.testautomation;

import org.testng.annotations.Test;

import static ge.tbc.testautomation.data.Constants.*;

public class BranchesTest extends BaseTest {

    @Test
    public void branchesLocationTest() {

        utils.navigateToPage(TBC_Branches_URL);

        page.waitForTimeout(4000);

        branchesSteps.validateMapStartUpCity(
                TBILISI_LAT,
                TBILISI_LON,
                MAX_DISTANCE_KM
        );

    }
}