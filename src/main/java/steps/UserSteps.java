package steps;

import io.qameta.allure.Step;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.response.Response;
import model.User;

import static config.Endpoints.*;
import static config.URL.BASE_URL;
import static io.restassured.RestAssured.given;

public class UserSteps {
    @Step("Creating user")
    public static Response createUser(String email, String password, String name){
        User user = new User(email, password, name);
        return given()
                .header("content-type", "application/json")
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .body(user)
                .when()
                .post(BASE_URL + CREATE_USER);

    }
    @Step("Login user")
    public static Response loginUser(String email, String password){
    User user = new User(email, password);
    return given()
            .header("content-type", "application/json")
            .filter(new RequestLoggingFilter())
            .filter(new ResponseLoggingFilter())
            .body(user)
            .when()
            .post(BASE_URL + AUTH_USER);
    }
    @Step("Editing user data using auth token")
    public static Response editUserAuth(String email, String password, String name, String token){
        User user = new User (email, password, name);
       return given()
                .header("authorization", token)
                .header("content-type", "application/json")
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .body(user)
                .when()
                .patch(BASE_URL + USER_INFO);
    }
    @Step("Editing user data without auth token")
    public static Response editUserNoAuth(String email, String password, String name){
        User user = new User(email, password, name);
        return given()
                .header("content-type", "application/json")
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .body(user)
                .when()
                .patch(BASE_URL + USER_INFO);
    }
    @Step("Deleting user")
    public static void deleteUser(String token){
        given()
                //.header("content-type", "application/json")
                .header("authorization", token)
                //.auth().oauth2("\""+ token +"\"")
                //.auth().oauth2(token)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when()
                .delete(BASE_URL + USER_INFO)
                .then()
                .assertThat()
                .statusCode(202);
    }


}
