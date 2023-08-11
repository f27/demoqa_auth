package cloud.autotests.api;

import cloud.autotests.models.CredentialsModel;
import cloud.autotests.models.LoginResultModel;
import cloud.autotests.models.TokenModel;

import static io.restassured.RestAssured.given;

public class AccountApi {
    public static TokenModel generateToken(CredentialsModel credentialsModel) {
        return given()
                .when()
                .accept("application/json")
                .header("Content-Type", "application/json; charset=utf-8")
                .body(credentialsModel)
                .post("/Account/v1/GenerateToken")
                .then()
                .assertThat().statusCode(200)
                .extract().as(TokenModel.class);
    }

    public static LoginResultModel login(CredentialsModel credentialsModel) {
        return given()
                .when()
                .accept("application/json")
                .header("Content-Type", "application/json; charset=utf-8")
                .body(credentialsModel)
                .post("/Account/v1/Login")
                .then()
                .assertThat().statusCode(200)
                .extract().as(LoginResultModel.class);
    }
}
