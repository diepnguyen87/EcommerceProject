package model.components.global;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import model.components.Component;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class NavigationComponent extends Component {

    public static final By selector = AppiumBy.xpath("//android.view.View[@content-desc=\"homeIcon\"]/parent::android.view.View/parent::android.view.ViewGroup");
    private final By homeIconSel = AppiumBy.accessibilityId("homeIcon");

    private final By cartIconSel = AppiumBy.accessibilityId("cartIcon");
    private final String orderQualitySel = "//android.view.View[@content-desc=\"cartIcon\"]//android.widget.TextView[@text=\"3\"]";
    private final By userIconSel = AppiumBy.accessibilityId("userIcon");

    public NavigationComponent(AppiumDriver driver, WebElement component) {
        super(driver, component);
    }

    public void clickOnHomeIcon() {
        component.findElement(homeIconSel).click();
    }

    public void clickOnCartIcon() {
        component.findElement(cartIconSel).click();
    }

    public boolean isOrderQualityUnDisplayedOnCartIcon(int expectedOrderQuality){
        try{
            this.component.findElement(AppiumBy.xpath(String.format(orderQualitySel, expectedOrderQuality)));
        }catch (Exception e){
            return true;
        }
        return false;
    }
    public String getOrderQualityOnCartIcon(int expectedOrderQuality) {
        return this.component.findElement(AppiumBy.xpath(String.format(orderQualitySel, expectedOrderQuality))).getText();
    }

    public void clickOnUserIcon() {
        component.findElement(userIconSel).click();
    }
}
