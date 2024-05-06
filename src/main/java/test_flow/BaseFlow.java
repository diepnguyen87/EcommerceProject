package test_flow;

import io.appium.java_client.AppiumDriver;
import model.components.global.NavigationComponent;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BaseFlow {
    protected AppiumDriver driver;
    protected WebDriverWait wait;

    public BaseFlow(AppiumDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(15L));
    }

    public void navigateToHomePage() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        new NavigationComponent(driver, driver.findElement(NavigationComponent.selector)).clickOnHomeIcon();
    }

    public void navigateToCartPage() {
        new NavigationComponent(driver, driver.findElement(NavigationComponent.selector)).clickOnCartIcon();
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void navigateToUserPage() {
        new NavigationComponent(driver, driver.findElement(NavigationComponent.selector)).clickOnUserIcon();
    }
}
