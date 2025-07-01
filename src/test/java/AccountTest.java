import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.*;

public class AccountTest extends AbstractTest {

    MainPage objMainPage;
    LoginPage objLoginPage;
    AccountPage objAccountPage;
    RegisterPage objRegisterPage;

    @BeforeEach
    public void beforeEach() {
        objMainPage = new MainPage(driver);
        objLoginPage = new LoginPage(driver);
        objAccountPage = new AccountPage(driver);
        objRegisterPage = new RegisterPage(driver);
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

        objMainPage.checkIsPage();
    }

    @Test
    @DisplayName("Нажатие на кнопку \"Конструктор\"")
    public void checkClickConstructorButton() {
        objAccountPage.clickConstructorButton();

        objMainPage.checkIsPage();
    }

    @Test
    @DisplayName("Нажатие на кнопку \"Выйти\"")
    public void checkClickExitButton() {
        objAccountPage.clickExitButton();
        objLoginPage.waitForLoadPage();
        //При нажатии на кнопку "Личный Кабинет" ведет на форму авторизации
        objMainPage.clickAccountButton();

        objLoginPage.checkIsPage();
    }

}
