package Tests;

import Base.BaseClass;
import Pages.AdminBeCourseCataloguePage;
import Utility.utils;
import io.qameta.allure.*;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

public class AdminBeCourseCatalogueTest extends BaseClass {
    AdminBeCourseCataloguePage loginPage;
    String URLAdminBeCourseCatalogue = "https://xsitestg.singaporetech.edu.sg/d2l/loginh/";
    String username = "eDOCinstructor05";
    String password = "P@ssword#45678";

    utils utilities;

    @BeforeClass
    public void SetUpTests() {
        super.SetUp(URLAdminBeCourseCatalogue);
        loginPage = new AdminBeCourseCataloguePage(driver);
        utilities = new utils(driver);
    }

    @AfterClass
    public void TearDown() {
        driver.quit();
    }

    @Epic("Admin Be Course Catalogue Application")
    @Feature("Health Check")
    @Story("Successful functionality of the app")
    @Severity(SeverityLevel.CRITICAL)
    @Test(description = "Test: Login successfully using valid credentials and checking health of all pages")
    public void VerifyAdminBeCourseCatalogueHealthCheck() throws InterruptedException {
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

        // Verify navigation to the Users page and fail if the heading is not as expected
        if (!loginPage.users()) {
            utilities.TakeScreenshotOnSoftAssertion("Users page verification failed");
            softAssert.fail("Users page verification failed");
        }

        // Verify navigation to the Edit Dropdown List page and fail if the heading is incorrect
        if (!loginPage.EditDropdownList()) {
            utilities.TakeScreenshotOnSoftAssertion("Edit Dropdown List page verification failed");
            softAssert.fail("Edit Dropdown List page verification failed");
        }

        // Verify navigation to the Course List page and fail if the expected heading is missing
        if (!loginPage.CourseList()) {
            utilities.TakeScreenshotOnSoftAssertion("Course List page verification failed");
            softAssert.fail("Course List page verification failed");
        }

        // Verify navigation to the Competency Roadmap List page and fail if the heading doesn't match
        if (!loginPage.CompetencyRoadmapList()) {
            utilities.TakeScreenshotOnSoftAssertion("Competency Roadmap List page verification failed");
            softAssert.fail("Competency Roadmap List page verification failed");
        }

        // Verify navigation to the User Usage Analytics page and fail if the heading is incorrect
        if (!loginPage.UserUsageAnalytics()) {
            utilities.TakeScreenshotOnSoftAssertion("User Usage Analytics page verification failed");
            softAssert.fail("User Usage Analytics page verification failed");
        }

        // Attempt logout and fail the test if logout confirmation message is not as expected
        if (!loginPage.LogOut()) {
            utilities.TakeScreenshotOnSoftAssertion("Logout failed - LogOut returned false");
            softAssert.fail("Logout failed - LogOut returned false");
        }

        softAssert.assertAll();
    }
}
