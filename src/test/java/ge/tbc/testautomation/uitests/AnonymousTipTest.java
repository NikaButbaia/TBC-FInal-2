package ge.tbc.testautomation.uitests;

import ge.tbc.testautomation.data.FakerDataGenerator;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Feature("Anonymous Tip Submission")
public class AnonymousTipTest extends BaseTest {

    @BeforeClass
    public void setUp() {
        mainSteps
                .AnonTipPage()
                .clickTbcAnonPage();
    }

    @Test(description = "Anonymous tip: fill personal and location info")
    @Story("User submits an anonymous tip")
    @Description("Selects anonymous option, enters a randomly generated email and selects a branch location")
    @Severity(SeverityLevel.NORMAL)
    public void fillAnonymousTipInfo() {
        anonymousTipSteps
                .clickOnStayAnonymous()
                .inputEmail(FakerDataGenerator.randomEmail())
                .selectLocation();
    }

    @Test(description = "Anonymous tip: select incidents and submit details",
            dependsOnMethods = "fillAnonymousTipInfo")
    @Story("User submits an anonymous tip")
    @Description("Selects incident categories, fills in randomly generated incident details and submits the form, " +
            "then verifies the confirmation message is displayed")
    @Severity(SeverityLevel.CRITICAL)
    public void submitIncidentDetails() {
        anonymousTipSteps
                .clickOnIncidents(5)
                .inputDetails(FakerDataGenerator.randomSentence())
                .submit()
                .submissionValidation();
    }
}