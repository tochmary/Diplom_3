package helpers;

import io.qameta.allure.Step;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.ErrorLoggingFilter;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import model.User;

import static io.restassured.RestAssured.given;

public class ApiRequests {
    private final static RequestSpecification SPEC = new RequestSpecBuilder()
            .setBaseUri(Config.getHost())
            .addHeader("Content-type", "application/json")
            .setRelaxedHTTPSValidation()
            .addFilter(new RequestLoggingFilter())
            .addFilter(new ResponseLoggingFilter())
            .addFilter(new ErrorLoggingFilter())
            .build();

    @Step("Отправить POST запрос /api/auth/register")
    public static Response sendPostRequestCreateUser(User user) {
        return given()
                .spec(SPEC)
                .body(user)
                .post("/api/auth/register")
                .thenReturn();
    }

    @Step("Отправить POST запрос /api/auth/login")
    public static Response sendPostRequestLoginUser(User user) {
        return given()
                .spec(SPEC)
                .body(user)
                .post("/api/auth/login")
                .thenReturn();
    }

    @Step("Отправить DELETE запрос /api/auth/user")
    public static Response sendPostRequestDeleteUser(String accessToken) {
        return given()
                .spec(SPEC)
                .headers("Authorization", accessToken)
                .delete("/api/auth/user")
                .thenReturn();
    }
}
