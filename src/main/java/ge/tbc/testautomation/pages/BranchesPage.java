package ge.tbc.testautomation.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import ge.tbc.testautomation.steps.BranchesSteps;

public class BranchesPage {
    public final Page page;

    public BranchesPage(Page page) {
        this.page = page;
    }
    public Page page() {
        return page;
    }
    public Locator mapMarkers() {
        return page.locator("//gmp-advanced-marker");
    }

    public Locator loadElement() {
        return page.locator("//app-atm-branches-section-list-skeleton-item");
    }
}