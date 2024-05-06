package model.components.login;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import model.components.Component;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class LoginComponent extends Component {

    public final static By selector = AppiumBy.xpath("//android.view.View[@text=\"User\"]/parent::android.view.ViewGroup/following-sibling::android.view.ViewGroup");
    private final By emailInputSel = AppiumBy.accessibilityId("emailInput");
    private final By passwordInputSel = AppiumBy.accessibilityId("passwordInput");
    private final By loginBtnSel = AppiumBy.accessibilityId("loginBtn");
    private final By registerBtnSel = AppiumBy.accessibilityId("registerAccountBtn");

    public LoginComponent(AppiumDriver driver, WebElement component){
        super(driver, component);
    }

    public boolean isDisplayed(){
        return component.isDisplayed();
    }

    public void email(String email){
        this.component.findElement(emailInputSel).sendKeys(email);
    }

    public void password(String password){
        this.component.findElement(passwordInputSel).sendKeys(password);
    }

    public void clickOnLoginButton(){
        this.component.findElement(loginBtnSel).click();
    }
    public void clickOnRegisterButton(){
        this.component.findElement(registerBtnSel).click();
    }
}
