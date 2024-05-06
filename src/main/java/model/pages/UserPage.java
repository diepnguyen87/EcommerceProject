package model.pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import model.components.login.LoginComponent;
import model.components.register.RegisterComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class UserPage extends BasePage{

    private final By signoutBtnSel = AppiumBy.accessibilityId("signOutBtn");

    public UserPage(AppiumDriver driver) {
        super(driver);
    }

    public WebElement signoutButton(){
        return driver.findElement(signoutBtnSel);
    }


    public RegisterComponent getRegisterComp() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(RegisterComponent.selector));
        return new RegisterComponent(driver, driver.findElement(RegisterComponent.selector));
    }

    public LoginComponent getLoginComp() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(LoginComponent.selector));
        return new LoginComponent(driver, driver.findElement(LoginComponent.selector));
    }

}
