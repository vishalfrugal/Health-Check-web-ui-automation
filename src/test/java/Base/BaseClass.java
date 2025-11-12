package Base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class BaseClass {
    public static WebDriver driver;

    public void SetUp(String URL) {
        ChromeOptions options = new ChromeOptions();

        // Detect if running inside GitHub Actions or Linux CI
        String osName = System.getProperty("os.name").toLowerCase();
        String ciEnv = System.getenv("GITHUB_ACTIONS"); // non-null when running in CI


        if (ciEnv != null || osName.contains("linux")) {
            // ✅ CI / GitHub Actions setup
            options.addArguments("--headless=new");
            options.addArguments("--window-size=1920,1080");
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
            options.addArguments("--disable-gpu");
            options.addArguments("--disable-extensions");
            options.addArguments("--disable-infobars");
            options.addArguments("--remote-debugging-port=9222");
            options.addArguments("--user-data-dir=/tmp/chrome-profile-" + System.currentTimeMillis());
        } else {
            // ✅ Local (Windows / macOS) setup
            options.addArguments("--start-maximized");
        }

        try {
            driver = new ChromeDriver(options);
            driver.get(URL);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("❌ Failed to start Chrome session: " + e.getMessage());
        }
    }

    public void TearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
