package ge.tbc.testautomation.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class BranchesPage {

    private final Page page;

    public Locator mapMarkers, loadElement;

    public BranchesPage(Page page) {
        this.page = page;
        this.mapMarkers = page.locator("//gmp-advanced-marker");
        this.loadElement = page.locator("//app-atm-branches-section-list-skeleton-item");
    }
}