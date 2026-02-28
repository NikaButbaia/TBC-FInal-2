package ge.tbc.testautomation.steps;

import com.microsoft.playwright.Page;
import ge.tbc.testautomation.pages.BranchesPage;
import ge.tbc.testautomation.utils.MapUtils;
import io.qameta.allure.Step;

public class BranchesSteps {

    private final BranchesPage branchesPage;

    public BranchesSteps(Page page) {
        this.branchesPage = new BranchesPage(page);
    }

    @Step("Wait for map to load and validate markers are within {maxDistance}km of coordinates ({cityLat}, {cityLon})")
    public BranchesSteps validateMapStartUpCity(double cityLat, double cityLon, double maxDistance) {
        MapUtils.waitForMapReady(branchesPage.loadElement, branchesPage.mapMarkers);
        MapUtils.validateMarkers(branchesPage.mapMarkers, cityLat, cityLon, maxDistance);
        return this;
    }
}