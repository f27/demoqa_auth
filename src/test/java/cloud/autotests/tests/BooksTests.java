package cloud.autotests.tests;

import cloud.autotests.BaseTest;
import cloud.autotests.api.AccountApi;
import cloud.autotests.api.BooksApi;
import cloud.autotests.models.*;
import com.codeborne.selenide.Selenide;
import io.qameta.allure.AllureId;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Cookie;

import java.util.Collections;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static org.assertj.core.api.Assertions.assertThat;

public class BooksTests extends BaseTest {

    @Test
    @AllureId("25256")
    @DisplayName("Добавление книги в профиль")
    void addBookToProfileTest() {
        TokenModel tokenModel = AccountApi.generateToken(CREDENTIALS);
        assertThat(tokenModel.getStatus()).isEqualTo("Success");
        LoginResultModel loginResultModel = AccountApi.login(CREDENTIALS);
        BooksApi booksApi = new BooksApi(loginResultModel.getToken(), loginResultModel.getUserId());
        booksApi.deleteAllBooks();

        booksApi.addListOfBooks(ListOfBooksWithUserIdModel.builder()
                .userId(loginResultModel.getUserId())
                .collectionOfIsbns(Collections.singletonList(new IsbnModel("9781593277574")))
                .build());
        open("/favicon.png");
        getWebDriver().manage().addCookie(new Cookie("userName", loginResultModel.getUsername()));
        getWebDriver().manage().addCookie(new Cookie("userID", loginResultModel.getUserId()));
        getWebDriver().manage().addCookie(new Cookie("token", loginResultModel.getToken()));
        getWebDriver().manage().addCookie(new Cookie("expires", loginResultModel.getExpires()));
        open("/profile");

        $(".rt-noData").shouldNotBe(visible);
        $("[id='see-book-Understanding ECMAScript 6']").shouldBe(visible);
    }

    @Test
    @AllureId("25257")
    @DisplayName("Удаление книги из профиля")
    void deleteBookFromProfileTest() {
        TokenModel tokenModel = AccountApi.generateToken(CREDENTIALS);
        assertThat(tokenModel.getStatus()).isEqualTo("Success");
        LoginResultModel loginResultModel = AccountApi.login(CREDENTIALS);
        BooksApi booksApi = new BooksApi(loginResultModel.getToken(), loginResultModel.getUserId());
        booksApi.deleteAllBooks();
        booksApi.addListOfBooks(ListOfBooksWithUserIdModel.builder()
                .userId(loginResultModel.getUserId())
                .collectionOfIsbns(Collections.singletonList(new IsbnModel("9781593277574")))
                .build());
        open("/favicon.png");
        getWebDriver().manage().addCookie(new Cookie("userName", loginResultModel.getUsername()));
        getWebDriver().manage().addCookie(new Cookie("userID", loginResultModel.getUserId()));
        getWebDriver().manage().addCookie(new Cookie("token", loginResultModel.getToken()));
        getWebDriver().manage().addCookie(new Cookie("expires", loginResultModel.getExpires()));
        open("/profile");
        $(".rt-noData").shouldNotBe(visible);
        $("[id='see-book-Understanding ECMAScript 6']").shouldBe(visible);

        BookWithUserIdModel bookWithUserIdModel = BookWithUserIdModel.builder()
                .userId(loginResultModel.getUserId())
                .isbn("9781593277574")
                .build();
        booksApi.deleteBook(bookWithUserIdModel);
        Selenide.refresh();

        $(".rt-noData").shouldBe(visible);
        $("[id='see-book-Understanding ECMAScript 6']").shouldNotBe(visible);
    }
}
