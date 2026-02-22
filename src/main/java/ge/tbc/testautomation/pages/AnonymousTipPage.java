package ge.tbc.testautomation.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class AnonymousTipPage {
    private final Page page;

    public AnonymousTipPage(Page page){
        this.page=page;
    }
    public Locator organizationInput(){
        return page.locator("input[id='organisation']");
    }
    public Locator noToWorkButton(){
        return page.getByText("არა").first();
    }
    public Locator infoAboutIncident(){
        return page.locator("input[id='referer']");
    }
    public Locator halfAnonReport(){
        return page.getByText("ნახევრად");
    }
    public Locator firstName(){
        return page.locator("input[id='firstName']");
    }
    public Locator lastName(){
        return page.locator("input[id='lastName']");
    }
    public Locator selectCountry(){
        return page.locator("button[id='whistleblowerAddress.country']");
    }
    public Locator selectCountryDropdown(){
        return page.locator("[data-value]");
    }
    public Locator jobTitle(){
        return page.locator("input[id='jobTitle']");
    }
    public Locator phoneNumber(){
        return page.locator("input[id='phoneNumber']");
    }
    public Locator email(){
        return page.locator("input[id='email']");
    }
    public Locator confirmEmail(){
        return page.locator("input[id='confirmEmail']");
    }
    public Locator incidentContinueNoButton(){
        return page.getByText("არა").nth(4);
    }
    public Locator peopleInvolvedFirstName(){
        return page.locator("input[id='peopleInvolved.0.firstName']");
    }
    public Locator peopleInvolvedLastName(){
        return page.locator("input[id='peopleInvolved.0.lastName']");
    }
    public Locator incidentFirstAddress(){
        return page.locator("input[id='incidentAddress.addressLine1']");
    }
    public Locator incidentSecondAddress(){
        return page.locator("input[id='incidentAddress.addressLine2']");
    }
    public Locator incidentCity(){
        return page.locator("input[id='incidentAddress.city']");
    }
    public Locator incidentPostCode(){
        return page.locator("input[id='incidentAddress.postcode']");
    }
    public Locator incidentCountry(){
        return page.locator("button[id='incidentAddress.country']");
    }
    public Locator placeOfIncident(){
        return page.locator("input[id='placeOfIncident']");
    }
    public Locator incidentDetails(){
        return page.locator("textarea[id='incidentDescription']");
    }
    public Locator expectations(){
        return page.locator("textarea[id='expectations']");
    }
    public Locator memorableWord(){
        return page.locator("input[id='memorableWord']");
    }
    public Locator confirmButton(){
        return page.getByText("დიახ").nth(5);
    }
}
