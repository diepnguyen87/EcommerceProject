package model.pages;

import io.appium.java_client.AppiumDriver;
import model.components.cart.CartComponent;
import model.components.checkout.CheckoutComponent;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CartPage extends BasePage {

    public CartPage(AppiumDriver driver) {
        super(driver);
    }

    public CartComponent getCartComponent() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(CartComponent.selector));
        return new CartComponent(driver, driver.findElement(CartComponent.selector));
    }

    public CheckoutComponent getCheckoutComponent() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(CheckoutComponent.selector));
        return new CheckoutComponent(driver, driver.findElement(CheckoutComponent.selector));
    }
}
