package ge.tbc.testautomation.apitests;

import com.microsoft.playwright.*;
import ge.tbc.testautomation.api.ApiClient;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class ApiBaseTest {

    protected ApiClient apiClient;

    private Playwright playwright;
    private Browser browser;

    @BeforeClass
    public void setUpApi() {
        apiClient = new ApiClient();
    }
    /**
     * ქმნის Playwright-ის Page ობიექტს ინტეგრაციის ტესტებისთვის საჭირო დროს.
     *
     * ბრაუზერი პირველად გამოძახებისას ეშვება (lazy launch) და შემდეგ
     * იგივე ტესტ კლასის ფარგლებში ხელახლა გამოიყენება.
     *
     * @return ახალი Playwright {@link Page} ობიექტი
     */
    protected Page createPlaywrightPage() {
        if (playwright == null) {
            playwright = Playwright.create();
            browser = playwright.chromium().launch(
                    new BrowserType.LaunchOptions().setHeadless(false)
            );
        }
        BrowserContext context = browser.newContext();
        return context.newPage();
    }

    @AfterClass(alwaysRun = true)
    public void tearDownApi() {
        if (browser != null) browser.close();
        if (playwright != null) playwright.close();
    }
}