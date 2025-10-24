package Base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class BaseClass {
    public static WebDriver driver;

    public void SetUp(String URL) {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(URL);
    }
}
