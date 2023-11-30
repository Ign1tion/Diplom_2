import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;

import static org.apache.http.HttpStatus.SC_FORBIDDEN;
import static org.hamcrest.CoreMatchers.is;
import static org.apache.http.HttpStatus.SC_OK;
import static steps.UserSteps.createUser;

public class UserCreationTest extends BaseTest {
    @Test
    @DisplayName("Basic user creation")
    public void createUserTest(){
       TOKEN = createUser(EMAIL, PASSWORD, NAME)
                .then()
               .assertThat()
               .statusCode(SC_OK)
               .body("success", is(true))
               .and()
                .extract().path("accessToken");

    }
    @Test
    @DisplayName("Creating already existing user")
    public void createExistingUserTest(){
        TOKEN = createUser(EMAIL, PASSWORD, NAME)
                .then()
                .extract().path("accessToken");
        createUser(EMAIL, PASSWORD, NAME)
                .then()
                .assertThat()
                .statusCode(SC_FORBIDDEN)
                .and()
                .body("success", is(false))
                .body("message", is("User already exists"));

    }
    @Test
    @DisplayName("Creating user without email field")
    public void createUserWithoutEmailField(){
        TOKEN = createUser(null, PASSWORD, NAME)
                .then()
                .assertThat()
                .statusCode(SC_FORBIDDEN)
                .body("success", is(false))
                .body("message", is("Email, password and name are required fields"))
                .extract().path("accessToken");

    }
    @Test
    @DisplayName("Creating user without password field")
    public void createUserWithoutPasswordField(){
        TOKEN = createUser(EMAIL, null, NAME)
                .then()
                .assertThat()
                .statusCode(SC_FORBIDDEN)
                .body("success", is(false))
                .body("message", is("Email, password and name are required fields"))
                .extract().path("accessToken");
    }
    @Test
    @DisplayName("Creating user without name field")
    public void createUserWithoutNameField(){
        TOKEN = createUser(EMAIL, PASSWORD, null)
                .then()
                .assertThat()
                .statusCode(SC_FORBIDDEN)
                .body("success", is(false))
                .body("message", is("Email, password and name are required fields"))
                .extract().path("accessToken");
    }

}
