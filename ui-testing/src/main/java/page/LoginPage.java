package page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class LoginPage {
    private SelenideElement userNameField = $("#user-name");
    private SelenideElement passwordField = $("#password");
    private SelenideElement loginButton = $("#login-button");
    private SelenideElement errorMessage = $("h3");
    private SelenideElement loginPageTitle = $("#login-button");

    public void enterUserName(String userName) {
        userNameField.setValue(userName);
    }

    public void enterPassword(String password) {
        passwordField.setValue(password);
    }

    public void clickLoginButton() {
        loginButton.click();
    }

    public String getErrorMessage() {
        return errorMessage.getText();
    }

    public boolean isLoginPageDisplayed() {
        return loginPageTitle.isDisplayed();
    }
}
