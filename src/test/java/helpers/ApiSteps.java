package helpers;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import model.Resp;
import model.RespUser;
import model.User;

import static org.junit.jupiter.api.Assertions.*;

public class ApiSteps {

    @Step("Регистрация пользователя")
    public static RespUser createUser(User user) {
        Response response = ApiRequests.sendPostRequestCreateUser(user);
        response.then().statusCode(200);
        RespUser respUser = response.body().as(RespUser.class);
        assertTrue(respUser.isSuccess());
        return respUser;
    }

    @Step("Авторизация пользователя")
    public static RespUser loginUser(User user) {
        Response response = ApiRequests.sendPostRequestLoginUser(user);
        response.then().statusCode(200);
        RespUser respUser = response.body().as(RespUser.class);
        assertTrue(respUser.isSuccess());
        return respUser;
    }

    @Step("Удаление пользователя")
    public static void deleteUser(String accessToken) {
        Response response = ApiRequests.sendPostRequestDeleteUser(accessToken);
        response.then().statusCode(202);
        Resp resp = response.body().as(Resp.class);
        assertAll("Проверка полей ответа",
                () -> assertTrue(resp.isSuccess()),
                () -> assertEquals("User successfully removed", resp.getMessage())
        );
    }
}
