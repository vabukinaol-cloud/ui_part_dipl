package pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class MainPage {
    private final WebDriver driver;
    private final By loginButton = By.xpath("//button[text()='Войти в аккаунт']");
    private final By personalAccountButton = By.xpath("//p[text()='Личный Кабинет']");
    private final By orderButton = By.xpath("//button[text()='Оформить заказ']");
    private final By constructorHeader = By.xpath("//h1[text()='Соберите бургер']");

    // Вкладки
    private final By tabBun = By.xpath("//span[text()='Булки']/parent::div");
    private final By tabSauce = By.xpath("//span[text()='Соусы']/parent::div");
    private final By tabFilling = By.xpath("//span[text()='Начинки']/parent::div");

    // Заголовки разделов (для проверки скролла)
    private final By bunSection = By.xpath("//h2[text()='Булки']");
    private final By sauceSection = By.xpath("//h2[text()='Соусы']");
    private final By fillingSection = By.xpath("//h2[text()='Начинки']");

    public MainPage(WebDriver driver) { this.driver = driver; }

    public void clickLogin() { driver.findElement(loginButton).click(); }
    public void clickPersonalAccount() { driver.findElement(personalAccountButton).click(); }
    public boolean isOrderButtonVisible() { return driver.findElement(orderButton).isDisplayed(); }
    public boolean isConstructorHeaderVisible() { return driver.findElement(constructorHeader).isDisplayed(); }

    // Методы для конструктора
    public void clickTabBun() { driver.findElement(tabBun).click(); }
    public void clickTabSauce() { driver.findElement(tabSauce).click(); }
    public void clickTabFilling() { driver.findElement(tabFilling).click(); }

    public boolean isBunSectionVisible() { return driver.findElement(bunSection).isDisplayed(); }
    public boolean isSauceSectionVisible() { return driver.findElement(sauceSection).isDisplayed(); }
    public boolean isFillingSectionVisible() { return driver.findElement(fillingSection).isDisplayed(); }
}