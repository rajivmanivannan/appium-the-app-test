package com.ronetech.theapptest.utils;

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;

import java.util.HashMap;
import java.util.Map;

//https://github.com/appium/appium-xcuitest-driver/blob/master/docs/actions.md
public class IOSGestureActions {
    private final IOSDriver driver;

    public IOSGestureActions(IOSDriver driver) {
        this.driver = driver;
    }

    public void longPress(WebElement ele) {
        Map<String, Object> params = new HashMap<>();
        params.put("element", ((RemoteWebElement) ele).getId());
        params.put("duration", 5);
        driver.executeScript("mobile:touchAndHold", params);
    }

    public void scrollToEnd() {
        boolean canScrollMore;
        do {
            canScrollMore = (Boolean) ((JavascriptExecutor) driver).executeScript("mobile: scrollGesture", ImmutableMap.of(
                    "left", 100, "top", 100, "width", 200, "height", 200,
                    "direction", "down",
                    "percent", 3.0

            ));
        } while (canScrollMore);
    }

    public void scrollTo(WebElement element, String direction) {
        Map<String, Object> params = new HashMap<>();
        params.put("direction", direction);
        params.put("element", ((RemoteWebElement) element).getId());
        driver.executeScript("mobile:scroll", params);
    }


    public void swipe(WebElement element, String direction) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("direction", direction);
        params.put("element", ((RemoteWebElement) element).getId());
        driver.executeScript("mobile:swipe", params);
    }

}
