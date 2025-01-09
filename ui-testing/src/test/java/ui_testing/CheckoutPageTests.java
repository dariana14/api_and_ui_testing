package ui_testing;

import com.codeborne.selenide.Configuration;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import page.*;

import static com.codeborne.selenide.Selenide.open;

public class CheckoutPageTests {

    @BeforeAll
    public static void configureSelenide() {
        Configuration.browser = "edge";
        Configuration.browserSize = "2560x1440";
        Configuration.baseUrl = "https://www.saucedemo.com";
    }

    @BeforeEach
    public void loginAndAddItemsToCart() {
        open("/");
        LoginPage loginPage = new LoginPage();
        loginPage.enterUserName("standard_user");
        loginPage.enterPassword("secret_sauce");
        loginPage.clickLoginButton();

        ProductsPage productsPage = new ProductsPage();
        productsPage.addProductToCart(productsPage.getProductNameByIndex(0));
        productsPage.clickCartIcon();
        CartPage cartPage = new CartPage();
        cartPage.clickCheckoutButton();
    }

    @Test
    public void whenCheckoutPageLoads_thenShouldSeeCheckoutForm() {
        CheckoutPage checkoutPage = new CheckoutPage();

        Assertions.assertThat(checkoutPage.isCheckoutPageDisplayed()).isTrue();
        Assertions.assertThat(checkoutPage.isCheckoutFormDisplayed()).isTrue();
    }

    @Test
    public void whenEnteringValidCheckoutInformation_thenShouldNavigateToOverviewPage() {
        CheckoutPage checkoutPage = new CheckoutPage();

        checkoutPage.enterFirstName("John");
        checkoutPage.enterLastName("Doe");
        checkoutPage.enterPostalCode("12345");

        CheckoutOverviewPage overviewPage = checkoutPage.clickContinueButton();

        Assertions.assertThat(overviewPage.isOverviewPageDisplayed()).isTrue();
    }

    @Test
    public void whenCompletingOrder_thenShouldSeeConfirmationMessage() {
        CheckoutPage checkoutPage = new CheckoutPage();
        checkoutPage.enterFirstName("John");
        checkoutPage.enterLastName("Doe");
        checkoutPage.enterPostalCode("12345");

        CheckoutOverviewPage overviewPage = checkoutPage.clickContinueButton();

        CheckoutCompletePage completePage = overviewPage.clickFinishButton();

        Assertions.assertThat(completePage.getOrderCompleteMessage()).isEqualTo("Thank you for your order!");
    }

    @Test
    public void givenInfoWithoutFirstName_whenClickContinue_thenShouldSeeErrorMessage() {
        CheckoutPage checkoutPage = new CheckoutPage();

        checkoutPage.enterLastName("Doe");
        checkoutPage.enterPostalCode("12345");

        checkoutPage.clickContinueButton();

        Assertions.assertThat(checkoutPage.getErrorMessage()).isEqualTo("Error: First Name is required");
    }
}
