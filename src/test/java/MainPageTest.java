import helpers.URL;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.LoginPage;
import pages.MainPage;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MainPageTest extends AbstractTest {

    MainPage objMainPage;
    LoginPage objLoginPage;

    @BeforeEach
    public void beforeEach() {
        objMainPage = new MainPage(driver);
        objLoginPage = new LoginPage(driver);
        objMainPage.waitForLoadPage();
    }

    @Test
    @DisplayName("Нажатие на кнопку \"Личный Кабинет\" неавторизованного пользователя")
    public void checkClickAccountButtonWithoutAuth() {
        objMainPage.clickAccountButton();
        objLoginPage.waitForLoadPage();
        assertEquals(URL.getHost() + "/login", driver.getCurrentUrl(),
                "Открыта не форма авторизации \"Вход\"!");
    }

    @Test
    @DisplayName("Нажатие на раздел \"Булки\"")
    public void checkClickConstructorButton() {
        objMainPage.clickSauceSection();
        objMainPage.clickBunSection();
        //TODO Проверь, что работают переходы к разделу
    }

    @Test
    @DisplayName("Нажатие на раздел \"Соус\"")
    public void checkClickSauceSection() {
        objMainPage.clickSauceSection();
        //TODO Проверь, что работают переходы к разделу
    }

    @Test
    @DisplayName("Нажатие на раздел \"Начинки\"")
    public void checkClickFillingSection() {
        objMainPage.clickFillingSection();
        //TODO Проверь, что работают переходы к разделу
    }
}
