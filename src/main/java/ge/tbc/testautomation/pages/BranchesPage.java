package ge.tbc.testautomation.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class BranchesPage {
    private final Page page;

    public BranchesPage(Page page) {
        this.page = page;
    }

    public Locator mapMarkers() {
        return page.locator("//gmp-advanced-marker");
    }

    public Locator loadElement() {
        return page.locator("//app-atm-branches-section-list-skeleton-item");
    }
}