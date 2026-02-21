package ge.tbc.testautomation.utils;

import com.microsoft.playwright.Page;

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

    public static void verifyConversion(BigDecimal input, BigDecimal output, BigDecimal rate) {
        BigDecimal expected = input.multiply(rate).setScale(2, RoundingMode.HALF_UP);
        BigDecimal actual = output.setScale(2, RoundingMode.HALF_UP);

        assertTrue(expected.subtract(actual).abs().compareTo(new BigDecimal("1.0")) <= 0,
                "Expected: " + expected + ", Actual: " + actual);
    }
}