package ge.tbc.testautomation.steps;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;
import ge.tbc.testautomation.pages.ChatBotPage;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class ChatBotSteps {
    private final ChatBotPage page;

    public ChatBotSteps(Page page) {
        this.page = new ChatBotPage(page);
    }

    public ChatBotSteps writeMessage(String message) {
        page.chat().fill(message);
        return this;
    }

    public ChatBotSteps clickSendButton() {
        page.sendMessage().click();
        return this;
    }

    public ChatBotSteps waitForChatBoxMessageToLoad() {
        page.messageLoading().waitFor(new Locator.WaitForOptions()
                .setState(WaitForSelectorState.HIDDEN)
                .setTimeout(30000));
        return this;
    }

    public ChatBotSteps validateChatBox() {
        assertThat(page.chatboxMessages().first()).isVisible();
        return this;
    }
}