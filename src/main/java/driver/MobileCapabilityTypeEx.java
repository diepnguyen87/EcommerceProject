package driver;

import io.appium.java_client.remote.MobileCapabilityType;

public interface MobileCapabilityTypeEx extends MobileCapabilityType {
    String AUTOMATION_NAME = "automationName";
    String PLATFORM_NAME = "platformName";
    String APP_PACKAGE = "appPackage";
    String APP_ACTIVITY = "appActivity";
    String BUNDLE_ID = "bundleId";
    String SYSTEM_PORT = "systemPort";
    String WDA_LOCAL_PORT = "wdaLocalPort";
}
