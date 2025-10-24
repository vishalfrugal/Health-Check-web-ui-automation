package Tests;

import Base.BaseClass;
import Pages.AdminEdocPage;
import io.qameta.allure.*;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;


public class AdminEdocTest extends BaseClass {
    AdminEdocPage loginPage;
    String URLAdminEdoc = "https://xsitestg.singaporetech.edu.sg/d2l/loginh/";
    String username = "eDOCinstructor05";
    String password = "P@ssword#45678";

    @BeforeClass
    public void SetUpTests() {
        super.SetUp(URLAdminEdoc);
        loginPage = new AdminEdocPage(driver);
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
        // Verify that user is able to log in with valid credentials
        if(!loginPage.nativeLogin(username, password)) softAssert.fail();

        // Verify that the Users page is accessible and the heading is displayed correctly
        if(!loginPage.users()) softAssert.fail();

        // Verify that the Modules page is accessible and the heading is displayed correctly
        if(!loginPage.Modules()) softAssert.fail();

        // Verify that the Case Notes page is accessible and the heading is displayed correctly
        if(!loginPage.CaseNotes()) softAssert.fail();

        // Verify that the Student Score page is accessible and the heading is displayed correctly
        if(!loginPage.StudentScore()) softAssert.fail();

        // Verify that the Analytics page is accessible and the heading is displayed correctly
        if(!loginPage.Analytics()) softAssert.fail();

        // Verify that the user is able to log out and the logout confirmation message is displayed
        if(!loginPage.LogOut()) softAssert.fail();


        softAssert.assertAll();
    }
}
