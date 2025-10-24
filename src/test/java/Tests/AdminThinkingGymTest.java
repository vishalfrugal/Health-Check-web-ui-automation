package Tests;

import Base.BaseClass;
import Pages.AdminThinkingGymPage;
import io.qameta.allure.*;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

public class AdminThinkingGymTest extends BaseClass {
    AdminThinkingGymPage loginPage;
    String URLAdminThinkingGym = "https://xsitestg.singaporetech.edu.sg/d2l/loginh/";
    String username = "eDOCinstructor05";
    String password = "P@ssword#45678";

    @BeforeClass
    public void SetUpTests() {
        super.SetUp(URLAdminThinkingGym);
        loginPage = new AdminThinkingGymPage(driver);
    }

    @AfterClass
    public void TearDown() {
        driver.quit();
    }

    @Epic("Admin Thinking Gym Application")
    @Feature("Health Check")
    @Story("Successful functionality of the app")
    @Severity(SeverityLevel.CRITICAL)
    @Test(description = "Test: Login successfully using valid credentials and checking health of all pages")
    public void VerifyAdminThinkingGymHealthCheck() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();

        // Verify successful login with valid username and password
        if (!loginPage.nativeLogin(username, password)) softAssert.fail();

        // Verify that the Users page is loaded and heading is correct
        if (!loginPage.users()) softAssert.fail();

        // Verify that the Modules page is accessible and correctly loaded
        if (!loginPage.Modules()) softAssert.fail();

        // Verify that the Submission page is accessible and correctly loaded
        if (!loginPage.Submission()) softAssert.fail();

        // Verify that the Task Types page is accessible and correctly loaded
        if (!loginPage.TaskTypes()) softAssert.fail();

        // Verify that the user is logged out successfully and redirected properly
        if (!loginPage.LogOut()) softAssert.fail();

        softAssert.assertAll();
    }
}
