
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import api.UserClient;
import models.User;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageobject.*;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class NavigationTest extends BaseTest {
    private User user;
    private String token;
    private UserClient client = new UserClient();

    @BeforeEach
    public void login() {
        user = User.getRandomUser();
        token = client.create(user).extract().path("accessToken");
        new MainPage(driver).clickLogin();
        new LoginPage(driver).login(user.getEmail(), user.getPassword());
    }

    @Test
    @DisplayName("Переход в личный кабинет")
    public void testGoToProfile() {
        new MainPage(driver).clickPersonalAccount();
        assertTrue(new ProfilePage(driver).isProfileVisible());
    }

    @Test
    @DisplayName("Переход из личного кабинета в конструктор")
    public void testProfileToConstructor() {
        new MainPage(driver).clickPersonalAccount();
        new ProfilePage(driver).clickConstructor();
        assertTrue(new MainPage(driver).isConstructorHeaderVisible());
    }

    @Test
    @DisplayName("Переход из личного кабинета на логотип Stellar Burgers")
    public void testLogoFromProfile() {
        new MainPage(driver).clickPersonalAccount();
        new ProfilePage(driver).clickLogo();
        assertTrue(new MainPage(driver).isConstructorHeaderVisible());
    }

    @Test
    @DisplayName("Выход из аккаунта")
    public void testLogout() {
        new MainPage(driver).clickPersonalAccount();
        ProfilePage profilePage = new ProfilePage(driver);
        profilePage.clickLogout();
        boolean isRedirected = new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.urlContains("/login"));

        assertTrue(isRedirected, "После выхода не произошел переход на страницу логина. Текущий URL: " + driver.getCurrentUrl());
    }

    @AfterEach
    public void clean() { client.delete(token); }
}