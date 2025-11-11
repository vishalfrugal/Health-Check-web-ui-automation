package Tests;

import Base.BaseClass;
import Pages.AdminThinkingGymPage;
import Utility.utils;
import io.qameta.allure.*;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

public class AdminThinkingGymTest extends BaseClass {
    AdminThinkingGymPage loginPage;
    String URLAdminThinkingGym = "https://xsitestg.singaporetech.edu.sg/d2l/loginh/";
    String username = "eDOCinstructor05";
    String password = "P@ssword#45678";

    utils utilities;

    @BeforeClass
    public void SetUpTests() {
        super.SetUp(URLAdminThinkingGym);
        loginPage = new AdminThinkingGymPage(driver);
        utilities = new utils(driver);
    }

    @AfterClass
    public void TearDown() {
        driver.quit();
    }

    @Epic("Admin Thinking Gym Application")
    @Feature("Health Check")
    @Story("Successful functionality of the app")
    @Severity(SeverityLevel.CRITICAL)
    @Test(description = "Test: Login successfully using valid credentials and checking health of all pages")
    public void VerifyAdminThinkingGymHealthCheck() throws InterruptedException {
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

        // Verify that the Users page is loaded and heading is correct
        if (!loginPage.users()) {
            utilities.TakeScreenshotOnSoftAssertion("Users page verification failed");
            softAssert.fail("Users page verification failed");
        }

        // Verify that the Modules page is accessible and correctly loaded
        if (!loginPage.Modules()) {
            utilities.TakeScreenshotOnSoftAssertion("Modules page verification failed");
            softAssert.fail("Modules page verification failed");
        }

        // Verify that the Submission page is accessible and correctly loaded
        if (!loginPage.Submission()) {
            utilities.TakeScreenshotOnSoftAssertion("Submission page verification failed");
            softAssert.fail("Submission page verification failed");
        }

        // Verify that the Task Types page is accessible and correctly loaded
        if (!loginPage.TaskTypes()) {
            utilities.TakeScreenshotOnSoftAssertion("Task Types page verification failed");
            softAssert.fail("Task Types page verification failed");
        }

        // Verify that the user is logged out successfully and redirected properly
        if (!loginPage.LogOut()) {
            utilities.TakeScreenshotOnSoftAssertion("Logout verification failed");
            softAssert.fail("Logout verification failed");
        }

        softAssert.assertAll();
    }
}
