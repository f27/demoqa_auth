package cloud.autotests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Cookie;

import static com.codeborne.selenide.Condition.exactOwnText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

public class AuthTest {

    private GenerateTokenResponse generateToken(AuthRequest creds) {
        return given()
                .when()
                .accept("application/json")
                .header("Content-Type", "application/json; charset=utf-8")
                .body(creds)
                .log().all()
                .post("https://demoqa.com/Account/v1/GenerateToken")
                .then()
                .log().all()
                .assertThat().statusCode(200)
                .extract().as(GenerateTokenResponse.class);
    }

    private LoginResponse login(AuthRequest creds) {
        return given()
                .when()
                .accept("application/json")
                .header("Content-Type", "application/json; charset=utf-8")
                .body(creds)
                .log().all()
                .post("https://demoqa.com/Account/v1/Login")
                .then()
                .log().all()
                .assertThat().statusCode(200)
                .extract().as(LoginResponse.class);
    }

    @Test
    void authTest() {
        Configuration.pageLoadStrategy = "eager";
        AuthRequest creds = new AuthRequest("test123456", "Test123456@!");
        GenerateTokenResponse generateTokenResponse = generateToken(creds);
        assertThat(generateTokenResponse.getStatus()).isEqualTo("Success");
        LoginResponse loginData = login(creds);
        open("https://demoqa.com/favicon.png");
        getWebDriver().manage().addCookie(new Cookie("userName", loginData.getUsername()));
        getWebDriver().manage().addCookie(new Cookie("userID", loginData.getUserId()));
        getWebDriver().manage().addCookie(new Cookie("token", loginData.getToken()));
        getWebDriver().manage().addCookie(new Cookie("expires", loginData.getExpires()));
        open("https://demoqa.com/profile");
        $("#userName-value").shouldHave(exactOwnText(creds.getUserName()));
    }
}
