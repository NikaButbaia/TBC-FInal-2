package ge.tbc.testautomation.utils;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import static org.testng.Assert.assertTrue;

public class Utils {
    protected Page page;

    public Utils(Page page) {
        this.page = page;
    }

    public Utils navigateToPage(String pageURL){
        page.navigate(pageURL);
        return this;
    }
    public Utils bottomScroll() {
        page.evaluate("window.scrollTo(0, document.body.scrollHeight)");
        return this;
    }

    public static BigDecimal extractNumber(String text) {
        String cleaned = text.replaceAll("[^0-9.]", "");
        return new BigDecimal(cleaned);
    }

    public static BigDecimal extractRate(String rateText) {
        Pattern pattern = Pattern.compile("=\\s*([0-9]+\\.?[0-9]*)");
        Matcher matcher = pattern.matcher(rateText);

        if (!matcher.find()) {
            throw new IllegalStateException("Rate not found in: " + rateText);
        }

        return new BigDecimal(matcher.group(1));
    }

    public static void verifyConversion(BigDecimal input, BigDecimal output) {

        BigDecimal effectiveRate =
                output.divide(input, 6, RoundingMode.HALF_UP);

        assertTrue(output.compareTo(BigDecimal.ZERO) > 0,
                "Output amount must be positive");
    }
    private static final double EARTH_RADIUS_KM = 6371.0;

    public static double haversineKm(double lat1, double lon1, double lat2, double lon2) {
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);

        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                        Math.sin(dLon / 2) * Math.sin(dLon / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return EARTH_RADIUS_KM * c;
    }
    public static void waitForMapReady(Locator skeleton, Locator markers) {
        try {
            skeleton.waitFor(new Locator.WaitForOptions()
                    .setState(WaitForSelectorState.HIDDEN)
                    .setTimeout(10000));
        } catch (Exception ignored) {}

        markers.first().waitFor(new Locator.WaitForOptions()
                .setState(WaitForSelectorState.ATTACHED)
                .setTimeout(30000));
    }
}