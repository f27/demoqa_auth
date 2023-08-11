package cloud.autotests.tests;

import cloud.autotests.BaseTest;
import cloud.autotests.api.AccountApi;
import cloud.autotests.models.CredentialsModel;
import cloud.autotests.models.LoginResultModel;
import cloud.autotests.models.TokenModel;
import com.codeborne.selenide.Configuration;
import io.qameta.allure.AllureId;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Cookie;

import static com.codeborne.selenide.Condition.exactOwnText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static org.assertj.core.api.Assertions.assertThat;

public class AuthTest extends BaseTest {

    @Test
    @AllureId("25255")
    @DisplayName("Успешная авторизация")
    void authTest() {
        TokenModel tokenModel = AccountApi.generateToken(CREDENTIALS);
        assertThat(tokenModel.getStatus()).isEqualTo("Success");
        LoginResultModel loginResultModel = AccountApi.login(CREDENTIALS);
        open("/favicon.png");
        getWebDriver().manage().addCookie(new Cookie("userName", loginResultModel.getUsername()));
        getWebDriver().manage().addCookie(new Cookie("userID", loginResultModel.getUserId()));
        getWebDriver().manage().addCookie(new Cookie("token", loginResultModel.getToken()));
        getWebDriver().manage().addCookie(new Cookie("expires", loginResultModel.getExpires()));

        open("/profile");

        $("#userName-value").shouldHave(exactOwnText(CREDENTIALS.getUserName()));
    }
}
