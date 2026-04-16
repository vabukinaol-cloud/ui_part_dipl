
import io.qameta.allure.Description;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pageobject.MainPage;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("Тесты раздела Конструктор")
public class ConstructorTest extends BaseTest {

    @Test
    @DisplayName("Переход к разделу «Соусы»")
    @Description("По клику на вкладку 'Соусы' отображается соответствующий раздел")
    public void testScrollToSauce() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickTabSauce();
        assertTrue(mainPage.isSauceSectionVisible(), "Раздел 'Соусы' не отображается");
    }

    @Test
    @DisplayName("Переход к разделу «Начинки»")
    @Description("По клику на вкладку 'Начинки' отображается соответствующий раздел")
    public void testScrollToFilling() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickTabFilling();
        assertTrue(mainPage.isFillingSectionVisible(), "Раздел 'Начинки' не отображается");
    }

    @Test
    @DisplayName("Переход к разделу «Булки»")
    @Description("По клику на вкладку 'Булки' отображается соответствующий раздел")
    public void testScrollToBun() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickTabSauce();
        mainPage.clickTabBun();
        assertTrue(mainPage.isBunSectionVisible(), "Раздел 'Булки' не отображается");
    }
}
