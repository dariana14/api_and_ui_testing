package page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class ProductDetailsPage {
    private SelenideElement productTitle = $(".inventory_details_name");
    private SelenideElement productDescription = $(".inventory_details_desc");
    private SelenideElement productPrice = $(".inventory_details_price");

    public String getProductTitle() {
        return productTitle.getText();
    }

    public boolean isProductDetailsDisplayed() {
        return productTitle.isDisplayed()
                && productDescription.isDisplayed()
                && productPrice.isDisplayed();
    }
}
