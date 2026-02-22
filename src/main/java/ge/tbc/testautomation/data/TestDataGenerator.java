package ge.tbc.testautomation.data;

import com.github.javafaker.Faker;

public class TestDataGenerator {
    private static final Faker faker = new Faker();

    public static String getFirstName() {
        return faker.name().firstName();
    }

    public static String getLastName() {
        return faker.name().lastName();
    }

    public static String getFullName() {
        return faker.name().fullName();
    }

    public static String getJobTitle() {
        return faker.job().title();
    }

    public static String getEmail() {
        return faker.internet().emailAddress();
    }

    public static String getPhoneNumber() {
        return faker.phoneNumber().cellPhone().replaceAll("[^0-9]", "");
    }

    public static String getStreetAddress() {
        return faker.address().streetAddress();
    }

    public static String getSecondaryAddress() {
        return faker.address().secondaryAddress();
    }

    public static String getCity() {
        return faker.address().city();
    }

    public static String getPostCode() {
        return faker.address().zipCode();
    }

    public static String getCompanyName() {
        return faker.company().name();
    }

    public static String getIncidentDescription() {
        return faker.lorem().paragraph(3);
    }

    public static String getExpectations() {
        return faker.lorem().sentence(10);
    }

    public static String getMemorableWord() {
        return faker.lorem().word() + faker.number().digits(3);
    }

    public static String getRandomText() {
        return faker.lorem().sentence();
    }
}