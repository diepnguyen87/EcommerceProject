package driver;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

public class DriverFactory implements MobileCapabilityTypeEx {
    AppiumDriver appiumDriver;

    public AppiumDriver getDriver(String udid, String deviceName, Platform platformName, String platformVersion, String systemPort) {

        if (appiumDriver == null) {
            String remoteInfoViaEnvVar = System.getenv("remote");
            String remoteInfoViaCommandVar = System.getProperty("remote");
            String isRemote = remoteInfoViaEnvVar == null ? remoteInfoViaCommandVar : remoteInfoViaEnvVar;

            String targetServer = "http://localhost:4725/wd/hub";
            if(isRemote.equals("true")){
                String hubIPAddress = System.getenv("hub");
                if (hubIPAddress == null) {
                    hubIPAddress = System.getProperty("hub");
                }

                if(hubIPAddress == null){
                    throw new IllegalArgumentException("Please provide hub ip address via env variable [hub]!");
                }

                targetServer = hubIPAddress + ":4444/wd/hub";
            }

            if(isRemote == null){
                throw new RuntimeException("[ERROR] Please provide remote variable via environment or command line variable!");
            }

            URL appiumServer = null;
            try {
                appiumServer = new URL(targetServer);
            } catch (MalformedURLException malExc) {
                malExc.printStackTrace();
            }

            if (appiumServer == null) {
                throw new RuntimeException("[ERROR] Somehow we coult NOT contruct appium server URL");
            }
            DesiredCapabilities caps = new DesiredCapabilities();
            caps.setCapability(PLATFORM_NAME, platformName);

            switch (platformName) {
                case ANDROID:
                    caps.setCapability(AUTOMATION_NAME, "uiautomator2");
                    caps.setCapability(UDID, udid);
                    caps.setCapability(APP_PACKAGE, "com.tuhuynh.sdetproecommerce");
                    caps.setCapability(APP_ACTIVITY, "host.exp.exponent.MainActivity");
                    caps.setCapability(SYSTEM_PORT, systemPort);
                    appiumDriver = new AndroidDriver(appiumServer, caps);
                    break;
                case IOS:
                    caps.setCapability(AUTOMATION_NAME, "XCUITest");
                    caps.setCapability(UDID, udid);
                    caps.setCapability(DEVICE_NAME, deviceName);
                    caps.setCapability(PLATFORM_VERSION, platformVersion);
                    caps.setCapability(BUNDLE_ID, "com.tuhuynh.sdetproecommerce");
                    caps.setCapability(WDA_LOCAL_PORT, systemPort);
                    appiumDriver = new IOSDriver(appiumServer, caps);
                    break;
                default:
                    throw new IllegalArgumentException("Platform type can not be null");
            }
        }
        return appiumDriver;
    }

    public void quitAppiumSession(){
        if(appiumDriver != null){
            appiumDriver.quit();
        }
    }
}
