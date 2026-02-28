package ge.tbc.testautomation.data;

import java.util.UUID;

public final class TestDataGenerator {

    public static String randomInvalidPageId() {
        return UUID.randomUUID().toString();
    }
}