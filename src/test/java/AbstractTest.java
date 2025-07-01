import helpers.ApiRequests;
import helpers.ApiSteps;
import helpers.Browser;
import helpers.URL;
import io.restassured.response.Response;
import model.RespUser;
import model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;

abstract public class AbstractTest {
    protected WebDriver driver;
    protected static final User USER_1 = new User(
            "mary_test@yandex.ru", "marypass", "Мария");
    String accessToken1;

    @BeforeEach
    public void setUp() {
        clearData();
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

    //TODO убрать
    private void clearData(User user) {
        Response response = ApiRequests.sendPostRequestLoginUser(user);
        RespUser respUser = response.body().as(RespUser.class);
        if (respUser.isSuccess()) {
            ApiSteps.deleteUser(respUser.getAccessToken());
        }
    }

    private void clearData() {
        clearData(new User("mary_test@yandex.ru", "marypass", null));
        clearData(new User("dary_test@yandex.ru", "darypass", null));
    }
}
