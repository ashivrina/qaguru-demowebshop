import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class DemowebshopTests {

    Faker faker = new Faker();

    @Test
    void subscribeToNewsletter() {
        given()
                .contentType("application/x-www-form-urlencoded; charset=UTF-8")
                .body("email=" + faker.internet().emailAddress())
                .when()
                .post("http://demowebshop.tricentis.com/subscribenewsletter")
                .then()
                .statusCode(200)
                .body("Success", is(true))
                .body("Result", is("Thank you for signing up! A verification email has been sent. We appreciate your interest."));
    }

    @Test
    void subscribeToNewsletterWithIncorrectEmail() {
        given()
                .contentType("application/x-www-form-urlencoded; charset=UTF-8")
                .body("email=" + faker.name())
                .when()
                .post("http://demowebshop.tricentis.com/subscribenewsletter")
                .then()
                .statusCode(200)
                .body("Success", is(false))
                .body("Result", is("Enter valid email"));
    }
}
