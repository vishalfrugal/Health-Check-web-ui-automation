package Pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Objects;

public class AIKnowledgeAssistantPage {
    public WebDriver driver;
    public WebDriverWait wait;

    public AIKnowledgeAssistantPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    // Locators for elements
    final By emailField = By.xpath("//input[@name=\"email\"]");
    final By passwordField = By.xpath("//input[@name=\"password\"]");
    final By checkbox1 = By.xpath("//iframe[contains(@title,'reCAPTCHA')]");
    final By checkbox = By.xpath("//span[@id=\"recaptcha-anchor\"]");
    final By loginButton = By.xpath("//button[text()='Login']");
    final By homePageHeading = By.xpath("//h3[text()=\"AI Assistant\"]");
    final By Heading2 = By.xpath("//label[text()=\"Repository Name\"]");
    final By acceptbtn = By.xpath("//button[text()=\"Accept\"]");
    final By clickRepository = By.xpath("//img[@id=\"accountImage\"]");
    final By analyticsLink = By.xpath("//span[text()=\" Analytics \"]");
    final By analyticsHeading = By.xpath("//a[text()=\"Analytics\"]");
    final By subscriptionButton = By.xpath("//button[@id=\"payment-button\"]");
    final By subscriptionPlansLink = By.xpath("//a[text()=' Subscription Plans']");
    final By subscriptionPlansHeading = By.xpath("//div[@class=\"page-detail\"]/a");
    final By billingHistoryLink = By.xpath("//a[@id=\"payment-billing\"]");
    final By billingHistoryHeading = By.xpath("(//div[@class=\"page-detail\"]/a)[2]");
    final By configureSettingsLink = By.xpath("//a[@id=\"ai-models\"]");
    final By configureSettingsHeading = By.xpath("//h2[text()=\"Configuration Settings\"]");
    final By profileImage = By.xpath("//img[@id='profile-image']");
    final By logoutButton = By.xpath("//a[text()=\"Logout\"]");
    final By confirmLogoutButton = By.xpath("//button[text()=\"Log Out\"]");

    // Step: Click on email input field
    @Step("Clicking on Email input field")
    public void clickEmailField() {
        wait.until(ExpectedConditions.elementToBeClickable(emailField));
        driver.findElement(emailField).click();
    }

