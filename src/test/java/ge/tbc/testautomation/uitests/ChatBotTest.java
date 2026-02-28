package ge.tbc.testautomation.uitests;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.testng.annotations.Test;

import static ge.tbc.testautomation.data.Constants.Chatbot_message;

@Feature("Chatbot")
public class ChatBotTest extends BaseTest {

    @Test
    @Story("User interacts with the chatbot assistant")
    @Description("Scrolls to the bottom of the page, opens the chatbot, sends a predefined message " +
            "and verifies that a response is returned and visible in the chat window")
    @Severity(SeverityLevel.NORMAL)
    public void chatBotTest() {
        utils.bottomScroll();

        mainSteps.clickChatBot();

        chatBotSteps
                .writeMessage(Chatbot_message)
                .clickSendButton()
                .waitForChatBoxMessageToLoad()
                .validateChatBox();
    }
}