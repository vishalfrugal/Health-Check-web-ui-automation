package Tests;

import Base.BaseClass;
import Pages.KnowledgeGymSingaporePage;
import io.qameta.allure.*;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

public class KnowledgeGymSingaporeTest extends BaseClass {
    KnowledgeGymSingaporePage loginPage;
    String URLKnowledgeGymSingapore = "https://knowledge-empire.com/backend/module";
    String companyCode = "MANUALTEST";
    String username = "uiauto@yopmail.com";
    String password = "Test@123";

    @BeforeClass
    public void SetUpTests() {
        super.SetUp(URLKnowledgeGymSingapore);
        loginPage = new KnowledgeGymSingaporePage(driver);
    }

    @AfterClass
    public void TearDown() {
        driver.quit();
    }

    @Epic("Knowledge Empire Singapore Application")
    @Feature("Health Check")
    @Story("Successful functionality of the app")
    @Severity(SeverityLevel.CRITICAL)
    @Test(description = "Test: Login successfully using valid credentials and checking health of all pages")
    public void VerifyKnowledgeGymSingaporeHealthCheck() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();

        // Attempt login using username, password, and company code; fail if login is unsuccessful
        if (!loginPage.nativeLogin(username, password, companyCode)) softAssert.fail();

        // Check if the User List page is displayed with the correct heading
        if (!loginPage.userList()) softAssert.fail();

        // Check if the Group List page is accessible and correctly loaded
        if (!loginPage.groupList()) softAssert.fail();

        // Check if the Question List page is accessible and correctly loaded
        if (!loginPage.QuestionList()) softAssert.fail();

        // Check if the Modules List page is accessible and correctly loaded
        if (!loginPage.ModulesList()) softAssert.fail();

        // Attempt to log out and verify successful logout by checking redirection or message
        if (!loginPage.LogOut()) softAssert.fail();

        softAssert.assertAll();
    }
}
