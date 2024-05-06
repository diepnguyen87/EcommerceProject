package test_flow.register;

import io.appium.java_client.AppiumDriver;
import model.components.global.PopupNotificationComp;
import model.components.login.LoginComponent;
import model.components.register.RegisterComponent;
import model.pages.UserPage;
import org.testng.Assert;
import test_flow.BaseFlow;

public class RegistrationFlow extends BaseFlow {

    private String email, name, phoneNumber, password;
    public RegistrationFlow(AppiumDriver driver, String email, String name, String phoneNumber, String password){
        super(driver);
        this.email = email;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.password = password;
    }

    public void clickOnRegisterBtn() {
        LoginComponent loginComp = new UserPage(driver).getLoginComp();
        loginComp.clickOnRegisterButton();
    }

    public void registerAccount() {
        RegisterComponent registerComp = new UserPage(driver).getRegisterComp();
        registerComp.email().sendKeys(email);
        registerComp.name().sendKeys(name);
        registerComp.phone().sendKeys(phoneNumber);
        registerComp.password().sendKeys(password);
        registerComp.clickOnRegisterBtn();
    }

    public void verifySucceedRegistration() {
        verifyMessageDisplayed();
        verifyLoginScreenDisplayed();
    }

    private void verifyMessageDisplayed(){
        UserPage userPage = new UserPage(driver);
        PopupNotificationComp popupNotificationComp = userPage.getPopupNotificationComp();
        String expectedSuccessMsgInfo1 = "Registration Succeeded";
        String expectedSuccessMsgInfo2 = "Please Login into your account";
        Assert.assertEquals(popupNotificationComp.getMessageInfo1(), expectedSuccessMsgInfo1);
        Assert.assertEquals(popupNotificationComp.getMessageInfo2(), expectedSuccessMsgInfo2);
    }

    private void verifyLoginScreenDisplayed(){
        UserPage userPage = new UserPage(driver);
        LoginComponent loginComp = userPage.getLoginComp();
        Assert.assertTrue(loginComp.isDisplayed());
    }
}
