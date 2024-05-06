package tests;

import driver.DriverFactory;
import driver.Platform;
import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.testng.ITestResult;
import org.testng.annotations.Optional;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class BaseTest {

    private static final List<DriverFactory> driverFactoryList = Collections.synchronizedList(new ArrayList<>());
    protected static ThreadLocal<DriverFactory> currentDriverThread;
    protected String platformName;
    protected String platformVersion;
    protected String udid;
    protected String systemPort;

    @BeforeClass
    @Parameters({"udid", "systemPort", "platformName", "platformVersion"})
    public void getTestParams(String udid, String systemPort, String platformName, @Optional("platformVersion") String platformVersion) {
        this.udid = udid;
        this.systemPort = systemPort;
        this.platformName = platformName.toUpperCase();
        this.platformVersion = platformVersion;
    }

    @BeforeTest
    public void initAppiumSession() {
        currentDriverThread = ThreadLocal.withInitial(() -> {
            DriverFactory driverFactory = new DriverFactory();
            driverFactoryList.add(driverFactory);
            return driverFactory;
        });
    }

    public AppiumDriver getDriver() {
        return currentDriverThread.get().getDriver(Platform.valueOf(platformName), platformVersion, udid, systemPort);
    }

    @AfterMethod
    public void captureScreenshot(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            Calendar calendar = new GregorianCalendar();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH) + 1;
            int date = calendar.get(Calendar.DATE);
            int hour = calendar.get(Calendar.HOUR);
            int mins = calendar.get(Calendar.MINUTE);
            int second = calendar.get(Calendar.SECOND);
            String takenTime = year + "-" + month + "-" + date + "-" + hour + "-" + mins + "-" + second;

            String fileName = result.getName() + "_" + takenTime + ".png";
            String tartgetPath = System.getProperty("user.dir").concat("/screenshot/").concat(fileName);
            try {
                File sourceFile = getDriver().getScreenshotAs(OutputType.FILE);
                com.google.common.io.Files.copy(sourceFile, new File(tartgetPath));
                InputStream is = Files.newInputStream(Paths.get(tartgetPath));
                Allure.attachment(result.getName(), is);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @AfterClass
    public void afterClass() {
       /* currentDriverThread.get().resetApp(platformName);*/
    }

    @AfterTest(alwaysRun = true)
    public void quitAppiumSession() {
        currentDriverThread.get().quitAppiumSession();
    }
}
