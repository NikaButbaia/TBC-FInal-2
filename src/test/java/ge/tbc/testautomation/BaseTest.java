package ge.tbc.testautomation;

import com.microsoft.playwright.*;
import ge.tbc.testautomation.steps.*;
import ge.tbc.testautomation.utils.Utils;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import static ge.tbc.testautomation.data.Constants.TBC_URL;

public class BaseTest {
    protected Playwright playwright;
    protected Browser browser;
    protected BrowserContext context;
    protected Page page;

    protected Utils utils;
    protected LoansSteps loansSteps;
    protected AbroadTransferSteps abroadTransferSteps;
    protected ChatBotSteps chatBotSteps;
    protected MainSteps mainSteps;
    protected AnonymousTipSteps anonymousTipSteps;

    @BeforeClass
    public void setUp(){
        playwright = Playwright.create();

        browser = playwright.chromium().launch(
                new BrowserType.LaunchOptions()
                        .setHeadless(false)
                        .setArgs(java.util.List.of("--start-maximized"))
        );
        context = browser.newContext(
                new Browser.NewContextOptions()
                        .setViewportSize(null)
        );
        page = context.newPage();
        page.navigate(TBC_URL);

        utils = new Utils(page);
        loansSteps = new LoansSteps(page);
        abroadTransferSteps = new AbroadTransferSteps(page);
        chatBotSteps = new ChatBotSteps(page);
        mainSteps = new MainSteps(page);
        anonymousTipSteps = new AnonymousTipSteps(page);
    }

    @AfterClass
    public void tearDown() {
        context.close();
        browser.close();
        playwright.close();
    }
}
