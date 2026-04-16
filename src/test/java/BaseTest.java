
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.time.Duration;

public class BaseTest {
    protected WebDriver driver;

    @BeforeEach
    public void setup() {
        String browser = System.getProperty("browser", "chrome");
        if ("yandex".equals(browser)) {
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            options.setBinary("/Applications/Yandex.app/Contents/MacOS/Yandex");
            driver = new ChromeDriver(options);
        } else {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://stellarburgers.education-services.ru/");
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) driver.quit();
    }
}
