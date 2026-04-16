
import io.qameta.allure.Description;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import models.User;
import pageobject.*;
import java.time.Duration;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RegistrationTest extends BaseTest {

    @Test
    @DisplayName("Успешная регистрация")
    @Description("Проверка перехода на страницу логина при вводе валидных данных")
    public void successRegistration() {
        new MainPage(driver).clickLogin();
        new LoginPage(driver).clickRegister();
        ru.stellarburgers.pageobject.RegistrationPage regPage = new ru.stellarburgers.pageobject.RegistrationPage(driver);
        User user = User.getRandomUser();
        regPage.register(user.getName(), user.getEmail(), user.getPassword());

        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.urlContains("/login"));
        assertTrue(driver.getCurrentUrl().contains("/login"));
    }

    @Test
    @DisplayName("Ошибка регистрации: короткий пароль")
    @Description("Проверка появления ошибки 'Некорректный пароль' при вводе 5 символов")
    public void errorRegistrationShortPassword() {
        driver.get("https://stellarburgers.education-services.ru/register");
        new ru.stellarburgers.pageobject.RegistrationPage(driver).register("Daria", "qa@test.ru", "12345");
        assertTrue(new ru.stellarburgers.pageobject.RegistrationPage(driver).isPasswordErrorDisplayed());
    }
}