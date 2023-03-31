package com.ronetech.theapptest.android;


import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class HomePage {
    private final AppiumDriver driver;

    public HomePage(AppiumDriver driver) {
        this.driver = driver;
    }

    public String getHomeLabelText() {
        return driver.findElement(By.id("text_home")).getText();
    }
}