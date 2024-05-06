package model.components.checkout;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import model.Direction;
import model.components.Component;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.WebElement;

public class ShippingComponent extends Component {

    public static final By selector = AppiumBy.xpath("//android.view.View[@text=\"Checkout\"]/parent::android.view.ViewGroup/following-sibling::android.view.ViewGroup/android.view.ViewGroup/following-sibling::android.view.ViewGroup");
    private By headerSel = AppiumBy.xpath("//android.widget.TextView[@text=\"Shipping Address\"]");
    private By phoneNumInputSel = AppiumBy.accessibilityId("phoneNum");
    private By add1InputSel = AppiumBy.accessibilityId("shippingAdd1");
    private By add2InputSel = AppiumBy.accessibilityId("shippingAdd2");
    private By cityInputSel = AppiumBy.accessibilityId("shippingCity");
    private By zipCodeInputSel = AppiumBy.accessibilityId("shippingZIPCode");
    private By countryDropdownListSel = AppiumBy.accessibilityId("selectCountryTrigger");
    private By countryListViewSel = AppiumBy.xpath("//android.widget.CheckedTextView[@text=\"Anguilla\"]/parent::android.widget.ListView");
    private String dynamicCountryNameSel = "//android.widget.CheckedTextView[@text=\"%s\"]";
    private By confirmShippingFormSel = AppiumBy.accessibilityId("confirmShippingFormBtn");

    public ShippingComponent(AppiumDriver driver, WebElement component) {
        super(driver, component);
    }

    public String getHeader() {
        return this.component.findElement(headerSel).getText();
    }

    public WebElement phoneNum() {
        return this.component.findElement(phoneNumInputSel);
    }

    public WebElement address1() {
        return this.component.findElement(add1InputSel);
    }

    public WebElement address2() {
        return this.component.findElement(add2InputSel);
    }

    public WebElement city() {
        return this.component.findElement(cityInputSel);
    }

    public WebElement zipCode() {
        while (true) {
            try {
                return this.component.findElement(zipCodeInputSel);
            } catch (NoSuchElementException e) {
                int startY = getMidPointByPixel();
                swipeVerticalByPixel(Direction.DOWN, startY, 15);
            }
        }
    }

    public void selectCountry(String countryName) {
        while (true) {
            try {
                this.component.findElement(countryDropdownListSel).click();
                break;
            } catch (NoSuchElementException e) {
                int startY = getMidPointByPixel();
                swipeVerticalByPixel(Direction.DOWN, startY, 15);
            }
        }

        Rectangle rect = driver.findElement(countryListViewSel).getRect();
        int endY = rect.getY();
        int startY = endY + rect.getHeight();

        while (true) {
            try {
                driver.findElement(AppiumBy.xpath(String.format(dynamicCountryNameSel, countryName))).click();
                break;
            } catch (NoSuchElementException e) {
                swipeVerticalByPixel(Direction.DOWN, startY, 50);
            }
        }
    }

    public void clickOnConfirmShippingBtn() {
        while (true) {
            try {
                this.component.findElement(confirmShippingFormSel).click();
                break;
            } catch (NoSuchElementException e) {
                int startY = getMidPointByPixel();
                swipeVerticalByPixel(Direction.DOWN, startY, 50);
            }
        }
    }

    private int getMidPointByPixel() {
        Rectangle rect = this.component.getRect();
        return rect.getY() + rect.getHeight() / 2;
    }
}
