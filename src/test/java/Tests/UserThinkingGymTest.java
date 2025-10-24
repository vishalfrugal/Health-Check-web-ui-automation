package Tests;

import Base.BaseClass;
import Pages.UserThinkingGymPage;
import io.qameta.allure.*;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

public class UserThinkingGymTest extends BaseClass {
    UserThinkingGymPage loginPage;
    String URLUserThinkingGym = "https://xsitestg.singaporetech.edu.sg/d2l/loginh/";
    String username = "eDOCstudent13";
    String password = "P@ssword#45678";

    @BeforeClass
    public void SetUpTests() {
        super.SetUp(URLUserThinkingGym);
        loginPage = new UserThinkingGymPage(driver);
    }

    @AfterClass
    public void TearDown() {
        driver.quit();
    }

    @Epic("User Thinking Gym Application")
    @Feature("Health Check")
    @Story("Successful functionality of the app")
    @Severity(SeverityLevel.CRITICAL)
    @Test(description = "Test: Login successfully using valid credentials and checking health of all pages")
    public void VerifyUserThinkingGymHealthCheck() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();

        // Attempt to log in with valid username and password; fail if login is unsuccessful
        if (!loginPage.nativeLogin(username, password)) softAssert.fail();

        // Verify that the Home page loads and the correct welcome message is displayed
        if (!loginPage.Home()) softAssert.fail();

        // Verify that the Task List page is accessible and displays the correct heading
        if (!loginPage.TaskList()) softAssert.fail();

        // Verify that the Feedback Task page is accessible and displays the correct heading
        if (!loginPage.FeedBackTask()) softAssert.fail();

        // Verify that the ChatBot page is accessible and displays the correct heading
        if (!loginPage.ChatBot()) softAssert.fail();

        // Attempt to log out and verify that the logout confirmation message is displayed
        if (!loginPage.LogOut()) softAssert.fail();

        softAssert.assertAll();
    }
}
