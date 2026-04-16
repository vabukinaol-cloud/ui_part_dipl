
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import api.UserClient;
import models.User;
import pageobject.*;
import java.time.Duration;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("Тесты регистрации пользователя")
public class RegistrationTest extends BaseTest {

    private String token;
    private final UserClient client = new UserClient();

    @Test
    @DisplayName("Успешная регистрация")
    @Description("При вводе валидных данных происходит переход на страницу логина")
    public void successRegistration() {
        User user = User.getRandomUser();
        openRegistrationPage();
        new RegistrationPage(driver).register(user.getName(), user.getEmail(), user.getPassword());

        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.urlContains("/login"));
        assertTrue(driver.getCurrentUrl().contains("/login"), "После регистрации не произошёл переход на /login");
    }

    @Test
    @DisplayName("Ошибка регистрации при коротком пароле")
    @Description("При вводе пароля менее 6 символов отображается ошибка 'Некорректный пароль'")
    public void errorRegistrationShortPassword() {
        openRegistrationPage();
        RegistrationPage regPage = new RegistrationPage(driver);
        regPage.register("TestUser", "qa@test.ru", "12345");
        assertTrue(regPage.isPasswordErrorDisplayed(), "Сообщение об ошибке 'Некорректный пароль' не отображается");
    }

    @Step("Открыть страницу регистрации")
    private void openRegistrationPage() {
        new MainPage(driver).clickLogin();
        new LoginPage(driver).clickRegister();
    }

    @AfterEach
    @Step("Удаление тестового пользователя через API (если был создан)")
    public void clean() {
        if (token != null) {
            client.delete(token);
            token = null;
        }
    }
}
