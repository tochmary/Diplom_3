import helpers.ApiSteps;
import io.qameta.allure.Step;
import model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.*;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoginTest extends AbstractTest {
    protected static final User USER_1 = new User("mary_test@yandex.ru", "marypass", "Мария");
    protected String accessToken1;
    MainPage objMainPage;
    LoginPage objLoginPage;
    AccountPage objAccountPage;
    RegisterPage objRegisterPage;
    RecoverPage objRecoverPage;

    @BeforeEach
    public void beforeEach() {
        //Создать тестового пользователя
        accessToken1 = ApiSteps.createUser(USER_1).getAccessToken();

        objMainPage = new MainPage(driver);
        objLoginPage = new LoginPage(driver);
        objAccountPage = new AccountPage(driver);
        objRegisterPage = new RegisterPage(driver);
        objRecoverPage = new RecoverPage(driver);
        objMainPage.waitForLoadPage();
    }

    @Test
    @DisplayName("Вход по кнопке \"Войти в аккаунт\"")
    public void loginForClickLoginButton() {
        objMainPage.clickLoginButton();
        objLoginPage.waitForLoadPage();

        objLoginPage.loginUser(USER_1.getEmail(), USER_1.getPassword());
        objMainPage.waitForLoadPage();
        checkLogin();
    }

    @Test
    @DisplayName("Вход через кнопку \"Личный кабинет\"")
    public void loginForClickAccountButton() {
        objMainPage.clickAccountButton();
        objLoginPage.waitForLoadPage();

        objLoginPage.loginUser(USER_1.getEmail(), USER_1.getPassword());
        objMainPage.waitForLoadPage();
        checkLogin();
    }

    @Test
    @DisplayName("Вход через кнопку на форме \"Регистрация\"")
    public void loginForRegisterPage() {
        objMainPage.clickLoginButton();
        objLoginPage.waitForLoadPage();
        objLoginPage.clickRegisterButton();
        objRegisterPage.waitForLoadPage();
        objRegisterPage.clickLoginButton();
        objLoginPage.waitForLoadPage();

        objLoginPage.loginUser(USER_1.getEmail(), USER_1.getPassword());
        objMainPage.waitForLoadPage();
        checkLogin();
    }

    @Test
    @DisplayName("Вход через кнопку на форме \"Восстановление пароля\"")
    public void loginForRecoverPage() {
        objMainPage.clickLoginButton();
        objLoginPage.waitForLoadPage();
        objLoginPage.clickRecoverButton();
        objRecoverPage.waitForLoadPage();
        objRecoverPage.clickLoginButton();
        objLoginPage.waitForLoadPage();

        objLoginPage.loginUser(USER_1.getEmail(), USER_1.getPassword());
        objMainPage.waitForLoadPage();
        checkLogin();
    }

    @Test
    @DisplayName("Нажатие на кнопку \"Личный Кабинет\" авторизованного пользователя")
    public void checkClickAccountButton() {
        //Первый клик ведет на форму авторизации
        objMainPage.clickAccountButton();
        objLoginPage.waitForLoadPage();
        //Авторизация
        objLoginPage.loginUser(USER_1.getEmail(), USER_1.getPassword());
        objMainPage.waitForLoadPage();
        //второй клик ведет в профиль
        checkLogin();
    }

    @Step("Проверка авторизации")
    private void checkLogin() {
        //При нажатии на кнопку "Личный Кабинет" ведет в профиль
        objMainPage.clickAccountButton();
        objAccountPage.waitForLoadPage();
        //В профиле данные должны совпадать с веденными на форме авторизации
        assertAll("Проверка полей профиля",
                () -> assertEquals(USER_1.getName(), objAccountPage.getNameField(),
                        "Неверное значение поля name!"),
                () -> assertEquals(USER_1.getEmail(), objAccountPage.getEmailField(),
                        "Неверное значение поля email!")
        );
    }

    @AfterEach
    public void afterEach() {
        //Удалить тестового пользователя
        ApiSteps.deleteUser(accessToken1);
    }
}
