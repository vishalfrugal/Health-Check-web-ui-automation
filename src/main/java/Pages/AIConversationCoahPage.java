package Pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Objects;

public class AIConversationCoahPage {
    public WebDriver driver;
    public WebDriverWait wait;

    // Constructor to initialize WebDriver and WebDriverWait
    public AIConversationCoahPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    // Locators for different elements on the page
    final By emailField = By.xpath("//input[@id='validationCustom05 email']");
    final By passwordField = By.xpath("//input[@id='validationCustom05 password']");
    final By loginButton = By.xpath("//button[@id='loginapi']");
    final By homePageHeading = By.xpath("//h3[text()=\"AI Coach\"]");
    final By Heading2 = By.xpath("//label[text()=\"Repository Name\"]");
    final By acceptbtn = By.xpath("//button[text()=\"Accept\"]");
    final By clickRepository = By.xpath("(//div[@class=\"card position-relative\"])[1]");
    final By backtoHome = By.xpath("//a[text()=\"Repository\"]");
    final By paymentButton = By.xpath("//button[@id='payment-button']");
    final By subscriptionPlansLink = By.xpath("//a[@id='subscription']");
    final By subscriptionPlansHeading = By.xpath("//a[normalize-space()='Payment']");
    final By billingHistoryLink = By.xpath("//a[@id=\"payment-billing\"]");
    final By billingHistoryHeading = By.xpath("//a[normalize-space()='Billing History']");
    final By profileImage = By.xpath("//img[@id='profile-image']");
    final By logoutButton = By.xpath("//a[text()=\"Logout\"]");
    final By confirmLogoutButton = By.xpath("//button[text()=\"Log Out\"]");

    // Click on email input field
    @Step("Clicking on Email input field")
    public void clickEmailField() {
        wait.until(ExpectedConditions.elementToBeClickable(emailField));
        driver.findElement(emailField).click();
    }

    // Type email into the input field
    @Step("Typing into Email field: {username}")
    public void typeEmail(String username) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(emailField));
        driver.findElement(emailField).sendKeys(username);
    }

    // Click on password input field
    @Step("Clicking on Password input field")
    public void clickPasswordField() {
        wait.until(ExpectedConditions.elementToBeClickable(passwordField));
        driver.findElement(passwordField).click();
    }

    // Type password into the input field
    @Step("Typing into Password field")
    public void typePassword(String password) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(passwordField));
        driver.findElement(passwordField).sendKeys(password);
    }

    // Handle robot verification (manual wait for captcha/checkbox)
    @Step("Clicking on Robot verification checkbox")
    public void clickRobotCheckBox() throws InterruptedException {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
        Thread.sleep(30000); // Waiting manually for verification
    }

    // Click on login button
    @Step("Clicking on Login button")
    public void clickLoginButton() {
        wait.until(ExpectedConditions.elementToBeClickable(loginButton));
        driver.findElement(loginButton).click();
    }

    // Click accept button after login
    @Step("Clicking on accept button")
    public void clickacceptButton() {
        wait.until(ExpectedConditions.elementToBeClickable(acceptbtn));
        driver.findElement(acceptbtn).click();
    }

    // Click on the first repository card
    @Step("Clicking on firstRepository")
    public void clickfirstRepositoryButton() {
        wait.until(ExpectedConditions.elementToBeClickable(clickRepository));
        driver.findElement(clickRepository).click();
    }

    // Navigate back to home (repository page)
    @Step("Clicking to go back to Home Page")
    public void clickBackToHome(){
        wait.until(ExpectedConditions.elementToBeClickable(backtoHome));
        driver.findElement(backtoHome).click();
    }

    // Click on subscription/payment button
    @Step("Clicking on Subscription button")
    public void clickPaymentButton() {
        wait.until(ExpectedConditions.elementToBeClickable(paymentButton));
        driver.findElement(paymentButton).click();
    }

    // Click on subscription plans link
    @Step("Clicking on Subscription Plans link")
    public void clickSubscriptionPlansLink() {
        wait.until(ExpectedConditions.elementToBeClickable(subscriptionPlansLink));
        driver.findElement(subscriptionPlansLink).click();
    }

    // Click on billing history link
    @Step("Clicking on Billing History link")
    public void clickBillingHistoryLink() {
        wait.until(ExpectedConditions.elementToBeClickable(billingHistoryLink));
        driver.findElement(billingHistoryLink).click();
    }

    // Click on profile image (to open profile menu)
    @Step("Clicking on Profile Image")
    public void clickProfileImage() {
        wait.until(ExpectedConditions.elementToBeClickable(profileImage));
        driver.findElement(profileImage).click();
    }

    // Click on logout button
    @Step("Clicking on Log Out button")
    public void clickLogoutButton() {
        wait.until(ExpectedConditions.elementToBeClickable(logoutButton));
        driver.findElement(logoutButton).click();
    }

    // Click on confirm logout button
    @Step("Clicking on Confirm Log Out button")
    public void clickConfirmLogoutButton() {
        wait.until(ExpectedConditions.elementToBeClickable(confirmLogoutButton));
        driver.findElement(confirmLogoutButton).click();
    }

    // Complete login flow and verify landing on home page
    public boolean login(String username, String password) throws InterruptedException {
        clickEmailField();
        typeEmail(username);
        clickPasswordField();
        typePassword(password);
        clickRobotCheckBox();
        clickLoginButton();
        Thread.sleep(2000);
        wait.until(ExpectedConditions.visibilityOfElementLocated(homePageHeading));
        return driver.findElement(homePageHeading).isDisplayed();
    }

    // Navigate to repositories, validate heading, and go back to home page
    public boolean repositories() {
        clickacceptButton();
        clickfirstRepositoryButton();
        wait.until(ExpectedConditions.visibilityOfElementLocated(Heading2));
        String actualText = driver.findElement(Heading2).getText();
        if(!actualText.equalsIgnoreCase("Repository Name")){
            return false;
        }
        clickBackToHome();
        return driver.findElement(homePageHeading).isDisplayed();
    }

    // Navigate to subscription plans page and validate heading
    public boolean subscriptionPlansPage() {
        clickPaymentButton();
        clickSubscriptionPlansLink();
        wait.until(ExpectedConditions.visibilityOfElementLocated(subscriptionPlansHeading));
        String headingText = driver.findElement(subscriptionPlansHeading).getText();
        return Objects.equals(headingText, "Payment");
    }

    // Navigate to billing history page and validate heading
    public boolean billingHistoryPage() {
        clickBillingHistoryLink();
        wait.until(ExpectedConditions.visibilityOfElementLocated(billingHistoryHeading));
        String headingText = driver.findElement(billingHistoryHeading).getText();
        return Objects.equals(headingText, "Billing History");
    }

    // Perform logout flow and verify that login screen is displayed again
    public boolean verifyLogoutFlow() {
        clickProfileImage();
        clickLogoutButton();
        clickConfirmLogoutButton();
        wait.until(ExpectedConditions.visibilityOfElementLocated(emailField));
        return driver.findElement(emailField).isDisplayed();
    }
}
