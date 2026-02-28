package ge.tbc.testautomation.utils;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.options.WaitForSelectorState;

import static ge.tbc.testautomation.data.Constants.EARTH_RADIUS_KM;
import static org.testng.Assert.assertTrue;

public class MapUtils {

    public static double[] parseCoordinates(String position) {
        String[] parts = position.split(",");
        return new double[]{
                Double.parseDouble(parts[0].trim()),
                Double.parseDouble(parts[1].trim())
        };
    }

    public static void validateMarkers(Locator markers, double cityLat, double cityLon, double maxDistance) {
        int total = markers.count();
        if (total == 0) throw new AssertionError("No map markers found");

        for (int i = 0; i < total; i++) {
            Locator marker = markers.nth(i);
            if (!marker.isVisible()) continue; // skip hidden markers
            processMarker(marker, cityLat, cityLon, maxDistance);
        }
    }

    private static void processMarker(Locator marker, double cityLat, double cityLon, double maxDistance) {
        if (!marker.isVisible()) return;

        String position = marker.getAttribute("position");
        if (position == null) return;

        double[] coords = parseCoordinates(position);
        double distance = haversineKm(cityLat, cityLon, coords[0], coords[1]);

        System.out.printf("(%.6f, %.6f) -> %.2f km%n", coords[0], coords[1], distance);

        assertTrue(distance <= maxDistance,
                String.format("Marker at (%.6f, %.6f) is %.2f km away, exceeds max %.2f km",
                        coords[0], coords[1], distance, maxDistance));
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
        markers.first().page().waitForTimeout(1500);
    }
    public static double haversineKm(double lat1, double lon1, double lat2, double lon2) {
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);

        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                        Math.sin(dLon / 2) * Math.sin(dLon / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return EARTH_RADIUS_KM * c;
    }
}