package com.ronetech.theapptest;

import com.ronetech.theapptest.android.HomePage;
import com.ronetech.theapptest.android.LoginPage;
import com.ronetech.theapptest.utils.AndroidBaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class RegressionTest extends AndroidBaseTest {

    @Test(description = "Verify that a user cannot login to the application with invalid credentials")
    public void testInvalidLogin() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("Bob", "123");
    }

    @Test(description = "Verify that a user can login to the application with valid credentials")
    public void testValidLogin() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("Bob", "Welcome@123");
        HomePage homePage = new HomePage(driver);
        Assert.assertEquals(homePage.getHomeLabelText(), "This is home Fragment");
    }
}