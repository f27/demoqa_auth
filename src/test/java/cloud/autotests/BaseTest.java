package cloud.autotests;

import cloud.autotests.models.CredentialsModel;
import com.codeborne.selenide.Configuration;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

public class BaseTest {
    private static final String USERNAME = "test123456";
    private static final String PASSWORD = "Test123456@";
    protected static final CredentialsModel CREDENTIALS = new CredentialsModel(USERNAME, PASSWORD);

    @BeforeAll
    static void setup() {
        Configuration.pageLoadStrategy = "eager";
        Configuration.baseUrl = "https://demoqa.com";
        RestAssured.baseURI = "https://demoqa.com";
    }
}
