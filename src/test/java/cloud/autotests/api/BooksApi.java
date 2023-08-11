package cloud.autotests.api;

import cloud.autotests.models.BookWithUserIdModel;
import cloud.autotests.models.ListOfBooksWithUserIdModel;
import cloud.autotests.models.BooksModel;
import lombok.AllArgsConstructor;

import static io.restassured.RestAssured.given;

@AllArgsConstructor
public class BooksApi {
    private String token;
    private String userId;

    public void deleteAllBooks() {
        given()
                .when()
                .accept("application/json")
                .header("Content-Type", "application/json; charset=utf-8")
                .header("Authorization", "Bearer " + token)
                .queryParam("UserId", userId)
                .delete("/BookStore/v1/Books")
                .then()
                .assertThat().statusCode(204);
    }

    public BooksModel addListOfBooks(ListOfBooksWithUserIdModel listOfBooksWithUserIdModel) {
        return given()
                .when()
                .accept("application/json")
                .header("Content-Type", "application/json; charset=utf-8")
                .header("Authorization", "Bearer " + token)
                .body(listOfBooksWithUserIdModel)
                .post("/BookStore/v1/Books")
                .then()
                .assertThat().statusCode(201)
                .extract().as(BooksModel.class);
    }

    public void deleteBook(BookWithUserIdModel bookWithUserIdModel) {
        given()
                .when()
                .accept("application/json")
                .header("Content-Type", "application/json; charset=utf-8")
                .header("Authorization", "Bearer " + token)
                .body(bookWithUserIdModel)
                .delete("/BookStore/v1/Book")
                .then()
                .assertThat().statusCode(204);
    }
}
