package Tests;

import Base.BaseClass;
import Pages.AIConversationCoahPage;
import Utility.utils;
import io.qameta.allure.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class AIConversationCoachTest extends BaseClass {
    AIConversationCoahPage loginPage;
    String URLAIConversationCoach = "https://ai-coach.bes-learning.com/admin/login";
    String username = "vishal@frugaltestingin.com";
    String password = "Vishal@0580";

    utils utilities;

    @BeforeClass
    public void SetUpTests() {
        super.SetUp(URLAIConversationCoach);
        loginPage = new AIConversationCoahPage(driver);
        utilities = new utils(driver);
    }

    @AfterClass
    public void TearDown() {
        driver.quit();
    }

    @Epic("AI Conversation Coach Application")
    @Feature("Health Check")
    @Story("Successful functionality of the app")
    @Severity(SeverityLevel.CRITICAL)
    @Test(description = "Test: Login successfully using valid credentials and checking health of all pages")
    public void VerifyAIConversationCoachHealthCheck() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        // Attempt login using provided credentials and fail the test if login is unsuccessful
        try {
            boolean loggedIn = loginPage.login(username, password);
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

        // Step 2: Navigate to and verify the Repositories page
        if(!loginPage.repositories()) {
            utilities.TakeScreenshotOnSoftAssertion("Repositories page verification failed");
            softAssert.fail("Repositories page verification failed");
        }

        // Step 3: Navigate to and verify the Subscription Plans page
        if(!loginPage.subscriptionPlansPage()) {
            utilities.TakeScreenshotOnSoftAssertion("Subscription Plans page verification failed");
            softAssert.fail("Subscription Plans page verification failed");
        }

        // Step 4: Navigate to and verify the Billing History page
        if(!loginPage.billingHistoryPage()) {
            utilities.TakeScreenshotOnSoftAssertion("Billing History page verification failed");
            softAssert.fail("Billing History page verification failed");
        }

        // Step 5: Verify the complete Logout flow from the application
        if(!loginPage.verifyLogoutFlow()) {
            utilities.TakeScreenshotOnSoftAssertion("Logout flow verification failed");
            softAssert.fail("Logout flow verification failed");
        }

    }
}
