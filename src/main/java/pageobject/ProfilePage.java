package pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class ProfilePage {
    private final WebDriver driver;

    // Локатор кнопки Выход
    private final By logoutButton = By.xpath("//button[text()='Выход']");
    // Локатор кнопки Конструктор
    private final By constructorLink = By.xpath("//p[text()='Конструктор']");
    // Локатор Логотипа (исправлен на более надежный)
    private final By logo = By.xpath("//div[contains(@class,'AppHeader_header__logo')]");
    // Локатор ссылки Профиль
    private final By profileLink = By.xpath("//a[contains(@href, '/account/profile')]");

    public ProfilePage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Нажать 'Выход'")
    public void clickLogout() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(logoutButton)).click();
    }

    @Step("Нажать на ссылку 'Конструктор'")
    public void clickConstructor() {
        driver.findElement(constructorLink).click();
    }

    @Step("Нажать на Логотип")
    public void clickLogo() {
        driver.findElement(logo).click();
    }

    @Step("Проверка видимости ссылки 'Профиль'")
    public boolean isProfileVisible() {
        return new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(profileLink)).isDisplayed();
    }
}