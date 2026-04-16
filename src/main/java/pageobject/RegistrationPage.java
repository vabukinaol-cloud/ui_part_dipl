package ru.stellarburgers.pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegistrationPage {
    private final WebDriver driver;

    private final By nameInput = By.xpath("(//input[@name='name'])[1]");
    private final By emailInput = By.xpath("(//input[@name='name'])[2]");
    private final By passwordInput = By.xpath("//input[@type='password']");
    private final By registerButton = By.xpath("//button[text()='Зарегистрироваться']");
    private final By loginLink = By.xpath("//a[text()='Войти']");
    private final By passwordError = By.xpath("//p[text()='Некорректный пароль']");

    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Нажать на ссылку 'Войти'")
    public void clickLogin() {
        driver.findElement(loginLink).click();
    }

    @Step("Зарегистрировать пользователя: {name}, {email}")
    public void register(String name, String email, String password) {
        driver.findElement(nameInput).sendKeys(name);
        driver.findElement(emailInput).sendKeys(email);
        driver.findElement(passwordInput).sendKeys(password);
        driver.findElement(registerButton).click();
    }

    public boolean isPasswordErrorDisplayed() {
        return driver.findElement(passwordError).isDisplayed();
    }
}