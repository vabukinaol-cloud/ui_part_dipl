
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Step;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.io.IOException;
import java.time.Duration;

public class BaseTest {
    private static final String YANDEX_BINARY = "/Applications/Yandex.app/Contents/MacOS/Yandex";
    private static final String YANDEX_PLIST  = "/Applications/Yandex.app/Contents/Info.plist";
    private static final String BASE_URL = "https://stellarburgers.education-services.ru/";

    protected WebDriver driver;

    @BeforeEach
    @Step("Открытие браузера и главной страницы")
    public void setup() {
        String browser = System.getProperty("browser", "chrome");
        if ("yandex".equals(browser)) {
            // Яндекс Браузер основан на Chromium; его версию Chromium храним в plist
            // (ключ CFBundleChromiumShortVersionString, напр. "144.0.7559.236").
            // WDM скачает ChromeDriver именно под эту версию Chromium.
            String chromiumVersion = readYandexChromiumMajorVersion();
            WebDriverManager wdm = WebDriverManager.chromedriver();
            if (chromiumVersion != null) {
                wdm.browserVersion(chromiumVersion);
            }
            wdm.setup();
            ChromeOptions options = new ChromeOptions();
            options.setBinary(YANDEX_BINARY);
            driver = new ChromeDriver(options);
        } else {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get(BASE_URL);
    }

    @AfterEach
    @Step("Закрытие браузера")
    public void tearDown() {
        if (driver != null) driver.quit();
    }

    /**
     * Читает версию Chromium из plist Яндекс Браузера (CFBundleChromiumShortVersionString).
     * Возвращает мажорную версию (напр. "144") или null при ошибке.
     */
    private String readYandexChromiumMajorVersion() {
        try {
            ProcessBuilder pb = new ProcessBuilder(
                    "defaults", "read", YANDEX_PLIST, "CFBundleChromiumShortVersionString"
            );
            pb.redirectErrorStream(true);
            Process p = pb.start();
            String version = new String(p.getInputStream().readAllBytes()).trim();
            if (!version.isEmpty()) {
                return version.split("\\.")[0]; // "144.0.7559.236" -> "144"
            }
        } catch (IOException ignored) {
        }
        return null;
    }
}
