import helpers.URL;
import io.qameta.allure.Step;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.AccountPage;
import pages.LoginPage;
import pages.MainPage;

import static org.junit.jupiter.api.Assertions.*;

public class MainPageTest extends AbstractTest {

    MainPage objMainPage;
    LoginPage objLoginPage;
    AccountPage objAccountPage;

    @BeforeEach
    public void beforeEach() {
        objMainPage = new MainPage(driver);
        objLoginPage = new LoginPage(driver);
        objAccountPage = new AccountPage(driver);
    }

    @Test
    @DisplayName("Нажатие на кнопку \"Личный Кабинет\" неавторизованного пользователя")
    public void checkClickAccountButtonWithoutAuth() {
        objMainPage.clickAccountButton();
        checkGoToLoginPage();
    }

    @Test
    @DisplayName("Нажатие на раздел \"Булки\"")
    public void checkClickConstructorButton() {
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

    @Step("Проверка перехода на форму авторизации")
    private void checkGoToLoginPage() {
        assertAll("Проверка страницы",
                () -> assertEquals(URL.getHost() + "/login", driver.getCurrentUrl(),
                        "Открылась не форма авторизации \"Вход\"!"),
                () -> assertTrue(objLoginPage.isLoginHeader())
        );
    }

    @Step("Проверка перехода на страницу \"Личный Кабинет\"")
    private void checkGoToAccountPage() {
        assertAll("Проверка страницы",
                () -> assertEquals(URL.getHost()+"/account/profile", driver.getCurrentUrl(),
                        "Открылась не страница \"Личный Кабинет\"!"),
                () -> assertTrue(objAccountPage.isProfileSection())
        );
    }

}
