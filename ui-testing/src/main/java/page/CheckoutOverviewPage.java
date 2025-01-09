package page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class CheckoutOverviewPage {
    private SelenideElement pageTitle = $(".title");
    private SelenideElement finishButton = $("#finish");

    public boolean isOverviewPageDisplayed() {
        return pageTitle.getText().equals("Checkout: Overview");
    }

    public CheckoutCompletePage clickFinishButton() {
        finishButton.click();
        return new CheckoutCompletePage();
    }
}
