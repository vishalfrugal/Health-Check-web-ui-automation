// java
package Utility;

import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.util.logging.Level;
import java.util.logging.Logger;

public class utils {

    private final WebDriver driver;
    private static final Logger log = Logger.getLogger(utils.class.getName());

    public utils(WebDriver driver) {
        this.driver = driver;
    }

    @Attachment(value = "Screenshot: {name}", type = "image/png")
    public byte[] TakeScreenshotOnSoftAssertion(String name) {
        if (driver == null) {
            log.log(Level.WARNING, "WebDriver is null; cannot take screenshot");
            return new byte[0];
        }
        if (!(driver instanceof TakesScreenshot)) {
            log.log(Level.WARNING, "WebDriver does not implement TakesScreenshot; cannot take screenshot");
            return new byte[0];
        }

        try {
            return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        } catch (Exception e) {
            log.log(Level.SEVERE, "Failed to capture screenshot", e);
            return new byte[0];
        }
    }
}
