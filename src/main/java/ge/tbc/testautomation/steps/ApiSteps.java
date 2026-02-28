package ge.tbc.testautomation.steps;

import com.microsoft.playwright.Page;
import ge.tbc.testautomation.api.ApiClient;
import ge.tbc.testautomation.api.pojo.ListItem;
import ge.tbc.testautomation.api.pojo.PageResponse;
import ge.tbc.testautomation.api.pojo.SectionComponent;
import ge.tbc.testautomation.utils.UiExtractorUtils;
import io.restassured.response.Response;

import java.util.List;
import java.util.stream.Collectors;

import static ge.tbc.testautomation.data.Constants.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class ApiSteps {

    private final ApiClient apiClient;
    private Response lastResponse;
    private PageResponse lastPage;
    private List<String> lastListLabels;
    private String lastTitle;
    private Page playwrightPage;

    public ApiSteps(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public ApiSteps getPageRaw(String pageId) {
        lastResponse = apiClient.getPageRaw(pageId);
        return this;
    }

    public ApiSteps getPageDeserialized(String pageId) {
        lastPage = apiClient.getPageDeserialized(pageId);
        return this;
    }

    public ApiSteps validateStatus200() {
        assertThat("Expected HTTP 200 for a valid page ID",
                lastResponse.getStatusCode(), equalTo(HTTP_OK));
        return this;
    }

    public ApiSteps validateStatus404() {
        assertThat("Expected HTTP 404 for an invalid page ID",
                lastResponse.getStatusCode(), equalTo(HTTP_NOT_FOUND));
        return this;
    }

    public ApiSteps validateNotJson(String invalidPageId) {
        boolean isNotSuccess = lastResponse.getStatusCode() != HTTP_OK;
        boolean isHtmlResponse = lastResponse.getContentType() != null
                && lastResponse.getContentType().contains("text/html");

        assertThat("Invalid page ID [" + invalidPageId + "] should return an error status or " +
                        "HTML (not a valid JSON page) — this API returns text/html for unknown slugs",
                isNotSuccess || isHtmlResponse, is(true));
        return this;
    }

    public ApiSteps validateTitleNotEmpty() {
        assertThat("Page title must not be null or empty",
                lastPage.getTitle(), is(not(emptyOrNullString())));
        return this;
    }

    public ApiSteps validateTitleLengthGreaterThan(int length) {
        assertThat("Title should be a non-trivial string",
                lastPage.getTitle().length(), greaterThan(length));
        return this;
    }

    public ApiSteps validateSlugContains(String expected) {
        assertThat("Slug should match the requested page ID",
                lastPage.getSlug(), containsString(expected));
        return this;
    }

    public ApiSteps validateSectionsNotEmpty() {
        assertThat("Response must contain at least one section",
                lastPage.getSections(), is(not(empty())));
        return this;
    }

    public ApiSteps validateAtLeastOneSectionHasItems() {
        boolean hasItemsInAnySection = lastPage.getSections().stream()
                .anyMatch(section -> section.getItems() != null && !section.getItems().isEmpty());
        assertThat("At least one section should contain list items",
                hasItemsInAnySection, is(true));
        return this;
    }

    public ApiSteps validateFirstListItemLabelNotEmpty() {
        List<SectionComponent> sectionsWithItems = lastPage.getSections().stream()
                .filter(s -> s.getItems() != null && !s.getItems().isEmpty())
                .collect(Collectors.toList());

        assertThat("Must find sections containing list items",
                sectionsWithItems, is(not(empty())));

        ListItem firstItem = sectionsWithItems.get(0).getItems().get(0);
        assertThat("First list item label must not be null or empty",
                firstItem.getLabel(), is(not(emptyOrNullString())));
        return this;
    }

    public ApiSteps extractTitle() {
        lastTitle = lastPage.getTitle();
        assertThat("API title must not be empty", lastTitle, is(not(emptyOrNullString())));
        return this;
    }

    public ApiSteps extractLabels() {
        lastListLabels = lastPage.getSections().stream()
                .filter(s -> s.getItems() != null)
                .flatMap(s -> s.getItems().stream())
                .map(ListItem::getLabel)
                .filter(label -> label != null && !label.isEmpty())
                .collect(Collectors.toList());
        assertThat("API should provide at least one list item label", lastListLabels, is(not(empty())));
        return this;
    }

    public ApiSteps initPlaywrightPage(Page playwrightPage) {
        this.playwrightPage = playwrightPage;
        return this;
    }

    public ApiSteps navigateToUiPage(String uiUrl) {
        UiExtractorUtils.navigateAndWait(playwrightPage, uiUrl);
        return this;
    }

    public ApiSteps validateUiTitleMatchesApi() {
        String uiTitle = UiExtractorUtils.getPageTitleText(playwrightPage);
        assertThat("UI title must match API title (API is source of truth)",
                uiTitle.trim(), equalToIgnoringWhiteSpace(lastTitle));
        return this;
    }

    public ApiSteps validateUiContainsFirstApiLabel() {
        String fullPageText = UiExtractorUtils.getFullPageText(playwrightPage);
        String firstApiLabel = lastListLabels.get(0);
        assertThat("UI page must contain the first API list item label: [" + firstApiLabel + "]",
                fullPageText, containsString(firstApiLabel));
        return this;
    }

    public ApiSteps validateUiContainsAtLeastOneApiLabel() {
        String fullPageText = UiExtractorUtils.getFullPageText(playwrightPage);
        boolean anyLabelFoundInUi = lastListLabels.stream()
                .anyMatch(fullPageText::contains);
        assertThat("At least one API list item label must appear in UI",
                anyLabelFoundInUi, is(true));
        return this;
    }

    public ApiSteps closePage() {
        playwrightPage.context().close();
        return this;
    }
}