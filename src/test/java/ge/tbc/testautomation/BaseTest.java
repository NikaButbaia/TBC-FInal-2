package ge.tbc.testautomation;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.Geolocation;
import ge.tbc.testautomation.steps.*;
import ge.tbc.testautomation.utils.Utils;
import org.testng.annotations.*;

import static ge.tbc.testautomation.data.Constants.*;

public class BaseTest {

    protected Playwright playwright;
    protected Browser browser;
    protected BrowserContext context;
    protected Page page;
    protected boolean isMobile;

    protected Utils utils;
    protected LoansSteps loansSteps;
    protected AbroadTransferSteps abroadTransferSteps;
    protected ChatBotSteps chatBotSteps;
    protected MainSteps mainSteps;
    protected AnonymousTipSteps anonymousTipSteps;
    protected BranchesSteps branchesSteps;

    @BeforeClass
    @Parameters({"browser", "device"})
    public void setUp(
            @Optional("chrome") String browserName,
            @Optional("desktop") String device
    ) {
        playwright = Playwright.create();

        BrowserType.LaunchOptions launchOptions = new BrowserType.LaunchOptions()
                .setHeadless(false);

        if (device.equalsIgnoreCase("desktop")) {
            launchOptions.setArgs(java.util.List.of("--start-maximized"));
        }

        browser = switch (browserName.toLowerCase()) {
            case "firefox" -> playwright.firefox().launch(launchOptions);
            case "webkit" -> playwright.webkit().launch(launchOptions);
            default -> playwright.chromium().launch(launchOptions);
        };

        Browser.NewContextOptions contextOptions;

        if (device.equalsIgnoreCase("mobile") && !browserName.equalsIgnoreCase("firefox")) {
            isMobile = true;
            contextOptions = new Browser.NewContextOptions()
                    .setViewportSize(MobileDevice.IPHONE_12_PRO.width, MobileDevice.IPHONE_12_PRO.height)
                    .setUserAgent(MobileDevice.IPHONE_12_PRO.userAgent)
                    .setDeviceScaleFactor(MobileDevice.IPHONE_12_PRO.scaleFactor)
                    .setIsMobile(true)
                    .setHasTouch(true)
                    .setPermissions(java.util.List.of("geolocation"))
                    .setGeolocation(new Geolocation(TBILISI_LAT, TBILISI_LON));
        } else {
            isMobile = false;
            contextOptions = new Browser.NewContextOptions()
                    .setViewportSize(null)
                    .setPermissions(java.util.List.of("geolocation"))
                    .setGeolocation(new Geolocation(TBILISI_LAT, TBILISI_LON));
        }

        context = browser.newContext(contextOptions);
        page = context.newPage();
        page.navigate(TBC_URL);

        utils = new Utils(page);
        loansSteps = new LoansSteps(page, isMobile);
        abroadTransferSteps = new AbroadTransferSteps(page);
        chatBotSteps = new ChatBotSteps(page);
        mainSteps = new MainSteps(page);
        anonymousTipSteps = new AnonymousTipSteps(page);
        branchesSteps = new BranchesSteps(page);
    }

    @AfterClass
    public void tearDown() {
        if (context != null) context.close();
        if (browser != null) browser.close();
        if (playwright != null) playwright.close();
    }
}