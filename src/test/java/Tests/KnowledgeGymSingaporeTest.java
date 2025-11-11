package Tests;

import Base.BaseClass;
import Pages.KnowledgeGymSingaporePage;
import Utility.utils;
import io.qameta.allure.*;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

public class KnowledgeGymSingaporeTest extends BaseClass {
    KnowledgeGymSingaporePage loginPage;
    String URLKnowledgeGymSingapore = "https://knowledge-empire.com/backend/module";
    String companyCode = "MANUALTEST";
    String username = "uiauto@yopmail.com";
    String password = "Test@123";

    utils utilities;

    @BeforeClass
    public void SetUpTests() {
        super.SetUp(URLKnowledgeGymSingapore);
        loginPage = new KnowledgeGymSingaporePage(driver);
        utilities = new utils(driver);
    }

    @AfterClass
    public void TearDown() {
        driver.quit();
    }

    @Epic("Knowledge Empire Singapore Application")
    @Feature("Health Check")
    @Story("Successful functionality of the app")
    @Severity(SeverityLevel.CRITICAL)
    @Test(description = "Test: Login successfully using valid credentials and checking health of all pages")
    public void VerifyKnowledgeGymSingaporeHealthCheck() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();

        // Verify successful login using username, password, and company code
        try {
            boolean loggedIn = loginPage.nativeLogin(username, password, companyCode);
            if (!loggedIn) {
                utilities.TakeScreenshotOnSoftAssertion("Login failed - returned false");
                softAssert.fail("nativeLogin returned false");
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

        // Check if the User List page is displayed with the correct heading
        if (!loginPage.userList()) {
            Thread.sleep(1000);
            utilities.TakeScreenshotOnSoftAssertion("User List page verification failed");
            softAssert.fail("User List page verification failed");
        }

        // Check if the Group List page is accessible and correctly loaded
        if (!loginPage.groupList()) {
            utilities.TakeScreenshotOnSoftAssertion("Group List page verification failed");
            softAssert.fail("Group List page verification failed");
        }

        // Check if the Question List page is accessible and correctly loaded
        if (!loginPage.QuestionList()) {
            utilities.TakeScreenshotOnSoftAssertion("Question List page verification failed");
            softAssert.fail("Question List page verification failed");
        }

        // Check if the Modules List page is accessible and correctly loaded
        if (!loginPage.ModulesList()) {
            utilities.TakeScreenshotOnSoftAssertion("Modules List page verification failed");
            softAssert.fail("Modules List page verification failed");
        }

        // Attempt to log out and verify successful logout by checking redirection or message
        if (!loginPage.LogOut()) {
            utilities.TakeScreenshotOnSoftAssertion("Logout verification failed");
            softAssert.fail("Logout verification failed");
        }

        softAssert.assertAll();
    }
}
