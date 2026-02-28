package ge.tbc.testautomation.utils;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;

public class UiExtractorUtils {

    private static final String PAGE_TITLE_SELECTOR = "h1";

    public static void navigateAndWait(Page page, String url) {
        page.navigate(url);
        page.waitForSelector(PAGE_TITLE_SELECTOR, new Page.WaitForSelectorOptions()
                .setState(WaitForSelectorState.VISIBLE));
    }

    public static String getPageTitleText(Page page) {
        return page.locator(PAGE_TITLE_SELECTOR).first().textContent().trim();
    }

    public static String getFullPageText(Page page) {
        return page.locator("body").textContent();
    }
}