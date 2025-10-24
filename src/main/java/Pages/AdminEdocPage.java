package Pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.Objects;

public class AdminEdocPage {
    public WebDriver driver;
    public WebDriverWait wait;

    public AdminEdocPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    // XPath
    final By GuestLoginBtn = By.id("guestLoginLinkId");
    final By EmailField = By.id("username");
    final By PasswordField = By.id("password");
    final By SignInBtn = By.xpath("//input[@value='Login']");
    String userLink = "https://xsitestg.singaporetech.edu.sg/d2l/common/dialogs/quickLink/quickLink.d2l?ou=25291&type=lti&rcode=SITStg-39350&srcou=25291&contentTopicId=116810";
    final By UserHeading = By.xpath("//h1[text()='Users']");
    final By ModulesLink = By.xpath("//a[@class='nav-link']//i[@class='fas fa-users']");
    final By ModulesHeading = By.xpath("//h1[text()='Modules']");
    final By CaseNotesLink = By.xpath("//i[@class='fas fa-file-alt']");
    final By CaseNotesHeading = By.xpath("//h1[text()='Case Notes']");
    final By StudentScoreLink = By.xpath("//a[@href='https://edoc.singaporetech.edu.sg/student-scoring']");
    final By StudentScoreHeading = By.xpath("//h1[text()='Student Score']");
    final By AnalyticsLink = By.xpath("//i[@class='fas fa-chart-bar']");
    final By AnalyticsHeading = By.xpath("//h1[text()='Analytics']");
    final By LogOutBtn = By.xpath("//a[@class='dropdown-item']");
    final By ProfilePictureLink = By.xpath("//img[@class='img-profile rounded-circle']");
    final By FinalLogOutBtn = By.xpath("//button[text()='Cancel']/preceding-sibling::a");
    final By LogOutMessage = By.xpath("//p[text()='You have successfully logged out.']");

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

    @Step("Clicking on Case Notes Link")
    public void clickCaseNotesLink() {
        wait.until(ExpectedConditions.elementToBeClickable(CaseNotesLink));
        driver.findElement(CaseNotesLink).click();
    }

    @Step("Clicking on Student Score Link")
    public void clickStudentScoreLink() {
        wait.until(ExpectedConditions.elementToBeClickable(StudentScoreLink));
        driver.findElement(StudentScoreLink).click();
    }

    @Step("Clicking on Analytics Link")
    public void clickAnalyticsLink() {
        wait.until(ExpectedConditions.elementToBeClickable(AnalyticsLink));
        driver.findElement(AnalyticsLink).click();
    }

    @Step("Clicking on Profile Picture")
    public void clickProfilePicture() {
        wait.until(ExpectedConditions.elementToBeClickable(ProfilePictureLink));
        driver.findElement(ProfilePictureLink).click();
    }

    @Step("Clicking on Logout Button")
    public void clickLogoutButton() {
        wait.until(ExpectedConditions.elementToBeClickable(LogOutBtn));
        driver.findElement(LogOutBtn).click();
    }

    @Step("Clicking on Final Logout Button")
    public void clickFinalLogoutButton() {
        wait.until(ExpectedConditions.elementToBeClickable(FinalLogOutBtn));
        driver.findElement(FinalLogOutBtn).click();
    }

    // Performs login using guest credentials and validates successful navigation to the home page
    public boolean nativeLogin(String username, String password) throws InterruptedException {
        clickGuestLoginButton();
        wait.until(ExpectedConditions.visibilityOfElementLocated(EmailField));
        enterEmail(username);
        enterPassword(password);
        clickSignInButton();
        wait.until(ExpectedConditions.urlToBe("https://xsitestg.singaporetech.edu.sg/d2l/home"));
        return Objects.equals(driver.getCurrentUrl(), "https://xsitestg.singaporetech.edu.sg/d2l/home");
    }

    // Navigates to the Users page and verifies the Users heading is displayed
    public boolean users() {
        navigateToUsersPage();
        wait.until(ExpectedConditions.elementToBeClickable(UserHeading));
        return Objects.equals(driver.findElement(UserHeading).getText(), "Users");
    }

    // Navigates to the Modules page and checks if the correct heading is present
    public boolean Modules() throws InterruptedException {
        wait.until(ExpectedConditions.elementToBeClickable(ModulesLink));
        clickModulesLink();
        wait.until(ExpectedConditions.visibilityOfElementLocated(ModulesHeading));
        return Objects.equals(driver.findElement(ModulesHeading).getText(), "Modules");
    }

    // Navigates to the Case Notes page and validates the heading
    public boolean CaseNotes() throws InterruptedException {
        wait.until(ExpectedConditions.elementToBeClickable(CaseNotesLink));
        clickCaseNotesLink();
        wait.until(ExpectedConditions.visibilityOfElementLocated(CaseNotesHeading));
        return Objects.equals(driver.findElement(CaseNotesHeading).getText(), "Case Notes");
    }

    // Navigates to the Student Score page and validates the heading
    public boolean StudentScore() throws InterruptedException {
        wait.until(ExpectedConditions.elementToBeClickable(StudentScoreLink));
        clickStudentScoreLink();
        wait.until(ExpectedConditions.visibilityOfElementLocated(StudentScoreHeading));
        return Objects.equals(driver.findElement(StudentScoreHeading).getText(), "Student Score");
    }

    // Navigates to the Analytics page and verifies the heading
    public boolean Analytics() throws InterruptedException {
        wait.until(ExpectedConditions.elementToBeClickable(AnalyticsLink));
        clickAnalyticsLink();
        wait.until(ExpectedConditions.visibilityOfElementLocated(AnalyticsHeading));
        return Objects.equals(driver.findElement(AnalyticsHeading).getText(), "Analytics");
    }

    // Logs the user out and checks for the logout confirmation message
    public boolean LogOut() throws InterruptedException {
        clickProfilePicture();
        wait.until(ExpectedConditions.elementToBeClickable(LogOutBtn));
        clickLogoutButton();
        wait.until(ExpectedConditions.elementToBeClickable(FinalLogOutBtn));
        clickFinalLogoutButton();
        wait.until(ExpectedConditions.visibilityOfElementLocated(LogOutMessage));
        return Objects.equals(driver.findElement(LogOutMessage).getText(), "You have successfully logged out.");
    }

}
