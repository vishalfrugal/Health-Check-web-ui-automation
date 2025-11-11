package Tests;

import Base.BaseClass;
import Pages.UserBeCourseCataloguePage;
import Utility.utils;
import io.qameta.allure.*;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

public class UserBeCourseCatalogueTest extends BaseClass {
    UserBeCourseCataloguePage loginPage;
    String URLUserBeCourseCatalogue = "https://xsitestg.singaporetech.edu.sg/d2l/loginh/";
    String username = "eDOCstudent13";
    String password = "P@ssword#45678";

    utils utilities;

    @BeforeClass
    public void SetUpTests() {
        super.SetUp(URLUserBeCourseCatalogue);
        loginPage = new UserBeCourseCataloguePage(driver);
        utilities = new utils(driver);
    }

    @AfterClass
    public void TearDown() {
        driver.quit();
    }

    @Epic("User Be Course Catalogue Application")
    @Feature("Health Check")
    @Story("Successful functionality of the app")
    @Severity(SeverityLevel.CRITICAL)
    @Test(description = "Test: Login successfully using valid credentials and checking health of all pages")
    public void VerifyUserBeCourseCatalogueHealthCheck() throws InterruptedException {
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

        // Verify that the Home page is loaded and the welcome heading is displayed correctly
        if (!loginPage.Home()) {
            utilities.TakeScreenshotOnSoftAssertion("Home page verification failed");
            softAssert.fail("Home page verification failed");
        }

        // Verify that the Course List page is accessible and the correct heading is shown
        if (!loginPage.CourseList()) {
            utilities.TakeScreenshotOnSoftAssertion("Course list page verification failed");
            softAssert.fail("Course list page verification failed");
        }

        // Verify that the Competency Roadmap page is accessible and the correct heading is shown
        if (!loginPage.CompetencyRoadmap()) {
            utilities.TakeScreenshotOnSoftAssertion("Competency roadmap page verification failed");
            softAssert.fail("Competency roadmap page verification failed");
        }

        // Attempt to sign out and verify successful logout via confirmation message
        if (!loginPage.SignOut()) {
            utilities.TakeScreenshotOnSoftAssertion("Sign out page verification failed");
            softAssert.fail("Sign out page verification failed");
        }

        softAssert.assertAll();
    }
}
