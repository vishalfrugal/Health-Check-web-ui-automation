package Tests;

import Base.BaseClass;
import Pages.UserEdocPage;
import Utility.utils;
import io.qameta.allure.*;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

public class UserEdocTest extends BaseClass {
    UserEdocPage loginPage;
    String URLUserEdoc = "https://xsitestg.singaporetech.edu.sg/d2l/loginh/";
    String username = "eDOCstudent13";
    String password = "P@ssword#45678";

    utils utilities;

    @BeforeClass
    public void SetUpTests() {
        super.SetUp(URLUserEdoc);
        loginPage = new UserEdocPage(driver);
        utilities = new utils(driver);
    }

    @AfterClass
    public void TearDown() {
        driver.quit();
    }

    @Epic("User Edoc Application")
    @Feature("Health Check")
    @Story("Successful functionality of the app")
    @Severity(SeverityLevel.CRITICAL)
    @Test(description = "Test: Login successfully using valid credentials and checking health of all pages")
    public void VerifyUserEdocHealthCheck() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();

        // Attempt login using provided credentials and fail the test if login is unsuccessful
        try {
            boolean loggedIn = loginPage.nativeLogin(username, password);
            if (!loggedIn) {
                utilities.TakeScreenshotOnSoftAssertion("Failed to login - nativeLogin returned false");
                softAssert.fail("Failed to login - nativeLogin returned false");
            }
        } catch (Throwable t) {
            // Capture screenshot for exceptions (e.g. TimeoutException)
            try {
                utilities.TakeScreenshotOnSoftAssertion("Login Failed - " + t.getClass().getSimpleName());
            } catch (Exception ignore) {
                // utils handles its own errors; avoid hiding original failure
            }
            softAssert.fail("Exception during login: " + t.getMessage());
        }

        // Verify that the Home page loads successfully and the correct welcome message is displayed
        if (!loginPage.Home()) {
            utilities.TakeScreenshotOnSoftAssertion("Home page verification failed");
            softAssert.fail("Home page verification failed");
        }

        // Verify that the Assignment page is accessible and the heading is correctly displayed
        if (!loginPage.Assignment()) {
            utilities.TakeScreenshotOnSoftAssertion("Assignment page verification failed");
            softAssert.fail("Assignment page verification failed");
        }

        // Attempt to log out and verify that the logout confirmation message is shown
        if (!loginPage.LogOut()) {
            utilities.TakeScreenshotOnSoftAssertion("Logout page verification failed");
            softAssert.fail("Logout page verification failed");
        }

        softAssert.assertAll();
    }
}
