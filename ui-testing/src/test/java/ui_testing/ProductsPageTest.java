package ui_testing;

import com.codeborne.selenide.Configuration;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import page.CartPage;
import page.LoginPage;
import page.ProductDetailsPage;
import page.ProductsPage;

import java.util.Comparator;

import static com.codeborne.selenide.Selenide.open;
import static org.assertj.core.api.Assertions.assertThat;

public class ProductsPageTest {
    @BeforeAll
    public static void configureSelenide() {
        Configuration.browser = "edge";
        Configuration.browserSize = "2560x1440";
        Configuration.baseUrl = "https://www.saucedemo.com";
    }

    @BeforeEach
    public void loginAndNavigateToProductsPage() {
        open("/");
        LoginPage loginPage = new LoginPage();
        loginPage.enterUserName("standard_user");
        loginPage.enterPassword("secret_sauce");
        loginPage.clickLoginButton();
    }

    @Test
    public void whenPageLoads_thenShouldDisplayProductList() {
        ProductsPage productsPage = new ProductsPage();
        Assertions.assertThat(productsPage.isProductListDisplayed()).isTrue();
        Assertions.assertThat(productsPage.getProductCount()).isGreaterThan(0);
    }

    @Test
    public void whenSortingProducts_thenShouldDisplaySortedList() {
        ProductsPage productsPage = new ProductsPage();

        productsPage.selectSortingOption("Price (low to high)");
        assertThat(productsPage.getAllProductPrices()).isSortedAccordingTo(Comparator.naturalOrder());

        productsPage.selectSortingOption("Price (high to low)");
        assertThat(productsPage.getAllProductPrices()).isSortedAccordingTo(Comparator.reverseOrder());

        productsPage.selectSortingOption("Name (A to Z)");
        assertThat(productsPage.getAllProductNames()).isSortedAccordingTo(Comparator.naturalOrder());
    }

    @Test
    public void whenAddingProductToCart_thenShouldIncreaseCartCount() {
        ProductsPage productsPage = new ProductsPage();
        int initialCartCount = productsPage.getCartItemCount();

        String firstProductName = productsPage.getProductNameByIndex(0);
        productsPage.addProductToCart(firstProductName);
        Assertions.assertThat(productsPage.getCartItemCount()).isEqualTo(initialCartCount + 1);
    }

    @Test
    public void whenClickingOnCartIcon_thenShouldNavigateToCartPage() {
        ProductsPage productsPage = new ProductsPage();
        CartPage cartPage = productsPage.clickCartIcon();

        Assertions.assertThat(cartPage.isCartPageDisplayed()).isTrue();
    }

    @Test
    public void whenClickingOnProduct_thenShouldNavigateToProductDetailsPage() {
        ProductsPage productsPage = new ProductsPage();

        String firstProductName = productsPage.getProductNameByIndex(0);
        ProductDetailsPage productDetailsPage = productsPage.clickProductByName(firstProductName);

        Assertions.assertThat(productDetailsPage.getProductTitle()).isEqualTo(firstProductName);
        Assertions.assertThat(productDetailsPage.isProductDetailsDisplayed()).isTrue();
    }
}
