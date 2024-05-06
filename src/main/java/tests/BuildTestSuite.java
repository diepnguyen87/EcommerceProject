package tests;

import com.google.common.reflect.ClassPath;
import driver.MobileCapabilityType;
import driver.Platform;
import org.testng.TestNG;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;
import test_data.model.DeviceList;
import test_data.model.DeviceList.Device;
import test_data.util.DataObjectBuilder;

import java.io.IOException;
import java.security.SecureRandom;
import java.util.*;

public class BuildTestSuite implements MobileCapabilityType {

    public static void main(String[] args) throws IOException {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        List<Class<?>> testClasses = new ArrayList<>();
        for (ClassPath.ClassInfo info : ClassPath.from(loader).getTopLevelClasses()) {
            String classInfoName = info.getName();
            boolean startWithTestDot = classInfoName.startsWith("tests.");
            boolean isBaseTest = classInfoName.startsWith("tests.BaseTest");
            boolean isBuildTestSuite = classInfoName.startsWith("tests.BuildTestSuite");
            if (startWithTestDot && !isBaseTest && !isBuildTestSuite) {
                testClasses.add(info.load());
            }
        }

        String platformName = System.getProperty("platformName").toUpperCase();
        if (platformName == null) {
            throw new IllegalArgumentException("[ERROR] Please supply -DplatformName in commandline/IDE");
        }

        try {
            Platform.valueOf(platformName);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("[ERROR] The system support only " + Arrays.toString(Platform.values()));
        }

        String deviceListJsonPath = "/src/main/resources/DeviceList.json";
        DeviceList deviceList = DataObjectBuilder.buildDataObject(deviceListJsonPath, DeviceList.class);
        List<Device> androidDeviceList = deviceList.getAndroidDeviceList();
        List<Device> iosDeviceList = deviceList.getIosDeviceList();
        List<Device> deviceListByPlatform = platformName.equals("ANDROID") ? androidDeviceList : iosDeviceList;

        int testNumberEachDevice = testClasses.size() / deviceListByPlatform.size();
        Map<Device, List<Class<?>>> deviceTestClassesMap = new HashMap<>();

        for (int deviceIndex = 0; deviceIndex < deviceListByPlatform.size(); deviceIndex++) {
            int startIndex = deviceIndex * testNumberEachDevice;
            boolean isLastDevice = deviceIndex == deviceListByPlatform.size() - 1;
            int endIndex = isLastDevice ? testClasses.size() : startIndex + testNumberEachDevice;
            List<Class<?>> testClassesSubList = testClasses.subList(startIndex, endIndex);
            deviceTestClassesMap.put(deviceListByPlatform.get(deviceIndex), testClassesSubList);
        }

        TestNG testNG = new TestNG();
        XmlSuite suite = new XmlSuite();
        suite.setName("Regression");

        List<XmlTest> allTest = new ArrayList<>();
        for (Device device : deviceTestClassesMap.keySet()) {
            XmlTest test = new XmlTest(suite);
            test.setName(device.getDeviceName());
            List<XmlClass> xmlClasses = new ArrayList<>();
            List<Class<?>> testClassesByDevice = deviceTestClassesMap.get(device);

            for (Class<?> testClass : testClassesByDevice) {
                xmlClasses.add(new XmlClass(testClass));
            }

            test.setClasses(xmlClasses);
            test.addParameter(PLATFORM_NAME, platformName);
            test.addParameter(PLATFORM_VERSION, device.getPlatformVersion());
            test.addParameter(UDID, device.getDeviceName());
            test.addParameter(SYSTEM_PORT, new SecureRandom().nextInt(1000) + 7000 + "");
            allTest.add(test);
        }
        suite.setTests(allTest);
        suite.setParallel(XmlSuite.ParallelMode.TESTS);
        suite.setThreadCount(allTest.size());
        System.out.println(suite.toXml());

        List<XmlSuite> xmlSuiteList = new ArrayList<>();
        xmlSuiteList.add(suite);

        testNG.setXmlSuites(xmlSuiteList);
        testNG.run();
    }
}
