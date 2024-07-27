import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import java.util.concurrent.TimeUnit;
import java.time.Duration;
public class TestInitialization {
    WebDriver driver;

    @BeforeMethod
    public void openBrowser() {
        // Initialize the ChromeDriver
        driver = new ChromeDriver();
        // Maximize the browser window
        driver.manage().window().maximize();
        // Set an implicit wait
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); // Adjust the timeout as necessary
        // Navigate to the Sauce Demo login page
        driver.navigate().to("https://www.saucedemo.com/");
    }

    @AfterMethod
    public void quit() {
        if (driver != null) {
            WebDriverWait wait = new WebDriverWait(driver,  Duration.ofSeconds(10)); // 10 seconds wait time
            try {
                //wait for the URL to be the expected one before quitting
                wait.until(ExpectedConditions.urlToBe("https://www.saucedemo.com/"));
            } catch (Exception e) {
                // Handle the exception if needed
                System.out.println("Condition not met before quitting: " + e.getMessage());
            } finally {
                // Quit the browser
                driver.quit();
            }
        }
    }

}
