package ge.tbc.testautomation.apitests;

import ge.tbc.testautomation.dataproviders.ApiDataProvider;
import ge.tbc.testautomation.steps.ApiSteps;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static ge.tbc.testautomation.data.Constants.*;

public class ApiUiConsistencyTest extends ApiBaseTest {

    private ApiSteps apiSteps;

    @BeforeClass
    public void setUpSteps() {
        apiSteps = new ApiSteps(apiClient);
    }

    @Test(description = "Integration: Extract API data for consumer loan page")
    public void extractApiData() {
        apiSteps
                .getPageDeserialized(CONSUMER_LOAN_PAGE_ID)
                .extractTitle()
                .extractLabels();
    }

    @Test(description = "Integration: UI title and list items match API response (source of truth)",
            dependsOnMethods = "extractApiData",
            dataProvider = "validApiPages",
            dataProviderClass = ApiDataProvider.class)
    public void validateUiMatchesApi(String pageId, String uiUrl) {
        apiSteps
                .initPlaywrightPage(createPlaywrightPage())
                .navigateToUiPage(uiUrl)
                .validateUiTitleMatchesApi()
                .validateUiContainsFirstApiLabel()
                .validateUiContainsAtLeastOneApiLabel()
                .closePage();
    }
}