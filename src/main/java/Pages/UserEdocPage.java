package Pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.Objects;

public class UserEdocPage {
    public WebDriver driver;
    public WebDriverWait wait;

    public UserEdocPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // XPath
    final By GuestLoginBtn = By.id("guestLoginLinkId");
    final By EmailField = By.id("username");
    final By PasswordField = By.id("password");
    final By SignInBtn = By.xpath("//input[@value='Login']");
    String HomeLink = "https://xsitestg.singaporetech.edu.sg/d2l/common/dialogs/quickLink/quickLink.d2l?ou=25291&type=lti&rcode=SITStg-39350&srcou=25291&contentTopicId=116810";
    final By HomeHeading = By.xpath("//h3[text()='Welcome to the E-DOC']");
    final By AssignmentLink = By.xpath("//a[@id='view-tasks']");
    final By AssignmentHeading = By.xpath("//h3[text()='Assignment']");
    final By LogOutBtn = By.xpath("//div[@class='icon mt-5']");
    final By FinalLogOutBtn = By.xpath("//a[text()='Logout']");
    final By LogOutMessage = By.xpath("//p[text()='You have successfully logged out.']");

    @Step("Clicking on Guest Login Button")
    public void clickGuestLoginButton() {
        wait.until(ExpectedConditions.elementToBeClickable(GuestLoginBtn));
        driver.findElement(GuestLoginBtn).click();
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

    @Step("Navigating to Home Page")
    public void navigateToHomePage() {
        driver.get(HomeLink);
    }

    @Step("Clicking on Assignment Link")
    public void clickAssignmentLink() {
        wait.until(ExpectedConditions.elementToBeClickable(AssignmentLink));
        driver.findElement(AssignmentLink).click();
    }

    @Step("Clicking on Log Out Button")
    public void clickLogoutButton() throws InterruptedException {
        Thread.sleep(5000);
        wait.until(ExpectedConditions.elementToBeClickable(LogOutBtn));
        driver.findElement(LogOutBtn).click();
    }

    @Step("Clicking on Final Log Out Button")
    public void clickFinalLogoutButton() {
        wait.until(ExpectedConditions.elementToBeClickable(FinalLogOutBtn));
        driver.findElement(FinalLogOutBtn).click();
    }

    // Logs in using guest credentials and verifies successful navigation to the home page
    public boolean nativeLogin(String username, String password) {
        clickGuestLoginButton();
        wait.until(ExpectedConditions.visibilityOfElementLocated(EmailField));
        enterUsername(username);
        enterPassword(password);
        clickSignInButton();
        wait.until(ExpectedConditions.urlToBe("https://xsitestg.singaporetech.edu.sg/d2l/home"));
        return Objects.equals(driver.getCurrentUrl(), "https://xsitestg.singaporetech.edu.sg/d2l/home");
    }

    // Navigates to the Home page and verifies the welcome heading
    public boolean Home() {
        navigateToHomePage();
        wait.until(ExpectedConditions.elementToBeClickable(HomeHeading));
        return Objects.equals(driver.findElement(HomeHeading).getText(), "Welcome to the E-DOC=");
    }

    // Navigates to the Assignment page and verifies the heading is displayed
    public boolean Assignment() {
        clickAssignmentLink();
        wait.until(ExpectedConditions.visibilityOfElementLocated(AssignmentHeading));
        return Objects.equals(driver.findElement(AssignmentHeading).getText(), "Assignment");
    }

    // Logs the user out and verifies the logout confirmation message
    public boolean LogOut() throws InterruptedException {
        Thread.sleep(2000);
        clickLogoutButton();
        clickFinalLogoutButton();
        wait.until(ExpectedConditions.visibilityOfElementLocated(LogOutMessage));
        return Objects.equals(driver.findElement(LogOutMessage).getText(), "You have successfully logged out.");
    }
}
