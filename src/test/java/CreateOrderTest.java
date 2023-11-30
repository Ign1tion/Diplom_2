import io.qameta.allure.junit4.DisplayName;
import org.junit.Before;
import org.junit.Test;
import steps.OrderSteps;

import static org.apache.http.HttpStatus.*;
import static steps.OrderSteps.*;
import static steps.UserSteps.createUser;
import static org.hamcrest.CoreMatchers.is;

public class CreateOrderTest extends BaseTest {
    @Before
    public void preSetUp() {
        TOKEN = createUser(EMAIL, PASSWORD, NAME)
                .then()
                .extract().path("accessToken");
    }
    @Test
    @DisplayName("Order creation with auth and ingredients")
    public void createOrderWithAuthAndIngredient(){
        createOrderWithAuth(INGREDIENTS, TOKEN)
                .then()
                .assertThat()
                .statusCode(SC_OK)
                .and()
                .body("success", is(true));

    }
    @Test
    @DisplayName("Order creation without auth and with ingredients")
    public void createOrderWithoutAuthButIngredients(){
        createOrderWithoutAuth(INGREDIENTS)
                .then()
                .assertThat()
                .statusCode(SC_OK)
                .and()
                .body("success", is(true));
    }
    @Test
    @DisplayName("Order creation with auth and without ingredients")
    public void createOrderWithoutIngredientsButAuth(){
        createOrderWithoutIngredientButAuth(TOKEN)
                .then()
                .assertThat()
                .statusCode(SC_BAD_REQUEST)
                .and()
                .body("success", is(false));

    }
    @Test
    @DisplayName("Order creation with auth and wrong ingredients hash")
    public void createOrderWithAuthAndFakeIngredients(){
        createOrderWithAuth(FAKE_INGREDIENTS, TOKEN)
                .then()
                .assertThat()
                .statusCode(SC_INTERNAL_SERVER_ERROR);
    }
}