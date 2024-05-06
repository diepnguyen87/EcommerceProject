package model.components.cart;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import model.components.Component;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class CartItemComponent extends Component {

    public static final By selector = AppiumBy.id("cartItem");
    private By productNameSel = AppiumBy.accessibilityId("productName");
    private By productPriceSel = AppiumBy.accessibilityId("productPrice");

    public CartItemComponent(AppiumDriver driver, WebElement component) {
        super(driver, component);
    }

    public String getProductName() {
        return this.component.findElement(productNameSel).getText();
    }

    public int getProductPrice() {
        String priceStr = this.component.findElement(productPriceSel).getText();
        String[] priceArr = priceStr.split("\\s");
        return Integer.parseInt(priceArr[priceArr.length - 1]);
    }
}
