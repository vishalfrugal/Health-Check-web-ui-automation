package Tests;

import Base.BaseClass;
import Pages.AdminEdocPage;
import Utility.utils;
import io.qameta.allure.*;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;


public class AdminEdocTest extends BaseClass {
    AdminEdocPage loginPage;
    String URLAdminEdoc = "https://xsitestg.singaporetech.edu.sg/d2l/loginh/";
    String username = "eDOCinstructor05";
    String password = "P@ssword#45678";

    utils utilities;

    @BeforeClass
    public void SetUpTests() {
        super.SetUp(URLAdminEdoc);
        loginPage = new AdminEdocPage(driver);
        utilities = new utils(driver);
    }

    @AfterClass
    public void TearDown() {
        driver.quit();
    }

    @Epic("Admin Edoc Application")
    @Feature("Health Check")
    @Story("Successful functionality of the app")
    @Severity(SeverityLevel.CRITICAL)
    @Test(description = "Test: Login successfully using valid credentials and checking health of all pages")
    public void VerifyAdminEdocHealthCheck() throws InterruptedException {
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

        // Verify that the Users page is accessible and the heading is displayed correctly
        if(!loginPage.users()) {
            utilities.TakeScreenshotOnSoftAssertion("Users page verification failed");
            softAssert.fail("Users page verification failed");
        }

        // Verify that the Modules page is accessible and the heading is displayed correctly
        if(!loginPage.Modules()) {
            utilities.TakeScreenshotOnSoftAssertion("Modules page verification failed");
            softAssert.fail("Modules page verification failed");
        }

        // Verify that the Case Notes page is accessible and the heading is displayed correctly
        if(!loginPage.CaseNotes()) {
            utilities.TakeScreenshotOnSoftAssertion("Case Notes page verification failed");
            softAssert.fail("Case Notes page verification failed");
        }

        // Verify that the Student Score page is accessible and the heading is displayed correctly
        if(!loginPage.StudentScore()) {
            utilities.TakeScreenshotOnSoftAssertion("Student Score page verification failed");
            softAssert.fail("Student Score page verification failed");
        }

        // Verify that the Analytics page is accessible and the heading is displayed correctly
        if(!loginPage.Analytics()) {
            utilities.TakeScreenshotOnSoftAssertion("Analytics page verification failed");
            softAssert.fail("Analytics page verification failed");
        }

        // Verify that the user is able to log out and the logout confirmation message is displayed
        if(!loginPage.LogOut()) {
            utilities.TakeScreenshotOnSoftAssertion("Logout failed - LogOut returned false");
            softAssert.fail("Logout failed - LogOut returned false");
        }


        softAssert.assertAll();
    }
}
