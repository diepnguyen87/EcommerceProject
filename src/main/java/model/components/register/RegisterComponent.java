package model.components.register;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import model.components.Component;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class RegisterComponent extends Component {

    public static By selector = AppiumBy.xpath("//android.view.View[@text=\"User\"]/parent::android.view.ViewGroup/following-sibling::android.view.ViewGroup");
    private final By emailTxtSel = AppiumBy.accessibilityId("email");
    private final By nameTxtSel = AppiumBy.accessibilityId("name");
    private final By phoneTxtSel = AppiumBy.accessibilityId("phone");
    private final By passwordTxtSel = AppiumBy.accessibilityId("password");
    private final By registerBtnSel = AppiumBy.accessibilityId("registerBtn");

    public RegisterComponent(AppiumDriver driver, WebElement component) {
        super(driver, component);
    }

    public WebElement email() {
        return this.component.findElement(emailTxtSel);
    }

    public WebElement name() {
        return this.component.findElement(nameTxtSel);
    }

    public WebElement phone() {
        return this.component.findElement(phoneTxtSel);
    }

    public WebElement password() {
        return this.component.findElement(passwordTxtSel);
    }

    public void clickOnRegisterBtn() {
        this.component.findElement(registerBtnSel).click();
    }
}
