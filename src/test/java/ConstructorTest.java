
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pageobject.MainPage;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ConstructorTest extends BaseTest {

    @Test
    @DisplayName("Переход к разделу Соусы")
    public void testScrollToSauce() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickTabSauce();
        assertTrue(mainPage.isSauceSectionVisible());
    }

    @Test
    @DisplayName("Переход к разделу Начинки")
    public void testScrollToFilling() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickTabFilling();
        assertTrue(mainPage.isFillingSectionVisible());
    }

    @Test
    @DisplayName("Переход к разделу Булки")
    public void testScrollToBun() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickTabSauce();
        mainPage.clickTabBun();
        assertTrue(mainPage.isBunSectionVisible());
    }
}