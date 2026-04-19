
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import api.UserClient;
import models.User;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageobject.*;
import java.time.Duration;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("Тесты навигации по сайту")
public class NavigationTest extends BaseTest {

    private User user;
    private String token;
    private final UserClient client = new UserClient();

    @BeforeEach
    @Step("Создать пользователя через API и выполнить вход")
    public void loginAsUser() {
        user = User.getRandomUser();
        token = client.create(user).extract().path("accessToken");
        new MainPage(driver).clickLogin();
        new LoginPage(driver).login(user.getEmail(), user.getPassword());
    }

    @Test
    @DisplayName("Переход в личный кабинет")
    @Description("По клику на 'Личный Кабинет' открывается страница профиля")
    public void testGoToProfile() {
        new MainPage(driver).clickPersonalAccount();
        assertTrue(new ProfilePage(driver).isProfileVisible(), "Страница профиля не открылась");
    }

    @Test
    @DisplayName("Переход из личного кабинета в конструктор по ссылке 'Конструктор'")
    @Description("По клику на 'Конструктор' происходит переход на главную страницу")
    public void testProfileToConstructor() {
        new MainPage(driver).clickPersonalAccount();
        new ProfilePage(driver).clickConstructor();
        assertTrue(new MainPage(driver).isConstructorHeaderVisible(), "Конструктор не открылся");
    }

    @Test
    @DisplayName("Переход из личного кабинета на главную по логотипу")
    @Description("По клику на логотип Stellar Burgers происходит переход на главную страницу")
    public void testLogoFromProfile() {
        new MainPage(driver).clickPersonalAccount();
        new ProfilePage(driver).clickLogo();
        assertTrue(new MainPage(driver).isConstructorHeaderVisible(), "Главная страница не открылась");
    }

    @Test
    @DisplayName("Выход из аккаунта")
    @Description("По клику 'Выйти' в личном кабинете происходит переход на страницу логина")
    public void testLogout() {
        new MainPage(driver).clickPersonalAccount();
        ProfilePage profilePage = new ProfilePage(driver);
        profilePage.clickLogout();
        boolean redirectedToLogin = new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.urlContains("/login"));
        assertTrue(redirectedToLogin, "После выхода не произошёл переход на /login. URL: " + driver.getCurrentUrl());
    }

    @AfterEach
    @Step("Удаление пользователя через API")
    public void clean() {
        if (token != null) client.delete(token);
    }
}
