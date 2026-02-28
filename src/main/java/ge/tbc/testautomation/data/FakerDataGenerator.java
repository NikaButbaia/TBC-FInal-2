package ge.tbc.testautomation.data;

import com.github.javafaker.Faker;

public class FakerDataGenerator {

    private static final Faker faker = new Faker();
    private static final String[] CURRENCIES = {"GEL", "USD", "EUR", "GBP"};

    public static String randomCurrency() {
        return CURRENCIES[faker.random().nextInt(CURRENCIES.length)];
    }

    public static String randomDifferentCurrency(String exclude) {
        String currency;
        do {
            currency = randomCurrency();
        } while (currency.equals(exclude));
        return currency;
    }

    public static String randomAmount(int min, int max) {
        return String.valueOf(faker.number().numberBetween(min, max));
    }

    public static String randomTerm(int min, int max) {
        return String.valueOf(faker.number().numberBetween(min, max));
    }
    public static String randomEmail() {
        return faker.internet().emailAddress();
    }

    public static String randomSentence() {
        return faker.lorem().sentence();
    }
}