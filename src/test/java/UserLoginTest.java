import io.qameta.allure.junit4.DisplayName;
import org.junit.Before;
import org.junit.Test;

import static org.apache.http.HttpStatus.SC_OK;
import static org.apache.http.HttpStatus.SC_UNAUTHORIZED;
import static steps.UserSteps.createUser;
import static steps.UserSteps.loginUser;
import static org.hamcrest.CoreMatchers.is;

public class UserLoginTest extends BaseTest {
    @Before
    public void preSetUp(){
        TOKEN = createUser(EMAIL, PASSWORD, NAME)
                .then()
                .extract().path("accessToken");
    }
    @Test
    @DisplayName("Login with existing email/password pair")
    public void loginUserTest(){
        TOKEN = loginUser(EMAIL, PASSWORD)
                .then()
                .assertThat()
                .statusCode(SC_OK)
                .body("success", is(true))
                .extract().path("accessToken");

    }
    @Test
    @DisplayName("Login with wrong email field")
    public void loginUserWithWrongEmail(){
        loginUser(null, PASSWORD)
                .then()
                .assertThat()
                .statusCode(SC_UNAUTHORIZED)
                .and()
                .body("success", is(false));
    }
    @Test
    @DisplayName("Login with wrong password field")
    public void loginUserWithWrongPassword(){
        loginUser(EMAIL, null)
                .then()
                .assertThat()
                .statusCode(SC_UNAUTHORIZED)
                .and()
                .body("success", is(false));
    }
}
