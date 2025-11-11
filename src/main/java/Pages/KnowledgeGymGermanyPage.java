package Pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Objects;

public class KnowledgeGymGermanyPage {
    public WebDriver driver;
    public WebDriverWait wait;

    public KnowledgeGymGermanyPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // XPath
    final By CompanyCodeField = By.id("companyCode");
    final By SubmitBtn = By.xpath("//button[text()='Submit']");
    final By EmailField = By.id("email");
    final By PasswordField = By.id("password");
    final By SignInBtn = By.xpath("//button[text()='sign in']");
    final By UserListHeading = By.xpath("//h5[text()='User List']");
    final By GroupLink = By.xpath("//span[@class='right-nav-text' and text()='Group']");
    final By GroupListHeading = By.xpath("//h5[text()='Group List']");
    final By QuestionLink = By.xpath("//span[@class='right-nav-text' and text()='Questions']");
    final By QuestionListHeading = By.xpath("//h5[text()='Question List']");
    final By ModulesLink = By.xpath("//span[@class='right-nav-text' and text()='Modules']");
    final By ModulesListHeading = By.xpath("//h5[text()='Modules List ']");
    final By ProfilePictureLink = By.xpath("//img[@class='user-auth-img img-circle']");
    final By LogOutBtn = By.xpath("//span[normalize-space()='Log Out']");

    @Step("Entering company code: {companyCode}")
    public void enterCompanyCode(String companyCode) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(CompanyCodeField));
        driver.findElement(CompanyCodeField).sendKeys(companyCode);
    }

    @Step("Clicking Submit Button")
    public void clickSubmitButton() {
        wait.until(ExpectedConditions.elementToBeClickable(SubmitBtn));
        driver.findElement(SubmitBtn).click();
    }

    @Step("Entering username: {username}")
    public void enterUsername(String username) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(EmailField));
        driver.findElement(EmailField).sendKeys(username);
    }

    @Step("Entering password")
    public void enterPassword(String password) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(PasswordField));
        driver.findElement(PasswordField).sendKeys(password);
    }


    @Step("Clicking Sign In Button")
    public void clickSignInButton() throws InterruptedException {
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
    public void clickQuestionLink() {
        wait.until(ExpectedConditions.elementToBeClickable(QuestionLink));
        driver.findElement(QuestionLink).click();
    }

    @Step("Clicking on Modules Link")
    public void clickModulesLink() {
        wait.until(ExpectedConditions.elementToBeClickable(ModulesLink));
        driver.findElement(ModulesLink).click();
    }

    @Step("Clicking on Profile Picture")
    public void clickProfilePicture() throws InterruptedException {
        Thread.sleep(4000);
        wait.until(ExpectedConditions.elementToBeClickable(ProfilePictureLink));
        driver.findElement(ProfilePictureLink).click();
    }

    @Step("Clicking on Log Out Button")
    public void clickLogoutButton() {
        wait.until(ExpectedConditions.elementToBeClickable(LogOutBtn));
        driver.findElement(LogOutBtn).click();
    }

    // Logs into the application using company code, username, and password; verifies redirection to user dashboard
    public boolean nativeLogin(String username, String password, String companyCode) throws InterruptedException {
        enterCompanyCode(companyCode);
        clickSubmitButton();
        wait.until(ExpectedConditions.visibilityOfElementLocated(EmailField));
        enterUsername(username);
        enterPassword(password);
        clickSignInButton();
        wait.until(ExpectedConditions.urlToBe("https://de-21.knowledge-empire.com/backend/users"));
        return Objects.equals(driver.getCurrentUrl(), "https://de-21.knowledge-empire.com/backend/users");
    }

    // Verifies that the user is on the User List page by checking the heading
    public boolean userList() {
        return Objects.equals(driver.findElement(UserListHeading).getText(), "User List");
    }

    // Navigates to the Group List page and verifies the heading
    public boolean groupList() throws InterruptedException {
        wait.until(ExpectedConditions.elementToBeClickable(GroupLink));
        clickGroupLink();
        wait.until(ExpectedConditions.visibilityOfElementLocated(GroupListHeading));
        return Objects.equals(driver.findElement(GroupListHeading).getText(), "Group List");
    }

    // Navigates to the Question List page and verifies the heading
    public boolean QuestionList() throws InterruptedException {
        wait.until(ExpectedConditions.elementToBeClickable(QuestionLink));
        clickQuestionLink();
        wait.until(ExpectedConditions.visibilityOfElementLocated(QuestionListHeading));
        return Objects.equals(driver.findElement(QuestionListHeading).getText(), "Question List==");
    }

    // Navigates to the Modules List page and verifies the heading
    public boolean ModulesList() throws InterruptedException {
        wait.until(ExpectedConditions.elementToBeClickable(ModulesLink));
        clickModulesLink();
        wait.until(ExpectedConditions.visibilityOfElementLocated(ModulesListHeading));
        return Objects.equals(driver.findElement(ModulesListHeading).getText(), "Modules List");
    }

    // Logs the user out and verifies redirection to the backend login/home page
    public boolean LogOut() throws InterruptedException {
        clickProfilePicture();
        clickLogoutButton();
        wait.until(ExpectedConditions.urlToBe("https://de-21.knowledge-empire.com/backend/"));
        return Objects.equals(driver.getCurrentUrl(), "https://de-21.knowledge-empire.com/backend/");
    }
}
