package ge.tbc.testautomation;

import org.testng.annotations.Test;

import static ge.tbc.testautomation.data.Constants.Chatbot_message;

public class ChatBotTest extends BaseTest {

    @Test
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