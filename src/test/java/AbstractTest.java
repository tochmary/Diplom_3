import helpers.Config;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;

abstract public class AbstractTest {
    protected WebDriver driver;
    protected static String hostTest;

    @BeforeAll
    public static void before() {
        hostTest = Config.getHost();
    }

    @BeforeEach
    public void setUp() {
        driver = Config.getWebDriver();
        //Переход на страницу тестового приложения
        driver.get(hostTest);
    }

    @AfterEach
    public void tearDown() {
        //Закрыть браузер
        driver.quit();
    }
}
