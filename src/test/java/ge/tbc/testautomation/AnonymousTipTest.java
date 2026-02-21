package ge.tbc.testautomation;

import org.testng.annotations.Test;

import static ge.tbc.testautomation.data.Constants.TBC_Anonymous_Tip_URL;

public class AnonymousTipTest extends BaseTest{

    @Test
    public void anonymousTipFullReportTest() {
        utils.navigateToPage(TBC_Anonymous_Tip_URL);

        anonymousTipSteps
                .inputOrganizationName("TBC Bank");
//                .clickNoToWork()
//                .inputInfoAboutIncident("Witnessed suspicious activity")
//                .clickHalfAnonReport()
//                .inputFirstName("John")
//                .inputLastName("Doe")
//                .selectCountry("Georgia")
//                .inputJobTitle("Manager")
//                .inputPhoneNumber("599123456")
//                .inputEmail("john.doe@example.com")
//                .inputConfirmEmail("john.doe@example.com")
//                .clickIncidentContinueNo()
//                .inputPeopleInvolvedFirstName("Jane")
//                .inputPeopleInvolvedLastName("Smith")
//                .inputIncidentFirstAddress("123 Main Street")
//                .inputIncidentSecondAddress("Apt 4B")
//                .inputIncidentCity("Tbilisi")
//                .inputIncidentPostCode("0100")
//                .selectIncidentCountry("Georgia")
//                .inputPlaceOfIncident("Office Building 5")
//                .inputIncidentDetails("I witnessed unauthorized access to sensitive documents on multiple occasions.")
//                .inputExpectations("I expect a thorough investigation and appropriate action to be taken.")
//                .inputMemorableWord("SecureWord123");

        page.waitForTimeout(3000);
    }
}