package Tests;

import Base.BaseClass;
import Pages.AdminBeCourseCataloguePage;
import io.qameta.allure.*;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

public class AdminBeCourseCatalogueTest extends BaseClass {
    AdminBeCourseCataloguePage loginPage;
    String URLAdminBeCourseCatalogue = "https://xsitestg.singaporetech.edu.sg/d2l/loginh/";
    String username = "eDOCinstructor05";
    String password = "P@ssword#45678";

    @BeforeClass
    public void SetUpTests() {
        super.SetUp(URLAdminBeCourseCatalogue);
        loginPage = new AdminBeCourseCataloguePage(driver);
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
        if (!loginPage.nativeLogin(username, password)) softAssert.fail();

        // Verify navigation to the Users page and fail if the heading is not as expected
        if (!loginPage.users()) softAssert.fail();

        // Verify navigation to the Edit Dropdown List page and fail if the heading is incorrect
        if (!loginPage.EditDropdownList()) softAssert.fail();

        // Verify navigation to the Course List page and fail if the expected heading is missing
        if (!loginPage.CourseList()) softAssert.fail();

        // Verify navigation to the Competency Roadmap List page and fail if the heading doesn't match
        if (!loginPage.CompetencyRoadmapList()) softAssert.fail();

        // Verify navigation to the User Usage Analytics page and fail if the heading is incorrect
        if (!loginPage.UserUsageAnalytics()) softAssert.fail();

        // Attempt logout and fail the test if logout confirmation message is not as expected
        if (!loginPage.LogOut()) softAssert.fail();

        softAssert.assertAll();
    }
}
