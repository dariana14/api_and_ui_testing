package ui_testing;

import com.codeborne.selenide.Configuration;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import page.LoginPage;
import page.ProductsPage;

import static com.codeborne.selenide.Selenide.open;
import static org.assertj.core.api.Assertions.assertThat;

public class LoginPageTests {

    @BeforeAll
    public static void configureSelenide() {
        Configuration.browser = "chrome";
        Configuration.browserSize = "2560x1440";
        Configuration.holdBrowserOpen = true;
        Configuration.baseUrl = "https://www.saucedemo.com";
    }

    @BeforeEach
    public void openLoginPage() {
        open("/");
    }

    @Test
    public void givenCorrectCredentials_whenLogin_thenShouldSeeProductsTitle() {
        LoginPage loginPage = new LoginPage();

        loginPage.enterUserName("standard_user");
        loginPage.enterPassword("secret_sauce");
        loginPage.clickLoginButton();

        ProductsPage productsPage = new ProductsPage();
        Assertions.assertThat(productsPage.getPageHeading()).isEqualTo("Products");
    }

    @Test
    public void givenIncorrectCredentials_whenLogin_thenShouldSeeErrorMessage() {
        LoginPage loginPage = new LoginPage();

        loginPage.enterUserName("standard_user");
        loginPage.enterPassword("tabasco");
        loginPage.clickLoginButton();

        Assertions.assertThat(loginPage.getErrorMessage()).isEqualTo("Epic sadface: Username and password do not match any user in this service");
    }

    @Test
    public void givenLockedUser_whenLogin_thenShouldSeeErrorMessage() {
        LoginPage loginPage = new LoginPage();

        loginPage.enterUserName("locked_out_user");
        loginPage.enterPassword("secret_sauce");
        loginPage.clickLoginButton();

        Assertions.assertThat(loginPage.getErrorMessage()).isEqualTo("Epic sadface: Sorry, this user has been locked out.");
    }

    @Test
    public void givenGlitchedPerformanceUser_whenLogin_thenShouldSeeProductsTitleWithDelay() {
        var loadingStart = System.currentTimeMillis();
        LoginPage loginPage = new LoginPage();

        loginPage.enterUserName("performance_glitch_user");
        loginPage.enterPassword("secret_sauce");
        loginPage.clickLoginButton();

        var loadingEnd = System.currentTimeMillis();

        var loadingTime = loadingEnd - loadingStart;

        ProductsPage productsPage = new ProductsPage();
        assertThat(productsPage.isProductListDisplayed()).isTrue();
        assertThat(loadingTime).isGreaterThan(500);
    }

    @Test
    public void givenEmptyFields_whenLogin_thenShouldSeeErrorMessage() {
        LoginPage loginPage = new LoginPage();

        loginPage.clickLoginButton();

        Assertions.assertThat(loginPage.getErrorMessage()).isEqualTo("Epic sadface: Username is required");
    }

    @Test
    public void givenCaseSensitiveCredentials_whenLogin_thenShouldSeeErrorMessage() {
        LoginPage loginPage = new LoginPage();

        loginPage.enterUserName("STANDARD_user");
        loginPage.enterPassword("SECRET_sauce");
        loginPage.clickLoginButton();

        Assertions.assertThat(loginPage.getErrorMessage()).isEqualTo("Epic sadface: Username and password do not match any user in this service");
    }

    @Test
    public void whenLoggedOut_thenShouldRedirectToLoginPage() {
        LoginPage loginPage = new LoginPage();

        loginPage.enterUserName("standard_user");
        loginPage.enterPassword("secret_sauce");
        loginPage.clickLoginButton();

        ProductsPage productsPage = new ProductsPage();
        productsPage.clickLogoutButton();

        Assertions.assertThat(loginPage.isLoginPageDisplayed()).isTrue();
    }

}
