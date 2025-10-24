package Listener;

import Base.BaseClass;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class AllureScreenShot extends BaseClass implements ITestListener {

    public void onTestFailure(ITestResult result) {
        ScreenShotOnTestFailure(driver);
        saveTextLogOnTestFailure(result.getMethod().getConstructorOrMethod().getName());
    }

    public void onTestStart(ITestResult result) {
        FunctionName(result.getMethod().getConstructorOrMethod().getName());
    }


    @Attachment(value = "Page Screenshot", type = "image/png")
    public static byte[] ScreenShotOnTestFailure(WebDriver driver) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

    @Attachment(value = "stacktrace", type = "text/plain") //Not using currently as framework modified
    public static String saveTextLogOnTestFailure(String message) {
        System.out.println("Test Failed  : " + message);
        return message;
    }

    public void FunctionName(String FunctionName) {
        System.out.println("---------------" + FunctionName + "---------------");
    }
}