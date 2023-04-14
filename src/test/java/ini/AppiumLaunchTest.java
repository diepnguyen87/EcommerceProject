package ini;

import io.appium.java_client.*;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

public class AppiumLaunchTest {

    public static void main(String[] args) throws InterruptedException {
        AppiumDriver driver = null;

        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("platformName", "android");
        caps.setCapability("automationName", "uiautomator2");
        caps.setCapability("udid", "1A021FDF60019Z");
        caps.setCapability("appPackage", "com.tuhuynh.sdetproecommerce");
        caps.setCapability("appActivity", "host.exp.exponent.MainActivity");

        URL appiumServer = null;
        try {
            appiumServer = new URL("http://localhost:4723/wd/hub/");
        } catch (MalformedURLException malExc) {
            malExc.printStackTrace();
        }

        if (appiumServer == null) {
            throw new RuntimeException("[ERROR] Somehow we coult NOT contruct appium server URL");
        }

        driver = new AndroidDriver(appiumServer, caps);

        Thread.sleep(3000);
    }
}
