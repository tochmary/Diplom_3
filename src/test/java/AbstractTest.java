import helpers.ApiSteps;
import helpers.Config;
import model.User;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;

abstract public class AbstractTest {
    protected WebDriver driver;
    protected static String hostTest;
    protected static final User USER_1 = new User("mary_test@yandex.ru", "marypass", "Мария");
    protected static String accessToken1;

    @BeforeAll
    public static void before() {
        //Создать тестового пользователя
        accessToken1 = ApiSteps.createUser(USER_1).getAccessToken();
        hostTest = Config.getHost();
    }

    @BeforeEach
    public void setUp() {
        driver = Config.getWebDriver();
        //Переход на страницу тестового приложения
        driver.get(hostTest);
    }

    @AfterEach
    public void teardown() {
        //Закрыть браузер
        driver.quit();
    }

    @AfterAll
    public static void after() {
        //Удалить тестового пользователя
        ApiSteps.deleteUser(accessToken1);
    }
}
