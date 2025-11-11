package Tests;

import Base.BaseClass;
import Pages.UserThinkingGymPage;
import Utility.utils;
import io.qameta.allure.*;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

public class UserThinkingGymTest extends BaseClass {
    UserThinkingGymPage loginPage;
    String URLUserThinkingGym = "https://xsitestg.singaporetech.edu.sg/d2l/loginh/";
    String username = "eDOCstudent13";
    String password = "P@ssword#45678";

    utils utilities;
    @BeforeClass
    public void SetUpTests() {
        super.SetUp(URLUserThinkingGym);
        loginPage = new UserThinkingGymPage(driver);
        utilities = new utils(driver);
    }

    @AfterClass
    public void TearDown() {
        driver.quit();
    }

    @Epic("User Thinking Gym Application")
    @Feature("Health Check")
    @Story("Successful functionality of the app")
    @Severity(SeverityLevel.CRITICAL)
    @Test(description = "Test: Login successfully using valid credentials and checking health of all pages")
    public void VerifyUserThinkingGymHealthCheck() throws InterruptedException {
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

        // Verify that the Home page loads and the correct welcome message is displayed
        if (!loginPage.Home()) {
            utilities.TakeScreenshotOnSoftAssertion("Home page verification failed");
            softAssert.fail("Home page verification failed");
        }

        // Verify that the Task List page is accessible and displays the correct heading
        if (!loginPage.TaskList()) {
            utilities.TakeScreenshotOnSoftAssertion("Task List page verification failed");
            softAssert.fail("Task List page verification failed");
        }

        // Verify that the Feedback Task page is accessible and displays the correct heading
        if (!loginPage.FeedBackTask()) {
            utilities.TakeScreenshotOnSoftAssertion("Feed Back page verification failed");
            softAssert.fail("Feed Back page verification failed");
        }

        // Verify that the ChatBot page is accessible and displays the correct heading
        if (!loginPage.ChatBot()) {
            utilities.TakeScreenshotOnSoftAssertion("Chat Bot page verification failed");
            softAssert.fail("Chat Bot page verification failed");
        }

        // Attempt to log out and verify that the logout confirmation message is displayed
        if (!loginPage.LogOut()) {
            utilities.TakeScreenshotOnSoftAssertion("Logout page verification failed");
            softAssert.fail("Logout page verification failed");
        }

        softAssert.assertAll();
    }
}
