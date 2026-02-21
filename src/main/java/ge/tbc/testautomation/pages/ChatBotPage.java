package ge.tbc.testautomation.pages;

import com.microsoft.playwright.FrameLocator;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class ChatBotPage {
    private final Page page;

    public ChatBotPage(Page page) {
        this.page = page;
    }

    public FrameLocator chatboxIframe() {
        return page.frameLocator("//iframe[@name='Messaging window']");
    }

    public Locator chat() {
        return chatboxIframe().locator("//textarea[@id='composer-input']");
    }

    public Locator sendMessage() {
        return chatboxIframe().locator("//button[@aria-label='Send message']");
    }

    public Locator chatboxMessages() {
        return chatboxIframe().locator("//figure[@data-garden-id='avatars.avatar']//following-sibling::div//span[@tabindex='-1']");
    }

    public Locator messageLoading() {
        return chatboxIframe().locator("//svg[@aria-label='loading']");
    }
}