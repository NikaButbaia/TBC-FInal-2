package ge.tbc.testautomation.steps;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import ge.tbc.testautomation.pages.AnonymousTipPage;

public class AnonymousTipSteps {
    private final AnonymousTipPage page;

    public AnonymousTipSteps(Page page){
        this.page = new AnonymousTipPage(page);
    }

    public AnonymousTipSteps inputOrganizationName(String organizationName){
        page.organizationInput().click(new Locator.ClickOptions().setForce(true));
        page.organizationInput().fill(organizationName);
        return this;
    }

    public AnonymousTipSteps clickNoToWork(){
        page.noToWorkButton().click();
        return this;
    }

    public AnonymousTipSteps inputInfoAboutIncident(String info){
        page.infoAboutIncident().fill(info);
        return this;
    }

    public AnonymousTipSteps clickHalfAnonReport(){
        page.halfAnonReport().click();
        return this;
    }

    public AnonymousTipSteps inputFirstName(String firstName){
        page.firstName().fill(firstName);
        return this;
    }

    public AnonymousTipSteps inputLastName(String lastName){
        page.lastName().fill(lastName);
        return this;
    }

    public AnonymousTipSteps selectCountry(){
        page.selectCountry().click();
        page.selectCountryDropdown().first().click();
        return this;
    }

    public AnonymousTipSteps inputJobTitle(String jobTitle){
        page.jobTitle().fill(jobTitle);
        return this;
    }

    public AnonymousTipSteps inputPhoneNumber(String phoneNumber){
        page.phoneNumber().fill(phoneNumber);
        return this;
    }

    public AnonymousTipSteps inputEmail(String email){
        page.email().fill(email);
        return this;
    }

    public AnonymousTipSteps inputConfirmEmail(String email){
        page.confirmEmail().fill(email);
        return this;
    }

    public AnonymousTipSteps clickIncidentContinueNo(){
        page.incidentContinueNoButton().click();
        return this;
    }

    public AnonymousTipSteps inputPeopleInvolvedFirstName(String firstName){
        page.peopleInvolvedFirstName().fill(firstName);
        return this;
    }

    public AnonymousTipSteps inputPeopleInvolvedLastName(String lastName){
        page.peopleInvolvedLastName().fill(lastName);
        return this;
    }

    public AnonymousTipSteps inputIncidentFirstAddress(String address){
        page.incidentFirstAddress().fill(address);
        return this;
    }

    public AnonymousTipSteps inputIncidentSecondAddress(String address){
        page.incidentSecondAddress().fill(address);
        return this;
    }

    public AnonymousTipSteps inputIncidentCity(String city){
        page.incidentCity().fill(city);
        return this;
    }

    public AnonymousTipSteps inputIncidentPostCode(String postCode){
        page.incidentPostCode().fill(postCode);
        return this;
    }

    public AnonymousTipSteps selectIncidentCountry(){
        page.incidentCountry().click();
        page.selectCountryDropdown().first().click();
        return this;
    }

    public AnonymousTipSteps inputPlaceOfIncident(String place){
        page.placeOfIncident().fill(place);
        return this;
    }

    public AnonymousTipSteps inputIncidentDetails(String details){
        page.incidentDetails().fill(details);
        return this;
    }

    public AnonymousTipSteps inputExpectations(String expectations){
        page.expectations().fill(expectations);
        return this;
    }

    public AnonymousTipSteps inputMemorableWord(String word){
        page.memorableWord().fill(word);
        return this;
    }

    public AnonymousTipSteps clickConfirm(){
        page.confirmButton().click();
        return this;
    }
}