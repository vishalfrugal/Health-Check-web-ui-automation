package Tests;

import Base.BaseClass;
import Pages.AIKnowledgeAssistantPage;
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

    @BeforeClass
    public void SetUpTests() {
        super.SetUp(URLAIknowledgeAssistant);
        loginPage = new AIKnowledgeAssistantPage(driver);
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
        // Step 1: Login into the application with the provided username and password
        if(!loginPage.login(username,password)) softAssert.fail();

        // Step 2: Navigate to and verify the Repositories page
        if(!loginPage.repositories()) softAssert.fail();

        // Step 3: Navigate to and verify the Analytics page
        if(!loginPage.analyticsPage()) softAssert.fail();

        // Step 4: Navigate to and verify the Subscription Plans page
        if(!loginPage.subscriptionPlansPage()) softAssert.fail();

        // Step 5: Navigate to and verify the Billing History page
        if(!loginPage.billingHistoryPage()) softAssert.fail();

        // Step 6: Navigate to and verify the Configure Settings page
        if(!loginPage.verifyConfigureSettingsPage()) softAssert.fail();

        // Step 7: Verify the complete Logout flow from the application
        if(!loginPage.verifyLogoutFlow()) softAssert.fail();
    }
}
