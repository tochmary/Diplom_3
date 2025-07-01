import helpers.ApiSteps;
import helpers.Browser;
import helpers.URL;
import model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;

import java.time.format.DateTimeFormatter;

abstract public class AbstractTest {
    protected WebDriver driver;
    public static DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    protected static final User USER_1 = new User(
            "mary_test@yandex.ru", "marypass", "Мария");
    String accessToken1;

    @BeforeEach
    public void setUp() {
        //Создать тестового пользователя
        accessToken1 = ApiSteps.createUser(USER_1).getAccessToken();
        //драйвер для браузера Chrome
        driver = new Browser().getWebDriver("chrome");
        // переход на страницу тестового приложения
        driver.get(URL.getHost());
    }

    @AfterEach
    public void teardown() {
        // Закрыть браузер
        driver.quit();
        //Удалить тестового пользователя
        ApiSteps.deleteUser(accessToken1);
    }
}
