package steps;

import io.qameta.allure.Step;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.response.Response;
import model.Order;
import model.User;

import java.util.List;

import static config.Endpoints.ORDERS;
import static config.URL.BASE_URL;
import static io.restassured.RestAssured.given;

public class OrderSteps {
@Step("Creating order with authorization and ingredients")
    public static Response createOrderWithAuth(List<String> ingredients, String  token){
    Order order = new Order(ingredients);
    return given()
            .header("Content-type", "application/json")
            .header("authorization", token)
            .filter(new RequestLoggingFilter())
            .filter(new ResponseLoggingFilter())
            .body(order)
            .when()
            .post(BASE_URL+ORDERS);

}
@Step("Creating order with ingredients and without authorization")
    public static Response createOrderWithoutAuth(List<String> ingredients){
    Order order = new Order(ingredients);
    return given()
            .header("content-type", "application/json")
            .filter(new RequestLoggingFilter())
            .filter(new ResponseLoggingFilter())
            .body(order)
            .when()
            .post(BASE_URL+ORDERS);
}
@Step("Creating order with authorization and without ingredients")
    public static Response createOrderWithoutIngredientButAuth(String token){
    return given()
            .header("authorization", token)
            .header("content-type", "application/json")
            .filter(new RequestLoggingFilter())
            .filter(new ResponseLoggingFilter())
            .when()
            .post(BASE_URL+ORDERS);
}
@Step("Get user order with auth token")
    public static Response getUserOrderAuth(String token){
    return given()
            .header("authorization", token)
            .filter(new RequestLoggingFilter())
            .filter(new ResponseLoggingFilter())
            .when()
            .get(BASE_URL+ORDERS);
}
@Step("Get user order without auth token")
    public static Response getUserOrderNoAuth(){
    return given()
            .filter(new RequestLoggingFilter())
            .filter(new ResponseLoggingFilter())
            .when()
            .get(BASE_URL+ORDERS);
}
}
