package Pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.Objects;

public class AdminThinkingGymPage {
    public WebDriver driver;
    public WebDriverWait wait;

    public AdminThinkingGymPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    // XPath
    final By GuestLoginBtn = By.id("guestLoginLinkId");
    final By EmailField = By.id("username");
    final By PasswordField = By.id("password");
    final By SignInBtn = By.xpath("//input[@value='Login']");
    String userLink = "https://xsitestg.singaporetech.edu.sg/d2l/common/dialogs/quickLink/quickLink.d2l?ou=25291&type=lti&rcode=SITStg-39352&srcou=25291&contentTopicId=116811";
    final By UserListHeading = By.xpath("//div[@class='container-fluid']/h1");
    final By ModulesLink = By.xpath("//i[@class='fa fa-th-large']");
    final By ModulesHeading = By.xpath("//div[@class='container-fluid']/h1");
    final By SubmissionLink = By.xpath("//i[@class='fa fa-folder-open']");
    final By SubmissionHeading = By.xpath("//div[@class='container-fluid']/h1");
    final By TaskTypesLink = By.xpath("//a[@href='https://thinking-gym.com/forms']");
    final By TaskTypesHeading = By.xpath("//div[@class='container-fluid']/h1");
    final By ProfilePictureLink = By.xpath("//img[@class='img-profile rounded-circle']");
    final By LogOutBtn = By.xpath("//a[@class='dropdown-item']");
    final By FinalLogOutBtn = By.xpath("//button[text()='Cancel']/following-sibling::a");
    final By LogOutMessage = By.xpath("//h4[text()='Successfully Logged out!']");

    @Step("Clicking on Guest Login Button")
    public void clickGuestLoginButton() {
        wait.until(ExpectedConditions.elementToBeClickable(GuestLoginBtn));
        driver.findElement(GuestLoginBtn).click();
    }

    @Step("Entering email: {email}")
    public void enterEmail(String email) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(EmailField));
        driver.findElement(EmailField).sendKeys(email);
    }

    @Step("Entering password")
    public void enterPassword(String password) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(PasswordField));
        driver.findElement(PasswordField).sendKeys(password);
    }

    @Step("Clicking on Sign In Button")
    public void clickSignInButton() {
        wait.until(ExpectedConditions.elementToBeClickable(SignInBtn));
        driver.findElement(SignInBtn).click();
    }

    @Step("Navigating to Users Page")
    public void navigateToUsersPage() {
        driver.get(userLink);
    }

    @Step("Clicking on Modules Link")
    public void clickModulesLink() {
        wait.until(ExpectedConditions.elementToBeClickable(ModulesLink));
        driver.findElement(ModulesLink).click();
    }

    @Step("Clicking on Submission Link")
    public void clickSubmissionLink() {
        wait.until(ExpectedConditions.elementToBeClickable(SubmissionLink));
        driver.findElement(SubmissionLink).click();
    }

    @Step("Clicking on Task Types Link")
    public void clickTaskTypesLink() {
        wait.until(ExpectedConditions.elementToBeClickable(TaskTypesLink));
        driver.findElement(TaskTypesLink).click();
    }

    @Step("Clicking on Profile Picture")
    public void clickProfilePicture() {
        wait.until(ExpectedConditions.elementToBeClickable(ProfilePictureLink));
        driver.findElement(ProfilePictureLink).click();
    }

    @Step("Clicking on Log Out Button")
    public void clickLogoutButton() {
        wait.until(ExpectedConditions.elementToBeClickable(LogOutBtn));
        driver.findElement(LogOutBtn).click();
    }

    @Step("Clicking on Final Log Out Button")
    public void clickFinalLogoutButton() {
        wait.until(ExpectedConditions.elementToBeClickable(FinalLogOutBtn));
        driver.findElement(FinalLogOutBtn).click();
    }

    // Performs login using guest credentials and verifies redirection to the home page
    public boolean nativeLogin(String username, String password) throws InterruptedException {
        clickGuestLoginButton();
        wait.until(ExpectedConditions.visibilityOfElementLocated(EmailField));
        enterEmail(username);
        enterPassword(password);
        clickSignInButton();
        wait.until(ExpectedConditions.urlToBe("https://xsitestg.singaporetech.edu.sg/d2l/home"));
        return Objects.equals(driver.getCurrentUrl(), "https://xsitestg.singaporetech.edu.sg/d2l/home");
    }

    // Navigates to the Users page and validates that the "Users" heading is displayed
    public boolean users() {
        navigateToUsersPage();
        wait.until(ExpectedConditions.elementToBeClickable(UserListHeading));
        return Objects.equals(driver.findElement(UserListHeading).getText(), "Users");
    }

    // Navigates to the Modules page and verifies the "Modules" heading
    public boolean Modules() throws InterruptedException {
        wait.until(ExpectedConditions.elementToBeClickable(ModulesLink));
        clickModulesLink();
        wait.until(ExpectedConditions.textToBePresentInElementLocated(ModulesHeading, "Modules"));
        return Objects.equals(driver.findElement(ModulesHeading).getText(), "Modules");
    }

    // Navigates to the Submission page and verifies the "Submission" heading
    public boolean Submission() throws InterruptedException {
        wait.until(ExpectedConditions.elementToBeClickable(SubmissionLink));
        clickSubmissionLink();
        wait.until(ExpectedConditions.textToBePresentInElementLocated(SubmissionHeading, "Submission"));
        return Objects.equals(driver.findElement(SubmissionHeading).getText(), "Submission");
    }

    // Navigates to the Task Types page and verifies the "Task types" heading
    public boolean TaskTypes() throws InterruptedException {
        wait.until(ExpectedConditions.elementToBeClickable(TaskTypesLink));
        clickTaskTypesLink();
        wait.until(ExpectedConditions.textToBePresentInElementLocated(TaskTypesHeading, "Task types"));
        return Objects.equals(driver.findElement(TaskTypesHeading).getText(), "Task types");
    }

    // Logs the user out and verifies the success message
    public boolean LogOut() throws InterruptedException {
        clickProfilePicture();
        wait.until(ExpectedConditions.elementToBeClickable(LogOutBtn));
        clickLogoutButton();
        wait.until(ExpectedConditions.elementToBeClickable(FinalLogOutBtn));
        clickFinalLogoutButton();
        wait.until(ExpectedConditions.visibilityOfElementLocated(LogOutMessage));
        return Objects.equals(driver.findElement(LogOutMessage).getText(), "Successfully Logged out!");
    }
}
