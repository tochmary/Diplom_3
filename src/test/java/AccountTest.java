import helpers.ApiSteps;
import model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AccountTest extends AbstractTest {
    protected static final User USER_1 = new User("mary_test@yandex.ru", "marypass", "Мария");
    protected String accessToken;
    MainPage objMainPage;
    LoginPage objLoginPage;
    AccountPage objAccountPage;

    @BeforeEach
    public void beforeEach() {
        //Создать тестового пользователя
        accessToken = ApiSteps.createUser(USER_1).getAccessToken();

        objMainPage = new MainPage(driver);
        objLoginPage = new LoginPage(driver);
        objAccountPage = new AccountPage(driver);
        objMainPage.waitForLoadPage();

        //При нажатии на кнопку "Личный Кабинет" ведет на форму авторизации
        objMainPage.clickAccountButton();
        objLoginPage.waitForLoadPage();
        //Авторизация ведет на главную страницу
        objLoginPage.loginUser(USER_1.getEmail(), USER_1.getPassword());
        objMainPage.waitForLoadPage();
        //При нажатии на кнопку "Личный Кабинет" ведет в профиль
        objMainPage.clickAccountButton();
        objAccountPage.waitForLoadPage();
    }

    @Test
    @DisplayName("Нажатие на логотип \"Stellar Burgers\"")
    public void checkClickLogoStellarBurgers() {
        objAccountPage.clickLogoStellarBurgers();

        objMainPage.waitForLoadPage();
        assertEquals(hostTest + "/", driver.getCurrentUrl(),
                "Открыта не главная страница \"Stellar Burgers\"!");
    }

    @Test
    @DisplayName("Нажатие на кнопку \"Конструктор\"")
    public void checkClickConstructorButton() {
        objAccountPage.clickConstructorButton();

        objMainPage.waitForLoadPage();
        assertEquals(hostTest + "/", driver.getCurrentUrl(),
                "Открыта не главная страница \"Stellar Burgers\"!");
    }

    @Test
    @DisplayName("Нажатие на кнопку \"Выйти\"")
    public void checkClickExitButton() {
        objAccountPage.clickExitButton();
        objLoginPage.waitForLoadPage();
        //При нажатии на кнопку "Личный Кабинет" ведет на форму авторизации
        objMainPage.clickAccountButton();

        objLoginPage.waitForLoadPage();
        assertEquals(hostTest + "/login", driver.getCurrentUrl(),
                "Открыта не форма авторизации \"Вход\"!");
    }

    @AfterEach
    public void afterEach() {
        //Удалить тестового пользователя
        ApiSteps.deleteUser(accessToken);
    }
}
