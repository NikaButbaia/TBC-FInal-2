package ge.tbc.testautomation.dataproviders;

import org.testng.annotations.DataProvider;

import static ge.tbc.testautomation.data.Constants.*;

public class ApiDataProvider {

    @DataProvider(name = "validApiPages")
    public static Object[][] validApiPages() {
        return new Object[][] {
                { CONSUMER_LOAN_PAGE_ID, CONSUMER_LOAN_UI_URL }
        };
    }
    /**
     * არასწორი გვერდის ID-ებს ვიყენებ უარყოფითი API ტესტებისთვის
     *
     *  API აბრუნებს 200 სტატუსს ნებისმიერ slug-ის მსგავს სტრიქონზე
     *   რადგან catch-all მარშრუტი მუშაობს
     *   მხოლოდ შემთხვევითი UUID ფორმატის ID-ები აბრუნებენ 404-ს
     *   (ეს testRandomPageIdReturns404 ტესტში მოწმდება)
     *   აქ ვამოწმებ რომ არასწორი ID-ების შემთხვევაში პასუხის ტექსტი
     *   ცარიელი ან უაზრო იყოს
     *
     * ყველა ჩანაწერი შეიცავს:
     * { invalidPageId }
     */
    @DataProvider(name = "invalidPageIds")
    public static Object[][] invalidPageIds() {
        return new Object[][] {
                { INVALID_PAGE_ID },
                { INVALID_PAGE_ID_2 }
        };
    }
}