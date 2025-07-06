import helpers.ApiSteps;
import model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.LoginPage;
import pages.MainPage;
import pages.RegisterPage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RegisterTest extends AbstractTest {
    MainPage objMainPage;
    LoginPage objLoginPage;
    RegisterPage objRegisterPage;
    protected static final User USER_1 = new User("mary_test@yandex.ru", "marypass", "Мария");
    protected String accessToken;

    @BeforeEach
    public void beforeEach() {
        objMainPage = new MainPage(driver);
        objLoginPage = new LoginPage(driver);
        objRegisterPage = new RegisterPage(driver);
        objMainPage.waitForLoadPage();

        //Вход на форму авторизации
        objMainPage.clickAccountButton();
        objLoginPage.waitForLoadPage();

        //Вход на форму регистрации
        objLoginPage.clickRegisterButton();
        objRegisterPage.waitForLoadPage();
    }

    @Test
    @DisplayName("Успешная регистрация")
    public void registerUser() {
        objRegisterPage.registerUser(USER_1.getName(),
                USER_1.getEmail(),
                USER_1.getPassword());
        objLoginPage.waitForLoadPage();

        //проверка авторизации через API
        accessToken = ApiSteps.loginUser(USER_1).getAccessToken();
    }

    @Test
    @DisplayName("Неуспешная регистрация с некорректным паролем")
    public void registerFailedUserWithWrongPassword() {
        objRegisterPage.registerUser(USER_1.getName(),
                USER_1.getEmail(),
                "mary");

        //проверка существования сообщения о некорректном пароле
        assertTrue(objRegisterPage.existPasswordMessage());
        objRegisterPage.waitForLoadPage();
        assertEquals(hostTest + "/register", driver.getCurrentUrl(),
                "Открыта не форма \"Регистрация\"!");
    }

    @AfterEach
    public void tearDown() {
        if (accessToken != null) ApiSteps.deleteUser(accessToken);
    }
}
