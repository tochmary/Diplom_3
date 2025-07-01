import helpers.URL;
import io.qameta.allure.Step;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertAll;

public class LoginTest extends AbstractTest {

    MainPage objMainPage;
    LoginPage objLoginPage;
    AccountPage objAccountPage;
    RegisterPage objRegisterPage;
    RecoverPage objRecoverPage;

    @BeforeEach
    public void beforeEach() {
        objMainPage = new MainPage(driver);
        objLoginPage = new LoginPage(driver);
        objAccountPage = new AccountPage(driver);
        objRegisterPage = new RegisterPage(driver);
        objRecoverPage = new RecoverPage(driver);
    }

    @Test
    @DisplayName("Нажатие на логотип \"Stellar Burgers\"")
    public void checkClickLogoStellarBurgers() {
        objMainPage.clickAccountButton();
        checkGoToLoginPage();
        login();
        checkLogin();

        objAccountPage.clickLogoStellarBurgers();
        checkGoToMainPage();
    }

    @Test
    @DisplayName("Нажатие на кнопку \"Конструктор\"")
    public void checkClickConstructorButton() {
        objMainPage.clickAccountButton();
        checkGoToLoginPage();
        login();
        checkLogin();

        objAccountPage.clickConstructorButton();
        checkGoToMainPage();
    }

    @Test
    @DisplayName("Нажатие на кнопку \"Личный Кабинет\" авторизованного пользователя")
    public void checkClickAccountButton() {
        //первый клик ведет на форму авторизации
        objMainPage.clickAccountButton();
        checkGoToLoginPage();
        login();
        //второй клик в профиль
        checkLogin();
    }

    @Test
    @DisplayName("Нажатие на кнопку \"Выйти\"")
    public void checkClickRecoverButton() {
        objMainPage.clickLoginButton();
        checkGoToLoginPage();
        login();
        checkLogin();

        objAccountPage.clickExitButton();
        checkGoToLoginPage();
    }

    @Test
    @DisplayName("Вход по кнопке \"Войти в аккаунт\"")
    public void loginForClickLoginButton() {
        objMainPage.clickLoginButton();
        checkGoToLoginPage();
        login();
        checkLogin();
    }

    @Test
    @DisplayName("Вход через кнопку \"Личный кабинет\"")
    public void loginForClickAccountButton() {
        objMainPage.clickAccountButton();
        checkGoToLoginPage();
        login();
        checkLogin();
    }

    @Test
    @DisplayName("Вход через кнопку на форме \"Регистрация\"")
    public void loginForRegisterPage() {
        objMainPage.clickLoginButton();
        checkGoToLoginPage();
        objLoginPage.clickRegisterButton();
        checkGoToRegisterPage();
        objRegisterPage.clickLoginButton();
        checkGoToLoginPage();

        login();
        checkLogin();
    }

    @Test
    @DisplayName("Вход через кнопку на форме \"Восстановление пароля\"")
    public void loginForRecoverPage() {
        objMainPage.clickLoginButton();
        checkGoToLoginPage();
        objLoginPage.clickRecoverButton();
        checkGoToRecoverPage();
        objRecoverPage.clickLoginButton();
        checkGoToLoginPage();

        login();
        checkLogin();
    }

    @Step("Авторизация")
    private void login() {
        objLoginPage.setEmail(USER_1.getEmail());
        objLoginPage.setPassword(USER_1.getPassword());
        objLoginPage.clickLoginButton();
        checkGoToMainPage();
    }

    @Step("Проверка авторизации")
    private void checkLogin() {
        //Нажатие на кнопку "Личный Кабинет" ведет в профиль
        objMainPage.clickAccountButton();
        checkGoToAccountPage();

        //TODO Проверить поля?
    }

    @Step("Проверка перехода на главную страницу")
    private void checkGoToMainPage() {
        assertAll("Проверка страницы",
                () -> assertEquals(URL.getHost() + "/", driver.getCurrentUrl(),
                        "Открылась не главная страница \"Stellar Burgers\""),
                () -> assertTrue(objMainPage.isHeaderSection())
        );
    }

    @Step("Проверка перехода на форму регистрации")
    private void checkGoToRegisterPage() {
        assertAll("Проверка страницы",
                () -> assertEquals(URL.getHost() + "/register", driver.getCurrentUrl(),
                        "Открылась не форма \"Регистрация\"!"),
                () -> assertTrue(objRegisterPage.isRegisterHeader())
        );
    }

    @Step("Проверка перехода на форму авторизации")
    private void checkGoToLoginPage() {
        assertAll("Проверка страницы",
                () -> assertEquals(URL.getHost() + "/login", driver.getCurrentUrl(),
                        "Открылась не форма авторизации \"Вход\"!"),
                () -> assertTrue(objLoginPage.isLoginHeader())
        );
    }
    
    @Step("Проверка перехода на форму восстановления пароля")
    private void checkGoToRecoverPage() {
        assertAll("Проверка страницы",
                () -> assertEquals(URL.getHost() + "/forgot-password", driver.getCurrentUrl(),
                        "Открылась не форма \"Восстановление пароля\"!"),
                () -> assertTrue(objRecoverPage.isRecoverHeader())
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
