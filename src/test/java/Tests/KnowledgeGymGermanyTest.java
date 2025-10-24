package Tests;

import Base.BaseClass;
import Pages.KnowledgeGymGermanyPage;
import io.qameta.allure.*;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

public class KnowledgeGymGermanyTest extends BaseClass {
    KnowledgeGymGermanyPage loginPage;
    String URLKnowledgeGymGerman = "https://de-21.knowledge-empire.com/backend/";
    String companyCode = "FRUGAL";
    String username = "no.one72047@gmail.com";
    String password = "Frugal@123";

    @BeforeClass
    public void SetUpTests() {
        super.SetUp(URLKnowledgeGymGerman);
        loginPage = new KnowledgeGymGermanyPage(driver);
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
        if (!loginPage.nativeLogin(username, password, companyCode)) softAssert.fail();

        // Verify that the User List page is loaded and heading is correct
        if (!loginPage.userList()) softAssert.fail();

        // Verify that the Group List page is accessible and the heading is displayed correctly
        if (!loginPage.groupList()) softAssert.fail();

        // Verify that the Question List page is accessible and the heading is displayed correctly
        if (!loginPage.QuestionList()) softAssert.fail();

        // Verify that the Modules List page is accessible and the heading is displayed correctly
        if (!loginPage.ModulesList()) softAssert.fail();

        // Verify successful logout and redirection to the backend landing page
        if (!loginPage.LogOut()) softAssert.fail();

        softAssert.assertAll();
    }
}
