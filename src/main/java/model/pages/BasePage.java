package model.pages;

import io.appium.java_client.AppiumDriver;
import model.components.Component;
import model.components.global.NavigationComponent;
import model.components.global.PopupNotificationComp;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class BasePage extends Component {

    public BasePage(AppiumDriver driver){
        super(driver);
    }

    public PopupNotificationComp getPopupNotificationComp(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(PopupNotificationComp.selector));
        return new PopupNotificationComp(driver, driver.findElement(PopupNotificationComp.selector));
    }
    public NavigationComponent getNavigationComp(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(NavigationComponent.selector));
        return new NavigationComponent(driver, driver.findElement(NavigationComponent.selector));
    }
}