    // Step: Type email into email input field
    @Step("Typing into Email field: {username}")
    public void typeEmail(String username) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(emailField));
        driver.findElement(emailField).sendKeys(username);
    }

    // Step: Click on password input field
    @Step("Clicking on Password input field")
    public void clickPasswordField() {
        wait.until(ExpectedConditions.elementToBeClickable(passwordField));
        driver.findElement(passwordField).click();
    }

    // Step: Type password into password input field
    @Step("Typing into Password field")
    public void typePassword(String password) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(passwordField));
        driver.findElement(passwordField).sendKeys(password);
    }

    // Step: Handle Robot verification checkbox (scroll + wait for manual verification)
    @Step("Clicking on Robot verification checkbox")
    public void clickRobotCheckBox() throws InterruptedException {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
//        Thread.sleep(30000);
    }

    // Step: Click on Login button
    @Step("Clicking on Login button")
    public void clickLoginButton() {
        wait.until(ExpectedConditions.elementToBeClickable(loginButton));
        driver.findElement(loginButton).click();
    }

    // Step: Click on Accept button
    @Step("Clicking on accept button")
    public void clickacceptButton() {
        wait.until(ExpectedConditions.elementToBeClickable(acceptbtn));
        driver.findElement(acceptbtn).click();
    }

    // Step: Click on the first Repository
    @Step("Clicking on firstRepository")
    public void clickfirstRepositoryButton() {
        wait.until(ExpectedConditions.elementToBeClickable(clickRepository));
        driver.findElement(clickRepository).click();
    }

    // Step: Click on Analytics link
    @Step("Clicking on Analytics link")
    public void clickAnalyticsLink() {
        wait.until(ExpectedConditions.elementToBeClickable(analyticsLink));
        driver.findElement(analyticsLink).click();
    }

    // Step: Click on Subscription button
    @Step("Clicking on Subscription button")
    public void clickSubscriptionButton() {
        wait.until(ExpectedConditions.elementToBeClickable(subscriptionButton));
        driver.findElement(subscriptionButton).click();
    }

    // Step: Click on Subscription Plans link
    @Step("Clicking on Subscription Plans link")
    public void clickSubscriptionPlansLink() {
        wait.until(ExpectedConditions.elementToBeClickable(subscriptionPlansLink));
        driver.findElement(subscriptionPlansLink).click();
    }

    // Step: Click on Billing History link
    @Step("Clicking on Billing History link")
    public void clickBillingHistoryLink() {
        wait.until(ExpectedConditions.elementToBeClickable(billingHistoryLink));
        driver.findElement(billingHistoryLink).click();
    }

    // Step: Click on Configure Settings link
    @Step("Clicking on Configure Settings link")
    public void clickConfigureSettingsLink() {
        wait.until(ExpectedConditions.elementToBeClickable(configureSettingsLink));
        driver.findElement(configureSettingsLink).click();
    }

    // Step: Click on Profile image (for logout)
    @Step("Clicking on Profile Image")
    public void clickProfileImage() {
        wait.until(ExpectedConditions.elementToBeClickable(profileImage));
        driver.findElement(profileImage).click();
    }

    // Step: Click on Logout button
    @Step("Clicking on Log Out button")
    public void clickLogoutButton() {
        wait.until(ExpectedConditions.elementToBeClickable(logoutButton));
        driver.findElement(logoutButton).click();
    }

    // Step: Click on Confirm Logout button
    @Step("Clicking on Confirm Log Out button")
    public void clickConfirmLogoutButton() {
        wait.until(ExpectedConditions.elementToBeClickable(confirmLogoutButton));
        driver.findElement(confirmLogoutButton).click();
    }

    // Function: Perform login flow and validate homepage
    public boolean login(String username, String password) throws InterruptedException {
        clickEmailField();
        typeEmail(username);
        clickPasswordField();
        typePassword(password);
        clickRobotCheckBox();
        clickLoginButton();
        Thread.sleep(10000);
        wait.until(ExpectedConditions.visibilityOfElementLocated(homePageHeading));
        return driver.findElement(homePageHeading).isDisplayed();
    }

    // Function: Navigate to Repository and validate Repository Name
    public boolean repositories() {
        clickacceptButton();
        clickfirstRepositoryButton();
        wait.until(ExpectedConditions.visibilityOfElementLocated(Heading2));
        String actualText = driver.findElement(Heading2).getText();
        return Objects.equals(actualText,"Repository Name");
    }

    // Function: Navigate to Analytics page and validate heading
    public boolean analyticsPage() {
        clickAnalyticsLink();
        wait.until(ExpectedConditions.visibilityOfElementLocated(analyticsHeading));
        String headingText = driver.findElement(analyticsHeading).getText();
        return Objects.equals(headingText, " Analytics ");
    }

    // Function: Navigate to Subscription Plans page and validate heading
    public boolean subscriptionPlansPage() {
        clickSubscriptionButton();
        clickSubscriptionPlansLink();
        wait.until(ExpectedConditions.visibilityOfElementLocated(subscriptionPlansHeading));
        String headingText = driver.findElement(subscriptionPlansHeading).getText();
        return Objects.equals(headingText, "Subscription");
    }

    // Function: Navigate to Billing History page and validate heading
    public boolean billingHistoryPage() {
        clickBillingHistoryLink();
        wait.until(ExpectedConditions.visibilityOfElementLocated(billingHistoryHeading));
        String headingText = driver.findElement(billingHistoryHeading).getText();
        return Objects.equals(headingText, "Billing History");
    }

    // Function: Navigate to Configure Settings page and validate heading
    public boolean verifyConfigureSettingsPage() {
        clickConfigureSettingsLink();
        wait.until(ExpectedConditions.visibilityOfElementLocated(configureSettingsHeading));
        String headingText = driver.findElement(configureSettingsHeading).getText();
        return Objects.equals(headingText, "CONFIGURATION SETTINGS");
    }

    // Function: Perform logout flow and validate login page is displayed again
    public boolean verifyLogoutFlow() {
        clickProfileImage();
        clickLogoutButton();
        clickConfirmLogoutButton();
        wait.until(ExpectedConditions.visibilityOfElementLocated(emailField));
        return driver.findElement(emailField).isDisplayed();
    }

}
