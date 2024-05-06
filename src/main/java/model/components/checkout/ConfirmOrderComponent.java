package model.components.checkout;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import model.components.Component;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

public class ConfirmOrderComponent extends Component {

    public static final By selector = AppiumBy.xpath("//android.view.View[@text=\"Checkout\"]/parent::android.view.ViewGroup/following-sibling::android.view.ViewGroup/android.view.ViewGroup/following-sibling::android.view.ViewGroup");
    public By headerSel = AppiumBy.accessibilityId("confirmTabHeader");
    private By shippingToSectionHeaderSel = AppiumBy.xpath("//android.widget.TextView[@text=\"Shipping to:\"]");
    private By confirmShippingAddress1Sel = AppiumBy.accessibilityId("confirmShippingAdd1");
    private By confirmShippingAddress2Sel = AppiumBy.accessibilityId("confirmShippingAdd2");
    private By confirmCitySel = AppiumBy.accessibilityId("confirmCity");
    private By confirmZipCodeSel = AppiumBy.accessibilityId("confirmZIPCode");
    private By confirmCountrySel = AppiumBy.accessibilityId("confirmCountry");
    private By itemSectionHeaderSel = AppiumBy.xpath("//android.widget.TextView[@text=\"Items:\"]");

    private By placeOrderBtnSel = AppiumBy.accessibilityId("placeOrderBtn");

    public ConfirmOrderComponent(AppiumDriver driver, WebElement component) {
        super(driver, component);
    }

    public String getHeader() {
        return this.component.findElement(headerSel).getText();
    }

    public String getShippingToSectionHeader() {
        return this.component.findElement(shippingToSectionHeaderSel).getText();
    }

    public String getConfirmShippingAddress1() {
        return this.component.findElement(confirmShippingAddress1Sel).getText();
    }

    public String getConfirmShippingAddress2() {
        return this.component.findElement(confirmShippingAddress2Sel).getText();
    }

    public String getConfirmCity() {
        return this.component.findElement(confirmCitySel).getText();
    }

    public String getConfirmZipCode() {
        return this.component.findElement(confirmZipCodeSel).getText();
    }

    public String getConfirmCountry() {
        return this.component.findElement(confirmCountrySel).getText();
    }

    public String getItemSectionHeader() {
        return this.component.findElement(itemSectionHeaderSel).getText();
    }

    public void clickOnPlaceOrderButton() {
        this.component.findElement(placeOrderBtnSel).click();
    }

    public List<ItemComponent> getItemListComponent() {
        List<WebElement> itemList = this.component.findElements(ItemComponent.selector);
        return itemList.stream().map(element -> {
            return new ItemComponent(driver, element);
        }).collect(Collectors.toList());
    }
}
