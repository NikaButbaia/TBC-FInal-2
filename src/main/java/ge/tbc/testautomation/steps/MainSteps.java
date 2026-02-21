package ge.tbc.testautomation.steps;

import com.microsoft.playwright.Page;
import ge.tbc.testautomation.pages.ChatBotPage;
import ge.tbc.testautomation.pages.MainPage;

public class MainSteps {
    private final MainPage page;

    public MainSteps(Page page){
        this.page= new MainPage(page);
    }
    public MainSteps clickChatBot(){
        page.chatBotLocator().click();
        return this;
    }
}
