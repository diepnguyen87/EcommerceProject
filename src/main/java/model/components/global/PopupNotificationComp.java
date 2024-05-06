package model.components.global;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import model.components.Component;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class PopupNotificationComp extends Component {

    public static final By selector = AppiumBy.id("animatedView");
    private final By leadingIconSel = AppiumBy.id("leadingIcon");
    private final By messageInfo1Sel = AppiumBy.id("text1");
    private final By messageInfo2Sel = AppiumBy.id("text2");
    private final By trailingIconSel = AppiumBy.id("trailingIcon");
    public PopupNotificationComp(AppiumDriver driver, WebElement component){
        super(driver, component);
    }

    public void isLeadingIconDisplayed(){
        this.component.findElement(leadingIconSel).isDisplayed();
    }

    public String getMessageInfo1(){
        return this.component.findElement(messageInfo1Sel).getText();
    }

    public String getMessageInfo2(){
        return this.component.findElement(messageInfo2Sel).getText();
    }

    public void isTrailingIconDisplayed(){
        this.component.findElement(trailingIconSel).isDisplayed();
    }

}
