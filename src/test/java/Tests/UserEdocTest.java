package Tests;

import Base.BaseClass;
import Pages.UserEdocPage;
import io.qameta.allure.*;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

public class UserEdocTest extends BaseClass {
    UserEdocPage loginPage;
    String URLUserEdoc = "https://xsitestg.singaporetech.edu.sg/d2l/loginh/";
    String username = "eDOCstudent13";
    String password = "P@ssword#45678";

    @BeforeClass
    public void SetUpTests() {
        super.SetUp(URLUserEdoc);
        loginPage = new UserEdocPage(driver);
    }

    @AfterClass
    public void TearDown() {
        driver.quit();
    }

    @Epic("User Edoc Application")
    @Feature("Health Check")
    @Story("Successful functionality of the app")
    @Severity(SeverityLevel.CRITICAL)
    @Test(description = "Test: Login successfully using valid credentials and checking health of all pages")
    public void VerifyUserEdocHealthCheck() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();

        // Attempt to log in with valid username and password; fail if login is unsuccessful
        if (!loginPage.nativeLogin(username, password)) softAssert.fail();

        // Verify that the Home page loads successfully and the correct welcome message is displayed
        if (!loginPage.Home()) softAssert.fail();

        // Verify that the Assignment page is accessible and the heading is correctly displayed
        if (!loginPage.Assignment()) softAssert.fail();

        // Attempt to log out and verify that the logout confirmation message is shown
        if (!loginPage.LogOut()) softAssert.fail();

        softAssert.assertAll();
    }
}
