package tests.register;

import io.appium.java_client.AppiumDriver;
import org.testng.annotations.Test;
import tests.BaseTest;

public class RegisterTest extends BaseTest {

    private AppiumDriver driver;

    @Test
    public void register(){
        driver = getDriver();
    }
}
