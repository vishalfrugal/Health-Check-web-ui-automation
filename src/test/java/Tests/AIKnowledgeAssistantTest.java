package Tests;

import Base.BaseClass;
import Pages.AIKnowledgeAssistantPage;
import Utility.utils;
import io.qameta.allure.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class AIKnowledgeAssistantTest extends BaseClass {
    AIKnowledgeAssistantPage loginPage;
    String URLAIknowledgeAssistant = "https://ai-assistant.bes-learning.com/admin/login";
    String username = "vishal@mailinator.com";
    String password = "Vishal@0580";

    utils utilities;

    @BeforeClass
    public void SetUpTests() {
        super.SetUp(URLAIknowledgeAssistant);
        loginPage = new AIKnowledgeAssistantPage(driver);
        utilities = new utils(driver);
    }

    @AfterClass
    public void TearDown() {
        driver.quit();
    }

    @Epic("AI Knowledge Assistant Application")
    @Feature("Health Check")
    @Story("Successful functionality of the app")
    @Severity(SeverityLevel.CRITICAL)
    @Test(description = "Test: Login successfully using valid credentials and checking health of all pages")
    public void VerifyAIKnowledgeAssistantHealthCheck() throws InterruptedException {
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

        // Step 3: Navigate to and verify the Analytics page
        if(!loginPage.analyticsPage()) {
            utilities.TakeScreenshotOnSoftAssertion("Analytics page verification failed");
            softAssert.fail("Analytics page verification failed");
        }

        // Step 4: Navigate to and verify the Subscription Plans page
        if(!loginPage.subscriptionPlansPage()) {
            utilities.TakeScreenshotOnSoftAssertion("Subscription Plans page verification failed");
            softAssert.fail("Subscription Plans page verification failed");
        }

        // Step 5: Navigate to and verify the Billing History page
        if(!loginPage.billingHistoryPage()) {
            utilities.TakeScreenshotOnSoftAssertion("Billing History page verification failed");
            softAssert.fail("Billing History page verification failed");
        }

        // Step 6: Navigate to and verify the Configure Settings page
        if(!loginPage.verifyConfigureSettingsPage()) {
            utilities.TakeScreenshotOnSoftAssertion("Configure Settings page verification failed");
            softAssert.fail("Configure Settings page verification failed");
        }

        // Step 7: Verify the complete Logout flow from the application
        if(!loginPage.verifyLogoutFlow()) {
            utilities.TakeScreenshotOnSoftAssertion("Logout Flow page verification failed");
            softAssert.fail("Logout Flow page verification failed");
        }
    }
}
