package Tests;

import Base.BaseClass;
import Pages.AIConversationCoahPage;
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

    @BeforeClass
    public void SetUpTests() {
        super.SetUp(URLAIConversationCoach);
        loginPage = new AIConversationCoahPage(driver);
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
        // Step 1: Login into the application with the given username and password
        if(!loginPage.login(username,password)) softAssert.fail();

// Step 2: Navigate to and verify the Repositories page
        if(!loginPage.repositories()) softAssert.fail();

// Step 3: Navigate to and verify the Subscription Plans page
        if(!loginPage.subscriptionPlansPage()) softAssert.fail();

// Step 4: Navigate to and verify the Billing History page
        if(!loginPage.billingHistoryPage()) softAssert.fail();

// Step 5: Verify the complete Logout flow from the application
        if(!loginPage.verifyLogoutFlow()) softAssert.fail();

    }
}
