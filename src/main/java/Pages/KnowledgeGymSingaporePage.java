package Pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.Objects;

public class KnowledgeGymSingaporePage {
    public WebDriver driver;
    public WebDriverWait wait;

    public KnowledgeGymSingaporePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(60));
    }

    // XPath
    final By CompanyCodeField = By.id("companyCode");
    final By SubmitBtn = By.xpath("//button[@onclick='veryfyCompanyCode()']");
    final By EmailField = By.id("email");
    final By PasswordField = By.id("password");
    final By SignInBtn = By.xpath("//button[text()='sign in']");
    final By UserListHeading = By.xpath("//h5[normalize-space()='User List']");
    final By GroupLink = By.xpath("//i[@class='zmdi zmdi-group mr-20']");
    final By GroupListHeading = By.xpath("//h5[@class='txt-dark']");
    final By QuestionLink = By.xpath("//span[text()='Questions']/preceding-sibling::i");
    final By QuestionListHeading = By.xpath("//h5[normalize-space()='Question List']");
    final By ModulesLink = By.xpath("//i[@class='zmdi zmdi-format-list-bulleted mr-20']");
    final By ModulesListHeading = By.xpath("//h5[text()='Modules List ']");
    final By ProfilePictureLink = By.xpath("//img[@class='user-auth-img img-circle']");
    final By LogOutBtn = By.xpath("//span[normalize-space()='Log Out']");

    @Step("Entering Company Code: {companyCode}")
    public void enterCompanyCode(String companyCode) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(CompanyCodeField));
        driver.findElement(CompanyCodeField).sendKeys(companyCode);
    }

    @Step("Clicking on Submit Button")
    public void clickSubmitButton() {
        wait.until(ExpectedConditions.elementToBeClickable(SubmitBtn));
        driver.findElement(SubmitBtn).click();
    }

    @Step("Entering Username: {username}")
    public void enterUsername(String username) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(EmailField));
        driver.findElement(EmailField).sendKeys(username);
    }

    @Step("Entering Password")
    public void enterPassword(String password) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(PasswordField));
        driver.findElement(PasswordField).sendKeys(password);
    }

    @Step("Clicking on Sign In Button")
    public void clickSignInButton() {
        wait.until(ExpectedConditions.elementToBeClickable(SignInBtn));
        driver.findElement(SignInBtn).click();
    }

    @Step("Clicking on Group Link")
    public void clickGroupLink() throws InterruptedException {
        Thread.sleep(5000);
        wait.until(ExpectedConditions.elementToBeClickable(GroupLink));
        driver.findElement(GroupLink).click();
    }

    @Step("Clicking on Question Link")
    public void clickQuestionLink() throws InterruptedException {
        Thread.sleep(5000);
        wait.until(ExpectedConditions.elementToBeClickable(QuestionLink));
        driver.findElement(QuestionLink).click();
    }

    @Step("Clicking on Modules Link")
    public void clickModulesLink() throws InterruptedException {
        Thread.sleep(5000);
        wait.until(ExpectedConditions.elementToBeClickable(ModulesLink));
        driver.findElement(ModulesLink).click();
    }

    @Step("Clicking on Profile Picture")
    public void clickProfilePicture() throws InterruptedException {
        Thread.sleep(5000);
        wait.until(ExpectedConditions.elementToBeClickable(ProfilePictureLink));
        driver.findElement(ProfilePictureLink).click();
    }

    @Step("Clicking on Log Out Button")
    public void clickLogoutButton() {
        wait.until(ExpectedConditions.elementToBeClickable(LogOutBtn));
        driver.findElement(LogOutBtn).click();
    }

    // Logs in to the application using company code, username, and password; verifies redirection to the user dashboard
    public boolean nativeLogin(String username, String password, String companyCode) throws InterruptedException {
        enterCompanyCode(companyCode);
        clickSubmitButton();
        wait.until(ExpectedConditions.visibilityOfElementLocated(EmailField));
        enterUsername(username);
        enterPassword(password);
        clickSignInButton();
        wait.until(ExpectedConditions.urlToBe("https://knowledge-empire.com/backend/users"));
        return Objects.equals(driver.getCurrentUrl(), "https://knowledge-empire.com/backend/users");
    }

    // Verifies that the User List page is displayed by checking the heading text
    public boolean userList() {
        return Objects.equals(driver.findElement(UserListHeading).getText(), "User List=");
    }

    // Navigates to the Group List page and verifies the heading is displayed correctly
    public boolean groupList() throws InterruptedException {
        wait.until(ExpectedConditions.elementToBeClickable(GroupLink));
        clickGroupLink();
        wait.until(ExpectedConditions.visibilityOfElementLocated(GroupListHeading));
        return Objects.equals(driver.findElement(GroupListHeading).getText(), "Group List");
    }

    // Navigates to the Question List page and verifies the heading is displayed correctly
    public boolean QuestionList() throws InterruptedException {
        wait.until(ExpectedConditions.elementToBeClickable(QuestionLink));
        clickQuestionLink();
        wait.until(ExpectedConditions.visibilityOfElementLocated(QuestionListHeading));
        return Objects.equals(driver.findElement(QuestionListHeading).getText(), "Question List");
    }

    // Navigates to the Modules List page and verifies the heading is displayed correctly
    public boolean ModulesList() throws InterruptedException {
        wait.until(ExpectedConditions.elementToBeClickable(ModulesLink));
        clickModulesLink();
        wait.until(ExpectedConditions.visibilityOfElementLocated(ModulesListHeading));
        return Objects.equals(driver.findElement(ModulesListHeading).getText(), "Modules List");
    }

    // Logs the user out and verifies redirection to the backend landing page
    public boolean LogOut() throws InterruptedException {
        clickProfilePicture();
        clickLogoutButton();
        wait.until(ExpectedConditions.urlToBe("https://knowledge-empire.com/backend/"));
        return Objects.equals(driver.getCurrentUrl(), "https://knowledge-empire.com/backend/");
    }
}
