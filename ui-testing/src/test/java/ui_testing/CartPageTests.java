package ui_testing;

import com.codeborne.selenide.Configuration;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import page.CartPage;
import page.CheckoutPage;
import page.LoginPage;
import page.ProductsPage;

import static com.codeborne.selenide.Selenide.open;

public class CartPageTests {
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
        productsPage.addProductToCart(productsPage.getProductNameByIndex(1));
        productsPage.clickCartIcon();
    }

    @Test
    public void whenCartPageLoads_thenShouldDisplayAddedItems() {
        CartPage cartPage = new CartPage();

        Assertions.assertThat(cartPage.isCartPageDisplayed()).isTrue();

        Assertions.assertThat(cartPage.getCartItemCount()).isEqualTo(2);
    }

    @Test
    public void whenRemovingItemFromCart_thenCartItemCountShouldDecrease() {
        CartPage cartPage = new CartPage();
        int initialCount = cartPage.getCartItemCount();

        cartPage.removeItemByIndex(0);

        Assertions.assertThat(cartPage.getCartItemCount()).isEqualTo(initialCount - 1);
    }

    @Test
    public void whenProceedingToCheckout_thenShouldNavigateToCheckoutPage() {
        CartPage cartPage = new CartPage();

        CheckoutPage checkoutPage = cartPage.clickCheckoutButton();

        Assertions.assertThat(checkoutPage.isCheckoutPageDisplayed()).isTrue();
    }

    @Test
    public void whenClickContinueShopping_thenShouldSeeProductPageTitle() {
        CartPage cartPage = new CartPage();

        ProductsPage productsPage = cartPage.clickContinueShopping();

        Assertions.assertThat(productsPage.getPageHeading()).isEqualTo("Products");
    }
}
