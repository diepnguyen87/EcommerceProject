package model.components.cart;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import model.components.Component;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

public class CartComponent extends Component {

    public static final By selector = AppiumBy.xpath("//android.view.View[@text=\"Cart\"]/parent::android.view.ViewGroup/following-sibling::android.view.ViewGroup");
    private By cartHeaderSel = AppiumBy.accessibilityId("cartHeader");
    private By emptyCartMsg = AppiumBy.accessibilityId("emptyCartSuggestionMsg");
    private By cartTotalPriceSel = AppiumBy.accessibilityId("cartTotalPrice");
    private By clearBtnSel = AppiumBy.accessibilityId("clearCartBtn");
    private By checkoutBtnSel = AppiumBy.accessibilityId("checkoutBtn");
    private By loginBtnSel = AppiumBy.xpath("//android.widget.TextView[@text=\"Login\"]");

    public CartComponent(AppiumDriver driver, WebElement component) {
        super(driver, component);
    }

    public String getCardHeader() {
        return this.component.findElement(cartHeaderSel).getText();
    }

    public String getEmptyCartMessage() {
        return this.component.findElement(emptyCartMsg).getText();
    }

    public List<CartItemComponent> getCartItemComponentList() {
        List<WebElement> cartItemList = this.component.findElements(CartItemComponent.selector);
        return cartItemList.stream().map(element -> {
            return new CartItemComponent(driver, element);
        }).collect(Collectors.toList());
    }

    public String getTotalPrice() {
        return this.component.findElement(cartTotalPriceSel).getText();
    }

    public void clickOnClearBtn() {
        this.component.findElement(clearBtnSel).click();
    }

    public void clickOnCheckoutBtn() {
        this.component.findElement(checkoutBtnSel).click();
    }
}
