package ge.tbc.testautomation;

import ge.tbc.testautomation.data.TestDataGenerator;
import org.testng.annotations.Test;

import static ge.tbc.testautomation.data.Constants.*;

public class AnonymousTipTest extends BaseTest {

    @Test(priority = 1)
    public void testOrganizationDetails() {
        utils.navigateToPage(TBC_Anonymous_Tip_URL);

        anonymousTipSteps
                .inputOrganizationName(TestDataGenerator.getCompanyName())
                .clickNoToWork()
                .inputInfoAboutIncident(TestDataGenerator.getRandomText());

        page.waitForTimeout(2000);
    }

    @Test(priority = 2)
    public void testIdentity() {
        String email = TestDataGenerator.getEmail();

        anonymousTipSteps
                .clickHalfAnonReport()
                .inputFirstName(TestDataGenerator.getFirstName())
                .inputLastName(TestDataGenerator.getLastName())
                .selectCountry()
                .inputJobTitle(TestDataGenerator.getJobTitle())
                .inputPhoneNumber(TestDataGenerator.getPhoneNumber())
                .inputEmail(email)
                .inputConfirmEmail(email);

        page.waitForTimeout(2000);
    }

    @Test(priority = 3)
    public void testContactDetails() {
        anonymousTipSteps
                .clickIncidentContinueNo()
                .inputPeopleInvolvedFirstName(TestDataGenerator.getFirstName())
                .inputPeopleInvolvedLastName(TestDataGenerator.getLastName());

        page.waitForTimeout(2000);
    }

    @Test(priority = 4)
    public void testIncidentDetails() {
        anonymousTipSteps
                .inputIncidentFirstAddress(TestDataGenerator.getStreetAddress())
                .inputIncidentSecondAddress(TestDataGenerator.getSecondaryAddress())
                .inputIncidentCity(TestDataGenerator.getCity())
                .inputIncidentPostCode(TestDataGenerator.getPostCode())
                .selectIncidentCountry()
                .inputPlaceOfIncident(TestDataGenerator.getRandomText())
                .inputIncidentDetails(TestDataGenerator.getIncidentDescription())
                .inputExpectations(TestDataGenerator.getExpectations());

        page.waitForTimeout(2000);
    }

    @Test(priority = 5)
    public void testSubmit() {
        anonymousTipSteps
                .inputMemorableWord(TestDataGenerator.getMemorableWord())
                .clickConfirm();

        page.waitForTimeout(3000);
    }
}