package model.components.checkout;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import model.components.Component;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class CheckoutComponent extends Component {

    public static final By selector = AppiumBy.xpath("//android.view.View[@text=\"Cart\"]/parent::android.view.ViewGroup/following-sibling::android.view.ViewGroup");
    private By backIconSel = AppiumBy.accessibilityId("Cart, back");
    private By headerSel = AppiumBy.xpath("//android.view.View[@text=\"Checkout\"]");
    private By shippingTabSel = AppiumBy.xpath("//android.widget.TextView[@text=\"SHIPPING\"]");
    private By paymentTabSel = AppiumBy.xpath("//android.widget.TextView[@text=\"PAYMENT\"]");
    private By confirmTabSel = AppiumBy.xpath("//android.widget.TextView[@text=\"CONFIRM\"]");

    public CheckoutComponent(AppiumDriver driver, WebElement component) {
        super(driver, component);
    }

    public WebElement backIcon() {
        return this.component.findElement(backIconSel);
    }

    public String getHeader() {
        return this.component.findElement(headerSel).getText();
    }

    public WebElement shippingTab() {
        return this.component.findElement(shippingTabSel);
    }

    public WebElement paymentTab() {
        return this.component.findElement(paymentTabSel);
    }

    public WebElement confirmTab() {
        return this.component.findElement(confirmTabSel);
    }

    public ShippingComponent getShippingComponent() {
        return new ShippingComponent(driver, this.component.findElement(ShippingComponent.selector));
    }

    public PaymentComponent getPaymentComponent() {
        return new PaymentComponent(driver, this.component.findElement(PaymentComponent.selector));
    }

    public ConfirmOrderComponent getConfirmOrderComponent() {
        return new ConfirmOrderComponent(driver, this.component.findElement(ConfirmOrderComponent.selector));
    }
}
