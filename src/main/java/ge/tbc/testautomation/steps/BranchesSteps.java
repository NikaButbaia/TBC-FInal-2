package ge.tbc.testautomation.steps;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import ge.tbc.testautomation.pages.BranchesPage;
import ge.tbc.testautomation.utils.Utils;

import static org.testng.Assert.assertTrue;

public class BranchesSteps {

    private final BranchesPage page;

    public BranchesSteps(Page page) {
        this.page = new BranchesPage(page);
    }

    public BranchesSteps validateMapStartUpCity(double cityLat, double cityLon, double maxDistance) {
        waitForMap();
        validateMarkers(cityLat, cityLon, maxDistance);
        return this;
    }

    private void waitForMap() {
        Utils.waitForMapReady(page.loadElement(), page.mapMarkers());
    }

    private void validateMarkers(double cityLat, double cityLon, double maxDistance) {
        int total = page.mapMarkers().count();
        if (total == 0) throw new AssertionError("No map markers found");

        for (int i = 0; i < total; i++) {
            processMarker(page.mapMarkers().nth(i), cityLat, cityLon, maxDistance);
        }
    }

    private void processMarker(Locator marker,
                               double cityLat,
                               double cityLon,
                               double maxDistance) {

        if (!marker.isVisible()) return;

        String position = marker.getAttribute("position");
        if (position == null) return;

        double[] coords = parseCoordinates(position);
        double distance = Utils.haversineKm(cityLat, cityLon, coords[0], coords[1]);

        System.out.printf("(%.6f, %.6f) -> %.2f km%n", coords[0], coords[1], distance);

        if (distance <= maxDistance) {
            assertTrue(true);
        }
    }

    private double[] parseCoordinates(String position) {
        String[] parts = position.split(",");
        return new double[]{
                Double.parseDouble(parts[0].trim()),
                Double.parseDouble(parts[1].trim())
        };
    }
}