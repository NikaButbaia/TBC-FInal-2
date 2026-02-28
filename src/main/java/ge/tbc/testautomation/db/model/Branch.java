package ge.tbc.testautomation.db.model;

import lombok.Data;

@Data
public class Branch {
    private double latitude;
    private double longitude;
    private double maxDistanceKm;
}