package Tests;

import Base.BaseClass;
import Pages.UserBeCourseCataloguePage;
import io.qameta.allure.*;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

public class UserBeCourseCatalogueTest extends BaseClass {
    UserBeCourseCataloguePage loginPage;
    String URLUserBeCourseCatalogue = "https://xsitestg.singaporetech.edu.sg/d2l/loginh/";
    String username = "eDOCstudent13";
    String password = "P@ssword#45678";

    @BeforeClass
    public void SetUpTests() {
        super.SetUp(URLUserBeCourseCatalogue);
        loginPage = new UserBeCourseCataloguePage(driver);
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

        // Attempt to log in with username and password; fail if login is unsuccessful
        if (!loginPage.nativeLogin(username, password)) softAssert.fail();

        // Verify that the Home page is loaded and the welcome heading is displayed correctly
        if (!loginPage.Home()) softAssert.fail();

        // Verify that the Course List page is accessible and the correct heading is shown
        if (!loginPage.CourseList()) softAssert.fail();

        // Verify that the Competency Roadmap page is accessible and the correct heading is shown
        if (!loginPage.CompetencyRoadmap()) softAssert.fail();

        // Attempt to sign out and verify successful logout via confirmation message
        if (!loginPage.SignOut()) softAssert.fail();

        softAssert.assertAll();
    }
}
