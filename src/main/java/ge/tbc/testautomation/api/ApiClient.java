package ge.tbc.testautomation.api;

import ge.tbc.testautomation.api.pojo.PageResponse;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static ge.tbc.testautomation.data.Constants.API_BASE_URL;
import static ge.tbc.testautomation.data.Constants.DEFAULT_LOCALE;

public class ApiClient {

    private RequestSpecification baseRequest() {
        return RestAssured
                .given()
                .filter(new AllureRestAssured())
                .baseUri(API_BASE_URL)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON);
    }

    public Response getPageRaw(String pageId, String locale) {
        return baseRequest()
                .queryParam("locale", locale)
                .when()
                .get("/api/v1/sites/pages/{pageId}", pageId)
                .then()
                .extract()
                .response();
    }

    public Response getPageRaw(String pageId) {
        return getPageRaw(pageId, DEFAULT_LOCALE);
    }

    public PageResponse getPageDeserialized(String pageId, String locale) {
        return getPageRaw(pageId, locale)
                .as(PageResponse.class);
    }

    public PageResponse getPageDeserialized(String pageId) {
        return getPageDeserialized(pageId, DEFAULT_LOCALE);
    }
}