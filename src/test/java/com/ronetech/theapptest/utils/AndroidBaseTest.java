package com.ronetech.theapptest.utils;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;


public class AndroidBaseTest {
    public AndroidDriver driver;
    public AppiumDriverLocalService service;

    @BeforeClass(alwaysRun = true)
    public void setUp() throws MalformedURLException {
        final DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("platformName", "android");
        caps.setCapability("automationName", "UiAutomator2");
        caps.setCapability("noReset", true);

        if (System.getenv("BITRISE_APK_PATH") == null) {
            // Local Appium Server
            caps.setCapability("deviceName", "Pixel 4 API 30");
            caps.setCapability("skipServerInstallation", false);
            caps.setCapability("appPackage", "com.ronetech.theapp");
            caps.setCapability("appActivity", "com.ronetech.theapp.ui.login.LoginActivity");
            caps.setCapability("app", System.getProperty("user.dir") + "/src/test/java/com/ronetech/theapptest/resources/app-debug.apk");
        } else {
            //Bitrise Config
            caps.setCapability("uiautomator2ServerLaunchTimeout", 90000);
            caps.setCapability("platformVersion", "9");
            caps.setCapability("deviceName", "Android Emulator");
            caps.setCapability("app", System.getenv("BITRISE_APK_PATH"));
        }
        driver = new AndroidDriver(new URL("http://localhost:4723/wd/hub"), caps);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
        if (service != null) {
            service.stop();
        }
    }

    //To Start the Appium Server Programmatically
    private void startLocalService() {
        service = new AppiumServiceBuilder()
                .withAppiumJS(new File("/opt/homebrew/lib/node_modules/appium/lib/main.js"))
                .withIPAddress("127.0.0.1").usingPort(Integer.parseInt("4723")).build();
        service.start();
    }

}
