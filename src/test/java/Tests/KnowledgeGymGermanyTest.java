package Tests;

import Base.BaseClass;
import Pages.KnowledgeGymGermanyPage;
import Utility.utils;
import io.qameta.allure.*;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

public class KnowledgeGymGermanyTest extends BaseClass {
    KnowledgeGymGermanyPage loginPage;
    String URLKnowledgeGymGerman = "https://de-21.knowledge-empire.com/backend/";
    String companyCode = "FRUGAL";
    String username = "no.one72047@gmail.com";
    String password = "Frugal@123";

    utils utilities;

    @BeforeClass
    public void SetUpTests() {
        super.SetUp(URLKnowledgeGymGerman);
        loginPage = new KnowledgeGymGermanyPage(driver);
        utilities = new utils(driver);
    }

    @AfterClass
    public void TearDown() {
        driver.quit();
    }

    @Epic("Knowledge Empire German Application")
    @Feature("Health Check")
    @Story("Successful functionality of the app")
    @Severity(SeverityLevel.CRITICAL)
    @Test(description = "Test: Login successfully using valid credentials and checking health of all pages")
    public void VerifyKnowledgeGymGermanyHealthCheck() throws InterruptedException {
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

        // Verify that the User List page is loaded and heading is correct
        if (!loginPage.userList()){
            Thread.sleep(1000);
            utilities.TakeScreenshotOnSoftAssertion("User List page verification failed");
            softAssert.fail("User List page verification failed");
        }

        // Verify that the Group List page is accessible and the heading is displayed correctly
        if (!loginPage.groupList()) {
            Thread.sleep(1000);
            utilities.TakeScreenshotOnSoftAssertion("Group List page verification failed");
            softAssert.fail("Group List page verification failed");
        }

        // Verify that the Question List page is accessible and the heading is displayed correctly
        if (!loginPage.QuestionList()) {
            Thread.sleep(1000);
            utilities.TakeScreenshotOnSoftAssertion("Question List page verification failed");
            softAssert.fail("Question List page verification failed");
        }

        // Verify that the Modules List page is accessible and the heading is displayed correctly
        if (!loginPage.ModulesList()) {
            Thread.sleep(1000);
            utilities.TakeScreenshotOnSoftAssertion("Modules List page verification failed");
            softAssert.fail("Modules List page verification failed");
        }

        // Verify successful logout and redirection to the backend landing page
        if (!loginPage.LogOut()) {
            Thread.sleep(1000);
            utilities.TakeScreenshotOnSoftAssertion("Logout verification failed");
            softAssert.fail("Logout verification failed");
        }

        softAssert.assertAll();
    }
}
