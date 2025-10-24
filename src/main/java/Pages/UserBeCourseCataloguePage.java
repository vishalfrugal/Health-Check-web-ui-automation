package Pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.Objects;

public class UserBeCourseCataloguePage {
    public WebDriver driver;
    public WebDriverWait wait;

    public UserBeCourseCataloguePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(60));
    }

    // XPath
    final By GuestLoginBtn = By.id("guestLoginLinkId");
    final By EmailField = By.id("username");
    final By PasswordField = By.id("password");
    final By SignInBtn = By.xpath("//input[@value='Login']");
    String HomeLink = "https://xsitestg.singaporetech.edu.sg/d2l/common/dialogs/quickLink/quickLink.d2l?ou=25291&type=lti&rcode=SITStg-39847&srcou=25291&contentTopicId=117036";
    final By HomeHeading = By.xpath("//h3[text()='Welcome to the Course Catalogue.']");
    final By CourseListLink = By.xpath("//a[@id='view-tasks']");
    final By CourseListHeading = By.xpath("//span[@id='page-title' and text()='All Courses:']");
    final By CompetencyRoadmapLink = By.xpath("//span[@class='title' and text()='Competency Roadmaps']\n");
    final By CompetencyRoadmapHeading = By.xpath("//span[@id='page-title']");
    final By SignOutBtn = By.xpath("(//span[@class=\"icon\"])[5]/i");
    final By FinalSignOutBtn = By.xpath("//button[@class='btn btn-secondary']/following-sibling::a");
    final By LogOutMessage = By.xpath("//p[text()='Successfully Logged out.']");
    final By Loader = By.xpath("//div[contains(@class,\"background-wrapper text-center loader\")]");

    @Step("Clicking on the Guest Login Button")
    public void clickGuestLoginButton(){
        wait.until(ExpectedConditions.elementToBeClickable(GuestLoginBtn));
        driver.findElement(GuestLoginBtn).click();
    }

    @Step("Entering Email")
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

    @Step("Navigating to Home Page")
    public void navigateToHomePage() {
        driver.get(HomeLink);
    }

    @Step("Clicking on Course List Link")
    public void clickCourseListLink() throws InterruptedException {
        wait.until(ExpectedConditions.elementToBeClickable(CourseListLink));
        driver.findElement(CourseListLink).click();
        Thread.sleep(2000);
    }

    @Step("Clicking on Competency Roadmap Link")
    public void clickCompetencyRoadmapLink() {
        wait.until(ExpectedConditions.elementToBeClickable(CompetencyRoadmapLink));
        driver.findElement(CompetencyRoadmapLink).click();
    }

    @Step("Clicking on Sign Out Button")
    public void clickSignOutButton() {
        wait.until(ExpectedConditions.elementToBeClickable(SignOutBtn));
        driver.findElement(SignOutBtn).click();
    }

    @Step("Clicking on Final Sign Out Button")
    public void clickFinalSignOutButton() {
        wait.until(ExpectedConditions.elementToBeClickable(FinalSignOutBtn));
        driver.findElement(FinalSignOutBtn).click();
    }

    // Logs in using guest login with provided credentials and verifies redirection to the home page
    public boolean nativeLogin(String username, String password) throws InterruptedException {
        clickGuestLoginButton();
        wait.until(ExpectedConditions.visibilityOfElementLocated(EmailField));
        enterEmail(username);
        enterPassword(password);
        clickSignInButton();
        wait.until(ExpectedConditions.urlToBe("https://xsitestg.singaporetech.edu.sg/d2l/home"));
        return Objects.equals(driver.getCurrentUrl(), "https://xsitestg.singaporetech.edu.sg/d2l/home");
    }

    // Navigates to the Home page and verifies the welcome heading is displayed
    public boolean Home() {
        navigateToHomePage();
        wait.until(ExpectedConditions.elementToBeClickable(HomeHeading));
        return Objects.equals(driver.findElement(HomeHeading).getText(), "Welcome to the Course Catalogue.");
    }

    // Navigates to the Course List page and verifies the heading is displayed
    public boolean CourseList() throws InterruptedException {
        wait.until(ExpectedConditions.elementToBeClickable(CourseListLink));
        clickCourseListLink();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(Loader));
        wait.until(ExpectedConditions.visibilityOfElementLocated(CourseListHeading));
        return Objects.equals(driver.findElement(CourseListHeading).getText(), "All Courses:");
    }

    // Navigates to the Competency Roadmap page and verifies the heading is displayed
    public boolean CompetencyRoadmap() throws InterruptedException {
        wait.until(ExpectedConditions.elementToBeClickable(CompetencyRoadmapLink));
        clickCompetencyRoadmapLink();
        wait.until(ExpectedConditions.visibilityOfElementLocated(CompetencyRoadmapHeading));
        return Objects.equals(driver.findElement(CompetencyRoadmapHeading).getText(), "All Competency Roadmaps:");
    }

    // Signs the user out and verifies the logout success message is displayed
    public boolean SignOut() throws InterruptedException {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(Loader));
        clickSignOutButton();
        clickFinalSignOutButton();
        wait.until(ExpectedConditions.visibilityOfElementLocated(LogOutMessage));
        return Objects.equals(driver.findElement(LogOutMessage).getText(), "Successfully Logged out.");
    }
}
