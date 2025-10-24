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

public class AdminBeCourseCataloguePage {
    public WebDriver driver;
    public WebDriverWait wait;

    public AdminBeCourseCataloguePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    // XPath
    final By GuestLoginBtn = By.id("guestLoginLinkId");
    final By EmailField = By.id("username");
    final By PasswordField = By.id("password");
    final By SignInBtn = By.xpath("//input[@value='Login']");
    String userLink = "https://xsitestg.singaporetech.edu.sg/d2l/common/dialogs/quickLink/quickLink.d2l?ou=25291&type=lti&rcode=SITStg-39847&srcou=25291&contentTopicId=117036";
    final By UserListHeading = By.xpath("//h4[@class='mb-0']");
    final By EditDropdownListLink = By.xpath("//i[@class='fa-solid fa-pen-to-square']");
    final By EditDropdownListHeading = By.xpath("//h3[@class='mb-3']");
    final By CourseListLink = By.xpath("//i[@class='fa-solid fa-book']");
    final By CourseListHeading = By.xpath("//h4[@class='mb-0']");
    final By CompetencyRoadmapListLink = By.xpath("//i[@class='fa-solid fa-file-signature']");
    final By CompetencyRoadmapListHeading = By.xpath("//h4[@class='mb-0']");
    final By UserUsageAnalyticsLink = By.xpath("//i[@class='fa fa-truck']");
    final By UserUsageAnalyticsHeading = By.xpath("//h4[@class='mb-0']");
    final By SignOutBtn = By.xpath("//span[@class='title nav-text']");
    final By FinalSignOutBtn = By.xpath("//a[@role='button']");
    final By LogOutMessage = By.xpath("//p[text()='Successfully Logged out.']");

    @Step("Clicking on the Guest Login Button")
    public void clickGuestLoginButton() {
        wait.until(ExpectedConditions.elementToBeClickable(GuestLoginBtn));
        driver.findElement(GuestLoginBtn).click();
    }

    @Step("Entering Email: {email}")
    public void enterEmail(String email) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(EmailField));
        driver.findElement(EmailField).sendKeys(email);
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

    @Step("Navigating to Users Page")
    public void navigateToUsersPage() {
        driver.get(userLink);
    }

    @Step("Clicking on Edit Dropdown List Link")
    public void clickEditDropdownListLink() {
        wait.until(ExpectedConditions.elementToBeClickable(EditDropdownListLink));
        driver.findElement(EditDropdownListLink).click();
    }

    @Step("Clicking on Course List Link")
    public void clickCourseListLink() {
        wait.until(ExpectedConditions.elementToBeClickable(CourseListLink));
        driver.findElement(CourseListLink).click();
    }

    @Step("Clicking on Competency Roadmap List Link")
    public void clickCompetencyRoadmapListLink() {
        wait.until(ExpectedConditions.elementToBeClickable(CompetencyRoadmapListLink));
        driver.findElement(CompetencyRoadmapListLink).click();
    }

    @Step("Clicking on User Usage Analytics Link")
    public void clickUserUsageAnalyticsLink() {
        wait.until(ExpectedConditions.elementToBeClickable(UserUsageAnalyticsLink));
        driver.findElement(UserUsageAnalyticsLink).click();
    }

    @Step("Hovering and clicking on Sign Out button")
    public void hoverAndClickSignOut() {
        WebElement element = driver.findElement(SignOutBtn);
        Actions actions = new Actions(driver);
        actions.moveToElement(element).perform();
        element.click();
    }

    @Step("Clicking on Final Sign Out button")
    public void clickFinalSignOut() {
        wait.until(ExpectedConditions.elementToBeClickable(FinalSignOutBtn));
        driver.findElement(FinalSignOutBtn).click();
    }

    // Logs in to the application
    public boolean nativeLogin(String username, String password) throws InterruptedException {
        clickGuestLoginButton();
        enterEmail(username);
        enterPassword(password);
        clickSignInButton();
        wait.until(ExpectedConditions.urlToBe("https://xsitestg.singaporetech.edu.sg/d2l/home"));
        return Objects.equals(driver.getCurrentUrl(), "https://xsitestg.singaporetech.edu.sg/d2l/home");
    }

    // Navigates to the Users page and verifies the presence of the heading
    public boolean users() {
        navigateToUsersPage();
        wait.until(ExpectedConditions.elementToBeClickable(UserListHeading));
        return Objects.equals(driver.findElement(UserListHeading).getText(), "Users List");
    }

    // Navigates to the Edit Dropdown List page and verifies the heading
    public boolean EditDropdownList() throws InterruptedException {
        clickEditDropdownListLink();
        wait.until(ExpectedConditions.visibilityOfElementLocated(EditDropdownListHeading));
        return Objects.equals(driver.findElement(EditDropdownListHeading).getText(), "Edit Dropdown Lists");
    }

    // Navigates to the Course List page and verifies the heading
    public boolean CourseList() throws InterruptedException {
        clickCourseListLink();
        wait.until(ExpectedConditions.visibilityOfElementLocated(CourseListHeading));
        return Objects.equals(driver.findElement(CourseListHeading).getText(), "Course List");
    }

    // Navigates to the Competency Roadmap List page and verifies the heading
    public boolean CompetencyRoadmapList() throws InterruptedException {
        wait.until(ExpectedConditions.elementToBeClickable(CompetencyRoadmapListLink));
        clickCompetencyRoadmapListLink();
        wait.until(ExpectedConditions.visibilityOfElementLocated(CompetencyRoadmapListHeading));
        return Objects.equals(driver.findElement(CompetencyRoadmapListHeading).getText(), "Competency Roadmap List");
    }

    // Navigates to the User Usage Analytics page and verifies the heading
    public boolean UserUsageAnalytics() throws InterruptedException {
        wait.until(ExpectedConditions.elementToBeClickable(UserUsageAnalyticsLink));
        clickUserUsageAnalyticsLink();
        wait.until(ExpectedConditions.visibilityOfElementLocated(UserUsageAnalyticsHeading));
        return Objects.equals(driver.findElement(UserUsageAnalyticsHeading).getText(), "User Usage Analytics");
    }

    // Logs the user out of the application and verifies the logout message
    public boolean LogOut() throws InterruptedException {
        hoverAndClickSignOut();
        wait.until(ExpectedConditions.elementToBeClickable(FinalSignOutBtn));
        clickFinalSignOut();
        wait.until(ExpectedConditions.visibilityOfElementLocated(LogOutMessage));
        return Objects.equals(driver.findElement(LogOutMessage).getText(), "Successfully Logged out.");
    }
}
