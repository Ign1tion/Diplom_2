import io.qameta.allure.junit4.DisplayName;
import org.junit.Before;
import org.junit.Test;

import static org.apache.http.HttpStatus.SC_OK;
import static org.apache.http.HttpStatus.SC_UNAUTHORIZED;
import static steps.OrderSteps.*;
import static steps.UserSteps.createUser;
import static org.hamcrest.CoreMatchers.is;

public class GetOrdersTest extends BaseTest {
    @Before
    public void preSetUp() {
        TOKEN = createUser(EMAIL, PASSWORD, NAME)
                .then()
                .extract().path("accessToken");
        createOrderWithAuth(INGREDIENTS, TOKEN);
    }
@Test
    @DisplayName("Get user orders with authorization")
    public void getUserOrderWithAuthTest(){
        getUserOrderAuth(TOKEN)
                .then()
                .assertThat()
                .statusCode(SC_OK)
                .and()
                .body("success", is(true));
}
@Test
    @DisplayName("Get user orders without authorization")
    public void getUserOrderNoAuthTest(){
        getUserOrderNoAuth()
                .then()
                .assertThat()
                .statusCode(SC_UNAUTHORIZED)
                .and()
                .body("success", is(false));
}
}