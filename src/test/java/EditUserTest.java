import io.qameta.allure.junit4.DisplayName;
import org.junit.Before;
import org.junit.Test;

import static org.apache.http.HttpStatus.SC_UNAUTHORIZED;
import static org.hamcrest.CoreMatchers.is;
import static org.apache.http.HttpStatus.SC_OK;
import static steps.UserSteps.*;

public class EditUserTest extends BaseTest {
    @Before
    public void preSetUp(){
        TOKEN = createUser(EMAIL, PASSWORD, NAME)
                .then()
                .extract().path("accessToken");
    }
    @Test
    @DisplayName("Editing user data with authorization")
    public void editUserWithAuthorizationTest(){
    editUserAuth(EDITED_EMAIL,null, EDITED_NAME, TOKEN)
            .then()
            .assertThat()
            .statusCode(SC_OK)
            .and()
            .body("success", is(true))
            .body("user.email", is(EDITED_EMAIL))
            .body("user.name", is(EDITED_NAME));
    }
    @Test
    @DisplayName("Editing user data without authorization")
    public void editUserWitoutAuthorizationTest(){
        editUserNoAuth(EDITED_EMAIL, null, EDITED_NAME)
                .then()
                .assertThat()
                .statusCode(SC_UNAUTHORIZED)
                .and()
                .body("success", is(false));
    }
}
