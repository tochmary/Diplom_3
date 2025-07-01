import helpers.ApiSteps;
import helpers.URL;
import io.qameta.allure.Step;
import model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.LoginPage;
import pages.MainPage;
import pages.RegisterPage;

import static org.junit.jupiter.api.Assertions.*;

public class RegisterTest extends AbstractTest {
    MainPage objMainPage;
    LoginPage objLoginPage;
    RegisterPage objRegisterPage;
    private static final User USER_2 = new User(
            "dary_test@yandex.ru", "darypass", "Дарья");
    String accessToken2;

    @BeforeEach
    public void beforeEach() {
        objMainPage = new MainPage(driver);
        objRegisterPage = new RegisterPage(driver);
        objLoginPage = new LoginPage(driver);

        //Вход на форму авторизации
        objMainPage.clickAccountButton();
        checkGoToLoginPage();

        //Вход на форму регистрации
        objLoginPage.clickRegisterButton();
        checkGoToRegisterPage();
    }

    @Test
    @DisplayName("Успешная регистрация")
    public void registerUser() {
        objRegisterPage.setName(USER_2.getName());
        objRegisterPage.setEmail(USER_2.getEmail());
        objRegisterPage.setPassword(USER_2.getPassword());
        objRegisterPage.clickRegisterButton();
        checkGoToLoginPage();

        //проверка авторизации через API
        accessToken2 = ApiSteps.loginUser(USER_2).getAccessToken();
    }

    @Test
    @DisplayName("Неуспешная регистрация с некорректным паролем")
    public void registerFailedUserWithWrongPassword() {
        objRegisterPage.setName(USER_2.getName());
        objRegisterPage.setName(USER_2.getEmail());
        objRegisterPage.setName("dary");
        objRegisterPage.clickRegisterButton();

        //проверка существования сообщения о некорректном пароле
        objRegisterPage.existPasswordMessage();
        checkGoToRegisterPage();
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

    @Step("Проверка перехода на главную страницу")
    private void checkGoToMainPage() {
        assertAll("Проверка страницы",
                () -> assertEquals(URL.getHost() + "/", driver.getCurrentUrl(),
                        "Открылась не главная страница \"Stellar Burgers\""),
                () -> assertTrue(objMainPage.isHeaderSection())
        );
    }


    @AfterEach
    public void tearDown() {
        if (accessToken2!=null) ApiSteps.deleteUser(accessToken2);
    }
}
