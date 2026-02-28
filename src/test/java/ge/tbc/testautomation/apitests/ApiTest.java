package ge.tbc.testautomation.apitests;

import ge.tbc.testautomation.data.TestDataGenerator;
import ge.tbc.testautomation.dataproviders.ApiDataProvider;
import ge.tbc.testautomation.steps.ApiSteps;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static ge.tbc.testautomation.data.Constants.*;

public class ApiTest extends ApiBaseTest {

    private ApiSteps apiSteps;

    @BeforeClass
    public void setUpSteps() {
        apiSteps = new ApiSteps(apiClient);
    }

    @Test(description = "Happy path: GET consumer-loan page returns 200 with correct fields")
    public void testConsumerLoanApiHappyPath() {
        apiSteps
                .getPageRaw(CONSUMER_LOAN_PAGE_ID)
                .validateStatus200()
                .getPageDeserialized(CONSUMER_LOAN_PAGE_ID)
                .validateTitleNotEmpty()
                .validateSlugContains("consumer-loan")
                .validateSectionsNotEmpty()
                .validateAtLeastOneSectionHasItems();
    }

    @Test(description = "Happy path: Deserialized POJO fields are correctly mapped")
    public void testConsumerLoanDeserializationIntegrity() {
        apiSteps
                .getPageDeserialized(CONSUMER_LOAN_PAGE_ID)
                .validateTitleLengthGreaterThan(3)
                .validateFirstListItemLabelNotEmpty();
    }

    @Test(description = "Negative: Invalid slug-style page ID returns HTML error page, not a JSON page object",
            dataProvider = "invalidPageIds",
            dataProviderClass = ApiDataProvider.class)
    public void testInvalidPageIdReturnsError(String invalidPageId) {
        apiSteps
                .getPageRaw(invalidPageId)
                .validateNotJson(invalidPageId);
    }

    @Test(description = "Negative: Random UUID page ID returns 404")
    public void testRandomPageIdReturns404() {
        apiSteps
                .getPageRaw(TestDataGenerator.randomInvalidPageId())
                .validateStatus404();
    }
}