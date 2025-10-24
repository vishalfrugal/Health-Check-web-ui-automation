package Pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.Objects;

public class UserThinkingGymPage {
    public WebDriver driver;
    public WebDriverWait wait;

    public UserThinkingGymPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    // XPath
    final By GuestLoginBtn = By.id("guestLoginLinkId");
    final By EmailField = By.id("username");
    final By PasswordField = By.id("password");
    final By SignInBtn = By.xpath("//input[@value='Login']");
    String HomeLink = "https://xsitestg.singaporetech.edu.sg/d2l/common/dialogs/quickLink/quickLink.d2l?ou=25291&type=lti&rcode=SITStg-39352&srcou=25291&contentTopicId=116811";
    final By HomeHeading = By.xpath("//h3[text()='Welcome to the Thinking Gym']");
    final By TaskListLink = By.xpath("//a[@id='view-tasks']");
    final By TaskListHeading = By.xpath("//span[text()='Your Tasks:']");
    final By FeedBackTaskLink = By.xpath("//i[@class='fab fa-rocketchat']");
    final By FeedBackTaskHeading = By.xpath("//span[normalize-space()='Feedback Tasks:']");
    final By ChatBotLink = By.xpath("//i[@class='fa-solid fa-robot']");
    final By ChatBotHeading = By.xpath("//span[normalize-space()='Start Practice with AI Chatbot']");
    final By LogOutBtn = By.xpath("//span[@class='icon']//i[@class='fas fa-sign-out-alt']");
    final By FinalLogOutBtn = By.xpath("//button[@class='btn btn-secondary']/following-sibling::a");
    final By LogOutMessage = By.xpath("//h4[text()='Successfully Logged out!']");

    @Step("Clicking on Guest Login Button")
    public void clickGuestLoginButton() {
        wait.until(ExpectedConditions.elementToBeClickable(GuestLoginBtn)).click();
    }

    @Step("Entering Username: {username}")
    public void enterUsername(String username) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(EmailField)).sendKeys(username);
    }

    @Step("Entering Password")
    public void enterPassword(String password) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(PasswordField)).sendKeys(password);
    }

    @Step("Clicking on Sign In Button")
    public void clickSignInButton() {
        wait.until(ExpectedConditions.elementToBeClickable(SignInBtn)).click();
    }

    @Step("Navigating to Home Page")
    public void navigateToHomePage() {
        driver.get(HomeLink);
    }

    @Step("Clicking on Task List Link")
    public void clickTaskListLink() {
        wait.until(ExpectedConditions.elementToBeClickable(TaskListLink)).click();
    }

    @Step("Clicking on Feedback Task Link")
    public void clickFeedBackTaskLink() {
        wait.until(ExpectedConditions.elementToBeClickable(FeedBackTaskLink)).click();
    }

    @Step("Clicking on ChatBot Link")
    public void clickChatBotLink() {
        wait.until(ExpectedConditions.elementToBeClickable(ChatBotLink)).click();
    }

    @Step("Clicking on Log Out Button")
    public void clickLogOutButton() {
        wait.until(ExpectedConditions.elementToBeClickable(LogOutBtn)).click();
    }

    @Step("Clicking on Final Log Out Button")
    public void clickFinalLogOutButton() {
        wait.until(ExpectedConditions.elementToBeClickable(FinalLogOutBtn)).click();
    }

    // Logs in using guest credentials and verifies successful navigation to the home page
    public boolean nativeLogin(String username, String password) {
        clickGuestLoginButton();
        enterUsername(username);
        enterPassword(password);
        clickSignInButton();
        wait.until(ExpectedConditions.urlToBe("https://xsitestg.singaporetech.edu.sg/d2l/home"));
        return Objects.equals(driver.getCurrentUrl(), "https://xsitestg.singaporetech.edu.sg/d2l/home");
    }

    // Navigates to the Home page and verifies the Thinking Gym welcome heading
    public boolean Home() {
        navigateToHomePage();
        wait.until(ExpectedConditions.visibilityOfElementLocated(HomeHeading));
        return Objects.equals(driver.findElement(HomeHeading).getText(), "Welcome to the Thinking Gym");
    }

    // Navigates to the Task List page and verifies the heading is displayed
    public boolean TaskList() {
        clickTaskListLink();
        wait.until(ExpectedConditions.visibilityOfElementLocated(TaskListHeading));
        return Objects.equals(driver.findElement(TaskListHeading).getText(), "Your Tasks:");
    }

    // Navigates to the Feedback Task page and verifies the heading is displayed
    public boolean FeedBackTask() {
        clickFeedBackTaskLink();
        wait.until(ExpectedConditions.visibilityOfElementLocated(FeedBackTaskHeading));
        return Objects.equals(driver.findElement(FeedBackTaskHeading).getText(), "Feedback Tasks:");
    }

    // Navigates to the ChatBot page and verifies the heading is displayed
    public boolean ChatBot() {
        clickChatBotLink();
        wait.until(ExpectedConditions.visibilityOfElementLocated(ChatBotHeading));
        return Objects.equals(driver.findElement(ChatBotHeading).getText(), "Start Practice with AI Chatbot");
    }

    // Logs the user out and verifies the logout confirmation message
    public boolean LogOut() {
        clickLogOutButton();
        clickFinalLogOutButton();
        wait.until(ExpectedConditions.visibilityOfElementLocated(LogOutMessage));
        return Objects.equals(driver.findElement(LogOutMessage).getText(), "Successfully Logged out!");
    }
}
