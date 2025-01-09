package page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class CartPage {

    private SelenideElement cartPageTitle = $(".title");
    private ElementsCollection cartItems = $$(".cart_item");
    private SelenideElement checkoutButton = $("#checkout");
    private SelenideElement continueShoppingButton = $("#continue-shopping");


    public ProductsPage clickContinueShopping() {
        continueShoppingButton.click();
        return new ProductsPage();
    }

    public boolean isCartPageDisplayed() {
        return cartPageTitle.getText().equals("Your Cart");
    }

    public int getCartItemCount() {
        return cartItems.size();
    }

    public void removeItemByIndex(int index) {
        cartItems.get(index).$(".cart_button").click();
    }

    public CheckoutPage clickCheckoutButton() {
        checkoutButton.click();
        return new CheckoutPage();
    }
}
