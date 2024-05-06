package model.components.checkout;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import model.components.Component;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ItemComponent extends Component {

    public static final By selector = AppiumBy.xpath("//android.widget.TextView[@text=\"Items:\"]/following-sibling::android.view.ViewGroup//android.widget.ImageView/following-sibling::android.view.ViewGroup");
    private By itemNameSel = AppiumBy.accessibilityId("itemName");
    private By itemPriceSel = AppiumBy.accessibilityId("itemPrice");

    public ItemComponent(AppiumDriver driver, WebElement component){
        super(driver, component);
    }

    public String getItemName(){
        return this.component.findElement(itemNameSel).getText();
    }

    public int getItemPrice() {
        String priceStr = this.component.findElement(itemPriceSel).getText();
        String[] priceArr = priceStr.split("\\s");
        return Integer.parseInt(priceArr[priceArr.length - 1]);
    }
}
