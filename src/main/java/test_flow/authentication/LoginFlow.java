package test_flow.authentication;

import io.appium.java_client.AppiumDriver;
import model.components.global.PopupNotificationComp;
import model.components.login.LoginComponent;
import model.pages.UserPage;
import org.testng.Assert;
import test_flow.BaseFlow;

public class LoginFlow extends BaseFlow {

    public LoginFlow(AppiumDriver driver) {
        super(driver);
    }

    public void login(String emailAddress, String password) {
        LoginComponent loginComp = new UserPage(driver).getLoginComp();
        loginComp.email(emailAddress);
        loginComp.password(password);
        loginComp.clickOnLoginButton();
    }

    public void verifyLoginWithCorrectCredentials() {
        Assert.assertTrue(new UserPage(driver).signoutButton().isDisplayed());
    }

    public void verifyLoginWithIncorrectCredentials() {
        PopupNotificationComp popupNotificationComp = new UserPage(driver).getPopupNotificationComp();
        String expectedMessageInfo1 = "Please provide correct credentials";
        Assert.assertEquals(popupNotificationComp.getMessageInfo1(), expectedMessageInfo1);
    }
}
