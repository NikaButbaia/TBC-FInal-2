package ge.tbc.testautomation.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class MainPage {
    protected Page page;

    public MainPage(Page page) {
        this.page = page;
    }
    public Locator chatBotLocator(){
        return page.getByText("ჩატბოტი");
    }
}
