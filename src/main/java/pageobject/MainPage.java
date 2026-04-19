package pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MainPage {
    private final WebDriver driver;
    private final By loginButton = By.xpath("//button[text()='Войти в аккаунт']");
    private final By personalAccountButton = By.xpath("//p[text()='Личный Кабинет']");
    private final By orderButton = By.xpath("//button[text()='Оформить заказ']");
    private final By constructorHeader = By.xpath("//h1[text()='Соберите бургер']");

    // Вкладки конструктора
    private final By tabBun = By.xpath("//span[text()='Булки']/parent::div");
    private final By tabSauce = By.xpath("//span[text()='Соусы']/parent::div");
    private final By tabFilling = By.xpath("//span[text()='Начинки']/parent::div");

    // Заголовки разделов
    private final By bunSection = By.xpath("//h2[text()='Булки']");
    private final By sauceSection = By.xpath("//h2[text()='Соусы']");
    private final By fillingSection = By.xpath("//h2[text()='Начинки']");

    public MainPage(WebDriver driver) { this.driver = driver; }

    @Step("Нажать кнопку 'Войти в аккаунт'")
    public void clickLogin() { driver.findElement(loginButton).click(); }

    @Step("Нажать кнопку 'Личный Кабинет'")
    public void clickPersonalAccount() { driver.findElement(personalAccountButton).click(); }

    @Step("Проверить видимость кнопки 'Оформить заказ'")
    public boolean isOrderButtonVisible() { return driver.findElement(orderButton).isDisplayed(); }

    @Step("Проверить видимость заголовка 'Соберите бургер'")
    public boolean isConstructorHeaderVisible() { return driver.findElement(constructorHeader).isDisplayed(); }

    @Step("Перейти к разделу 'Булки'")
    public void clickTabBun() { driver.findElement(tabBun).click(); }

    @Step("Перейти к разделу 'Соусы'")
    public void clickTabSauce() { driver.findElement(tabSauce).click(); }

    @Step("Перейти к разделу 'Начинки'")
    public void clickTabFilling() { driver.findElement(tabFilling).click(); }

    @Step("Проверить видимость раздела 'Булки'")
    public boolean isBunSectionVisible() { return driver.findElement(bunSection).isDisplayed(); }

    @Step("Проверить видимость раздела 'Соусы'")
    public boolean isSauceSectionVisible() { return driver.findElement(sauceSection).isDisplayed(); }

    @Step("Проверить видимость раздела 'Начинки'")
    public boolean isFillingSectionVisible() { return driver.findElement(fillingSection).isDisplayed(); }
}
