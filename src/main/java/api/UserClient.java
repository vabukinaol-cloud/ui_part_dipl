package api;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import models.User;
import static io.restassured.RestAssured.given;

public class UserClient {
    private static final String BASE_URL = "https://stellarburgers.education-services.ru/api/";

    @Step("Создать пользователя через API")
    public ValidatableResponse create(User user) {
        return given().header("Content-Type", "application/json").body(user)
                .post(BASE_URL + "auth/register").then();
    }

    @Step("Удалить пользователя через API")
    public void delete(String token) {
        if (token != null) {
            given().header("Authorization", token).delete(BASE_URL + "auth/user");
        }
    }
}