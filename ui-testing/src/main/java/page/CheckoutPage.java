package page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class CheckoutPage {

    private SelenideElement checkoutPageTitle = $(".title");
    private SelenideElement firstNameField = $("#first-name");
    private SelenideElement lastNameField = $("#last-name");
    private SelenideElement postalCodeField = $("#postal-code");
    private SelenideElement continueButton = $("#continue");
    private SelenideElement errorMessage = $(".error-message-container");

    public boolean isCheckoutFormDisplayed() {
        return firstNameField.isDisplayed() && lastNameField.isDisplayed() && postalCodeField.isDisplayed();
    }

    public void enterFirstName(String firstName) {
        firstNameField.setValue(firstName);
    }

    public void enterLastName(String lastName) {
        lastNameField.setValue(lastName);
    }

    public void enterPostalCode(String postalCode) {
        postalCodeField.setValue(postalCode);
    }

    public CheckoutOverviewPage clickContinueButton() {
        continueButton.click();
        return new CheckoutOverviewPage();
    }

    public boolean isCheckoutPageDisplayed() {
        return checkoutPageTitle.getText().equals("Checkout: Your Information");
    }

    public String getErrorMessage() {
        return errorMessage.getText();
    }
}
