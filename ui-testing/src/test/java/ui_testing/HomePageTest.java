package ui_testing;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.open;
import static org.assertj.core.api.Assertions.assertThat;

public class HomePageTest {

    @Test
    public void whenIgoToHomePage_thenBrowserShouldContainTitle() {
        String expectedTitle = "Swag Labs";

        open("https://www.saucedemo.com");
        String actualTitle = Selenide.title();

        assertThat(actualTitle).isEqualTo(expectedTitle);
    }
}
