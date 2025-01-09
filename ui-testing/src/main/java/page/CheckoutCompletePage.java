package page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class CheckoutCompletePage {
    private SelenideElement completeHeader = $(".complete-header");

    public boolean isOrderCompleteMessageDisplayed() {
        return completeHeader.isDisplayed();
    }

    public String getOrderCompleteMessage() {
        return completeHeader.getText();
    }

}
