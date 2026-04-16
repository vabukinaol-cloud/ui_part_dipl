package pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    private final WebDriver driver;
    private final By emailField = By.xpath("//input[@name='name' and @type='text']");
    private final By passwordField = By.cssSelector("input[name='Пароль']");
    private final By loginButton = By.xpath("//button[text()='Войти']");
    private final By registerLink = By.xpath("//a[text()='Зарегистрироваться']");
    private final By forgotPasswordLink = By.xpath("//a[text()='Восстановить пароль']");

    public LoginPage(WebDriver driver) { this.driver = driver; }

    @Step("Вход в аккаунт")
    public void login(String email, String password) {
        driver.findElement(emailField).sendKeys(email);
        driver.findElement(passwordField).sendKeys(password);
        driver.findElement(loginButton).click();
    }

    public void clickRegister() { driver.findElement(registerLink).click(); }
    public void clickForgotPassword() { driver.findElement(forgotPasswordLink).click(); }
}
