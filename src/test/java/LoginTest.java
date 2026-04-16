import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import api.UserClient;
import models.User;
import pageobject.*;

import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("Тесты авторизации пользователя")
public class LoginTest extends BaseTest {

    private User user;
    private String token;
    private UserClient client;
    private MainPage mainPage;
    private LoginPage loginPage;

    @BeforeEach
    @Step("Подготовка данных: создание пользователя через API")
    public void prepare() {
        client = new UserClient();
        user = User.getRandomUser();
        token = client.create(user).extract().path("accessToken");

        mainPage = new MainPage(driver);
        loginPage = new LoginPage(driver);
    }

    @Test
    @DisplayName("Вход по кнопке «Войти в аккаунт» на главной")
    @Description("Проверка авторизации через кнопку на главной странице")
    public void loginViaMainPageButton() {
        mainPage.clickLogin();
        loginPage.login(user.getEmail(), user.getPassword());

        assertTrue(mainPage.isOrderButtonVisible(), "Кнопка 'Оформить заказ' не появилась, вход не выполнен");
    }

    @Test
    @DisplayName("Вход через кнопку «Личный кабинет»")
    @Description("Проверка авторизации при переходе через шапку сайта")
    public void loginViaPersonalAccountButton() {
        mainPage.clickPersonalAccount();
        loginPage.login(user.getEmail(), user.getPassword());

        assertTrue(mainPage.isOrderButtonVisible(), "Вход через личный кабинет не удался");
    }

    @Test
    @DisplayName("Вход через кнопку в форме регистрации")
    @Description("Нажать 'Войти' на странице регистрации /register")
    public void loginViaRegistrationForm() {
        mainPage.clickLogin();
        loginPage.clickRegister(); // Переход на страницу регистрации

        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.clickLogin();

        loginPage.login(user.getEmail(), user.getPassword());
        assertTrue(mainPage.isOrderButtonVisible(), "Вход через форму регистрации не удался");
    }

    @Test
    @DisplayName("Вход через кнопку в форме восстановления пароля")
    @Description("Нажать 'Войти' на странице восстановления пароля /forgot-password")
    public void loginViaForgotPasswordPage() {
        mainPage.clickLogin();
        loginPage.clickForgotPassword(); // Переход на /forgot-password

        ForgotPasswordPage forgotPage = new ForgotPasswordPage(driver);
        forgotPage.clickLoginLink(); // Клик по ссылке "Войти"

        loginPage.login(user.getEmail(), user.getPassword());
        assertTrue(mainPage.isOrderButtonVisible(), "Вход через страницу восстановления пароля не удался");
    }

    @AfterEach
    @Step("Удаление пользователя через API")
    public void clean() {
        if (token != null) {
            client.delete(token);
        }
    }
}