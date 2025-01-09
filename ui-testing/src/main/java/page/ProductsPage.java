package page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class ProductsPage {
    private final SelenideElement pageHeadingField = $(".title");
    private SelenideElement menuButton = $("#react-burger-menu-btn");
    private SelenideElement logoutLink = $("#logout_sidebar_link");

    private SelenideElement productList = $(".inventory_list");
    private ElementsCollection products = $$(".inventory_item");
    private SelenideElement cartIcon = $(".shopping_cart_link");
    private SelenideElement sortingDropdown = $(".product_sort_container");
    private SelenideElement cartBadge = $(".shopping_cart_badge");

    private final ElementsCollection productNames = $$(".inventory_item_name");
    private final ElementsCollection productPrices = $$(".inventory_item_price");

    public String getPageHeading() {
        return pageHeadingField.getText();
    }

    public void clickLogoutButton() {

        menuButton.click();
        logoutLink.click();
    }

    public boolean isProductListDisplayed() {
        return productList.isDisplayed();
    }

    public int getProductCount() {
        return products.size();
    }

    public void selectSortingOption(String optionText) {
        sortingDropdown.selectOption(optionText);
    }

    public void addProductToCart(String productName) {
        SelenideElement product = products.findBy(text(productName));
        product.$(".btn_inventory").click(); // Click the "Add to cart" button
    }

    public int getCartItemCount() {
        return cartBadge.exists() ? Integer.parseInt(cartBadge.getText()) : 0;
    }

    public CartPage clickCartIcon() {
        cartIcon.click();
        return new CartPage();
    }

    public ProductDetailsPage clickProductByName(String productName) {
        SelenideElement product = products.findBy(text(productName));
        product.$(".inventory_item_name").click();
        return new ProductDetailsPage();
    }

    public String getProductNameByIndex(int index) {
        return products.get(index).$(".inventory_item_name").getText();
    }

    public List<Double> getAllProductPrices(){
        return productPrices.texts().stream()
                .map(strPrice -> Double.valueOf(strPrice.replace("$", "")))
                .collect(Collectors.toList());
    }

    public List<String> getAllProductNames() {
        return productNames.texts();
    }


}
